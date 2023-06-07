package chooeat.reservation.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import chooeat.reservation.model.EmailDetails;
import chooeat.reservation.model.ReservationVO;
import chooeat.reservation.model.RestaurantVO;
import chooeat.reservation.model.Result;
import chooeat.reservation.service.ReservationService;
import chooeat.reservation.service.SessionStorageService;
import redis.clients.jedis.Jedis;

@RestController
public class ReservationController {
	// 注入service物件
	@Autowired
	ReservationService reservationService;
	@Autowired
	ReservationVO reservationVO;
	@Autowired
	EmailDetails details;
	@Autowired
	RestaurantVO restaurantVO;
	@Autowired
	Jedis jedis;
	@Autowired
	private SessionStorageService sessionStorageService;

	@PostConstruct
	public void init() {
		jedis.select(2);

	}

	int count = 1;
	String index = "res:" + count;
		

	// 選擇日期時下判斷的controller
	@GetMapping("/getBusinessDay")
	public Result getBusinessDay(@RequestParam("date") String date) {
		Result result = new Result();

		System.out.println(date);

		// 如果是公休日，就回傳"status", "dayoff"
		// 寫死，1號會員2號餐廳
		if (reservationService.isDayOff(2, date)) {
			result.setStatus("dayoff");
			System.out.println("dayoff");

		} else {
			result.setStatus("BusinessDay");
			System.out.println("BusinessDay");
			// 以下抓出營業時間並回傳前端
			restaurantVO = reservationService.selectResInfo(2).get();
			result.setResStartTime(restaurantVO.getResStartTime().toString());
			result.setResEndTime(restaurantVO.getResEndTime().toString());

			// 以下檢查當天每個時段的剩餘座位數
			result.setHourlySeatlist(reservationService.findHourlySeats(2, date));

			// 檢查當天該會員是否有預約
			result.setReservedList(reservationService.reservedData(1, 2, date));
		}

		return result;

	}

	// 訂位輸入成功，傳入redis，進入結帳頁面
	// 會員編號1，餐廳編號2
	@PostMapping("/reservationRedis")
	public Result reservationRedis(@RequestBody Map<String, Object> map) {
		Result result = new Result();
		// 接前端的參數

		String dateTime = (String) map.get("date_time");
		Integer reservationNumber = Integer.valueOf((String) map.get("ppl"));
		String text = (String) map.get("text");
		
		Double accid =  (Double) map.get("acc_id");
		Double restaurantId =  (Double) map.get("restaurantId");

		System.out.println("accid = " + accid);
		System.out.println("restaurantId = " + restaurantId);
		

		reservationVO.setReservationNumber(reservationNumber);
		reservationVO.setReservationDateStartTime(Timestamp.valueOf(dateTime));
		if (!text.isEmpty()) {
			reservationVO.setReservationNote(text);
		} else {
			reservationVO.setReservationNote("");
		}
		try {
			// 以下存入redis

			jedis.rpush(index, "1", "2", reservationNumber.toString(), dateTime, text);

			result.setStatus("success");
		} catch (Exception e) {
			result.setStatus("error");
		}

		return result;

	}

	// 進入結帳頁面，抓預約人數作為結帳的數字
	@GetMapping("/getRedisResPpl")
	public Result getRedisResPpl() {
		Result result = new Result();
		String reservationNumber = jedis.lindex(index, 2);
		System.out.println(reservationNumber);
		result.setReservedPeople(Integer.valueOf(reservationNumber));
		result.setStatus("success");
		return result;

	}

	// 結帳確認後發出請求，如果成功，先將資料insert進資料庫，後刪除redis內數據
	// dao層寫死會員1號，餐廳2號
	@GetMapping("/reservation")
	public Result reserve() {
		Result result = new Result();
		// 從redis抓出暫存的預約資料，設定給vo
		List<String> list = jedis.lrange(index, 0, jedis.llen(index));
		for (String res : list)
			System.out.println(res);
		reservationVO.setAccId(Integer.valueOf(jedis.lindex(index, 0)));
		reservationVO.setRestaurantId(Integer.valueOf(jedis.lindex(index, 1)));
		reservationVO.setReservationNumber(Integer.valueOf(jedis.lindex(index, 2)));
		reservationVO.setReservationDateStartTime(Timestamp.valueOf(jedis.lindex(index, 3)));
		String text = jedis.lindex(index, 4);
		if (!text.isEmpty()) {
			reservationVO.setReservationNote(text);
		} else {
			reservationVO.setReservationNote("");
		}
		// 預約成功，回傳訂單編號
		int reservationId = reservationService.reservation(reservationVO);

		jedis.del(index);
		if (reservationId != 0) {
			result.setStatus("success");
			reservationService.sendMail(1, reservationId);
			result.setReservationId(reservationId);
		} else {
			result.setStatus("");
		}

		return result;
	}

	// 修改訂位資料
	// reservationId應該要在這裡作為參數傳進update，先寫死
	@PutMapping("/reservationUpdate")
	public Result reservationUpdate(@RequestParam("ppl") Integer ppl, @RequestParam("date_time") String dateTime,
			@RequestParam(value = "text", required = false) String text) {
		Result result = new Result();
		// 傳入人數和時間，存成vo
		reservationVO.setReservationNumber(ppl);
		reservationVO.setReservationDateStartTime(Timestamp.valueOf(dateTime));
		// 如果備註項目不是空值，就存進vo
		if (!text.isEmpty()) {
			reservationVO.setReservationNote(text);
		}

		if (reservationService.reservationUpdate(reservationVO, 1)) {
			result.setStatus("success");
		} else {
			result.setStatus("");
		}

		return result;
	}

	// 刪除訂位資料
	// reservationId應該要在這裡作為參數傳進update，先寫死
	@DeleteMapping("/reservationDelete")
	public Result reservationDelete() {
		Result result = new Result();
		if (reservationService.reservationDelete(2)) {
			result.setStatus("success");
		} else {
			result.setStatus("");
		}

		return result;
	}

	// 取得餐廳名稱，print在畫面上
	// 固定2號餐廳，先寫死
	@GetMapping("/restaurantName")
	public String getRestaurantName() {

		return reservationService.selectResInfo(2).get().getResName();

	}

	// 取得餐廳地址，串接地圖導航功能
	// 固定2號餐廳，先寫死
	@GetMapping("/restaurantAddress")
	public String getRestaurantAddress() {

		return reservationService.selectResInfo(2).get().getResAdd();
	}

	// 用訂位編號取得會員名稱、訂位時間、訂位人數，訂位成功/修改成功用
	// 訂位編號先固定10號
	@GetMapping("/getReservationInfo")
	public Result getReservationInfo() {

		return reservationService.reservationInfo(10).get(0);

	}

	// 用會員編號取得會員名稱，print在訂位取消的畫面上
	// 會員編號固定1號
	@GetMapping("/getMemberName")
	public String getMemberName() {

		return reservationService.memberName(1);

	}

}
