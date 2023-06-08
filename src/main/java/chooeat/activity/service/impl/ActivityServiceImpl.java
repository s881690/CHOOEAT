package chooeat.activity.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import chooeat.activity.dao.ActivityMemberRepository;
import chooeat.activity.dao.ActivityRepository;
import chooeat.activity.service.ActivityService;
import chooeat.activity.vo.ActivityVO;

@Component
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	ActivityMemberRepository activityMemberRepository;

	// 顯示所有資料
	@Transactional
	@Override
	public List<ActivityVO> sellectAll() {
		List<ActivityVO> list = activityRepository.findAll();
		for (int i = 0; i < list.size(); i++) {
			// 若activityPhoto欄位不為空，才進來轉
			if(list.get(i).getActivityPhoto() != null) {
				// 將byte[]圖片轉為base64 String
				String base64String = Base64.getEncoder().encodeToString(list.get(i).getActivityPhoto());
				list.get(i).setActivityPhotoBase64(base64String);
			}
		}
		return list;
	}

	
	// 活動名稱查詢
	@Transactional
	@Override
	public List<ActivityVO> search(String value) {
		List<ActivityVO> list = activityRepository.findByActivityNameContaining(value);
		for (int i = 0; i < list.size(); i++) {
			// 將byte[]圖片轉為base64 String
			String base64String = Base64.getEncoder().encodeToString(list.get(i).getActivityPhoto());
			list.get(i).setActivityPhotoBase64(base64String);
		}
		return list;
	}

	@Override
	@Transactional
	public ActivityVO findByActivityId(Integer activityId) {
		ActivityVO activityVO = activityRepository.findByActivityId(activityId);
		String base64String = Base64.getEncoder().encodeToString(activityVO.getActivityPhoto());
		activityVO.setActivityPhotoBase64(base64String);
		return activityVO;

	}

	// 活動申請
	@Override
	@Transactional
	public Object establish(Map<String, String> map) {
		ActivityVO activityVO = new ActivityVO();
		if(!map.get("activityId").isEmpty()) {
			activityVO.setActivityId(Integer.parseInt(map.get("activityId")));
		}
		activityVO.setActivityName(map.get("activityName"));
		activityVO.setActivityNumber(Integer.parseInt(map.get("activityNumber")));
		activityVO.setMinNumber(Integer.parseInt(map.get("minNumber")));
		activityVO.setMaxNumber(Integer.parseInt(map.get("maxNumber")));
		activityVO.setActivityDate(java.sql.Date.valueOf(map.get("activityDate")));
		activityVO.setAccId(Integer.parseInt(map.get("accId")));
		activityVO.setRestaurantId(Integer.parseInt(map.get("restaurantId")));
		activityVO.setRegesterationStartingTime(java.sql.Timestamp.valueOf(map.get("regesterationStartingTime")));
		activityVO.setRegesterationEndingTime(java.sql.Timestamp.valueOf(map.get("regesterationEndingTime")));
		activityVO.setActivityStartingTime(java.sql.Time.valueOf(map.get("activityStartingTime")+ ":00"));
		activityVO.setActivityEndingTime(java.sql.Time.valueOf(map.get("activityEndingTime")+ ":00"));
		activityVO.setActivityText(map.get("activityText"));
		activityVO.setActivityStatus(Integer.parseInt(map.get("activityStatus")));
		activityVO.setActivityPhotoBase64(map.get("activityPhotoBase64"));
		System.out.println(activityVO);
		
		if (activityVO.getAccId() == null) {
			return "請先登入";
		}
		if (activityVO.getActivityDate() == null) {
			return "請輸入活動日期";
		}
		if (activityVO.getActivityName() == null) {
			return "請輸入活動名稱";
		}
		if (activityVO.getRestaurantId() == null) {
			return "請輸入活動餐廳";
		}
		if (activityVO.getRegesterationStartingTime() == null) {
			return "請輸入活動報名開始時間";
		}
		if (activityVO.getRegesterationEndingTime() == null) {
			return "請輸入活動報名結束時間";
		}
		if (activityVO.getActivityStartingTime() == null) {
			return "請輸入活動開始時間";
		}
		if (activityVO.getActivityEndingTime() == null) {
			return "請輸入活動結束時間";
		}
		if (activityVO.getMinNumber() == null) {
			return "請輸入最少參加人數";
		}
		if (activityVO.getMaxNumber() == null) {
			return "請輸入最多參加人數";
		}
		if (activityVO.getActivityPhotoBase64() == null) {
			return "請上傳揪團照片";
		}
		
		String activityPhotoBase64 = activityVO.getActivityPhotoBase64();
		// 解碼base64圖片字串，然後塞回activityPhoto中
		byte[] activityPhoto = Base64Utils.decodeFromString(activityPhotoBase64);
		activityVO.setActivityPhoto(activityPhoto);

		activityRepository.save(activityVO);
		return activityVO;
	}

	@Override
	public ActivityVO findEdit(Integer activityId) {
		return activityRepository.findByActivityId(activityId);
	}

	@Override
	public Integer addActivityMember(Integer activityId) {
//		Integer activityNumber = activityMemberRepository.countByActivityId(activityId);
//		System.out.println(activityNumber);
//		ActivityVO activityVO = activityRepository.findByActivityId(activityId);
//		activityVO.setActivityNumber(activityNumber); 
//		System.out.println(activityVO.getActivityNumber());
//		return activityVO.getActivityNumber();
		return null;
		
	}


}
