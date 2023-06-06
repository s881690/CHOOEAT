package chooeat.reservation.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import chooeat.reservation.dao.AccountRepository;
import chooeat.reservation.dao.ReservationDao;
import chooeat.reservation.dao.ReservationRepository;
import chooeat.reservation.dao.RestaurantDayoffRepository;
import chooeat.reservation.dao.RestaurantRepository;
import chooeat.reservation.model.AccountVo;
import chooeat.reservation.model.EmailDetails;
import chooeat.reservation.model.EmailInfo;
import chooeat.reservation.model.HourlySeat;
import chooeat.reservation.model.ReservationVO;
import chooeat.reservation.model.RestaurantVO;
import chooeat.reservation.model.Result;
import chooeat.reservation.service.ReservationService;



@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private RestaurantDayoffRepository restaurantDayoffRepository;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	EmailInfo emailInfo;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	EmailDetails details;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountVo accountVo;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public Boolean isDayOff(Integer restaurant_id, String date) {
		// 把前端傳來的日期字串轉換成sqlDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Date selecteDate = Date.valueOf(localDate);

		// 資料庫查詢公休日資料表是否有那一天
		if (restaurantDayoffRepository.dayOffDate(restaurant_id, selecteDate) != null) {
			Date sqlDate = restaurantDayoffRepository.dayOffDate(restaurant_id, selecteDate).getDayoff();
			if (selecteDate.equals(sqlDate)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Optional<RestaurantVO> selectResInfo(int restaurantId) {

		return restaurantRepository.findById(restaurantId);
	}

	@Override
	public List<HourlySeat> findHourlySeats(int restaurantId, String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Date selecteDate = Date.valueOf(localDate);
		return reservationDao.selectall(restaurantId, selecteDate);

	}

	@Override
	public List<Integer> reservedData(int memberId, int restaurantId, String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Date selecteDate = Date.valueOf(localDate);
		return reservationDao.reservedList(memberId, restaurantId, selecteDate);

	}

	@Override
	public Integer reservation(ReservationVO reservationVO) {

		return reservationDao.insertReservation(reservationVO);
	}

	@Override
	public void sendMail(int memberId, int reservationId) {

		// 取得信件內容必要資訊
		// 這裡輸入會員編號跟預約編號
		emailInfo = reservationDao.getEmailInfos(memberId, reservationId).get(0);
		// 收件人email
		details.setRecipient(emailInfo.getRecipient());
		System.out.println(details.getRecipient().toString());

		// 寄件主旨，要+訂位編號
		details.setSubject("訂位成功通知 - 訂位編號" + emailInfo.getReservationId().toString());
		System.out.println(details.getSubject().toString());

		// 信件內容
		details.setMsgBody("親愛的會員 " + emailInfo.getRecipientName() + "您好：\n" 
						 + "我們很高興通知您，您的訂位已成功完成。以下是您的訂位詳細信息：\n " 
						 + "時間：" + emailInfo.getReservationTime() + "\n" 
						 + "餐廳名稱： " + emailInfo.getRestaurantName() + "\n"
						 + "地點：" + emailInfo.getRestaurantAddress() + "\n" 
						 + "電話： " + emailInfo.getRestaurantPhone() + "\n"
						 + "預訂人數：" + emailInfo.getReservationNumber());
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// 上面的value綁定application.properties綁定的寄件人
			mailMessage.setFrom(sender);
			// 收件人email
			mailMessage.setTo(details.getRecipient());
			// 信件內容
			mailMessage.setText(details.getMsgBody());
			// 信件主旨
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			String status = "Mail Sent Successfully...";
			System.out.println(status);
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			String status = "Error while Sending Mail";
			System.out.println(status);
		}
		
	
	}

	@Override
	public Boolean reservationUpdate(ReservationVO reservationVO,int reservationId) {
		return reservationDao.updateReservation(reservationVO, reservationId);
	}

	@Override
	public Boolean reservationDelete(int reservationId) {

		return reservationDao.deleteReservation(reservationId);
	}


	@Override
	public List<Result> reservationInfo(int reservationId) {
		
		

		return reservationDao.reservationData(reservationId);
	}

	@Override
	public String memberName(int memberId) {
		
		return accountRepository.findById(memberId).get().getAccName();
	}

	@Override
	public List<EmailInfo> getAllreservation(int memberId) {
		
		return reservationDao.selectAllReservationForMember(memberId);
	}

	@Override
	public String getRestaurantNameByReservation(int reservationId) {
		
		return reservationDao.getRestaurantNameByReservation(reservationId).get(0);
	}

	

	

//	@Override
//	public Boolean booking(ReservationVO reservationVO) {

	// 判斷訂位人數是否大於0，少於1人擋下
//		if(reservationVO.getReservationNumber() < 1) {
//			return false;
//		}

	// 判斷訂位時間是否晚於當前時間，晚於當前時間擋下
//		 LocalDateTime currentDateTime = LocalDateTime.now();
//	     LocalDateTime reservationDateTime = reservationVO.getReservationDateStartTime().toLocalDateTime();
//	     if (reservationDateTime.isAfter(currentDateTime)) {
//	    	 return false;
//	        }

//	
//	}

}
