package chooeat.activity.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import chooeat.activity.dao.ActivityRepository;
import chooeat.activity.service.ActivityService;
import chooeat.activity.vo.ActivityVO;
import chooeat.activity.vo.SavedActivityVO;

@Component
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	ActivityRepository activityRepository;

	// 顯示所有資料
	@Transactional
	@Override
	public List<ActivityVO> sellectAll() {
		List<ActivityVO> list = activityRepository.findAll();
		for(int i = 0 ; i < list.size(); i++) {
			// 將byte[]圖片轉為base64 String
			  String base64String = Base64.getEncoder().encodeToString(list.get(i).getActivityPhoto());
			  list.get(i).setActivityPhotoBase64(base64String);
		}
		return list;
	}

//	// 顯示前三筆資料於活動首頁
//	@Transactional
//	public String get3Activity(){
//		List<ActivityVO> list = activityDAO.selectAll();
////		// 使用jackson的object映射，可以將object轉為json
////		 ObjectMapper mapper = new ObjectMapper();
//		 // 將object轉為json
////		 String jsonStr = mapper.writeValueAsString(list.subList(0, 3));
//		String jsonStr = GsonUtils.toJson(list.subList(0, 3));
//		return jsonStr;
//	}
	
	
	// 活動id查詢
//	@Transactional
//	public ActivityVO selectByActivityId(Integer activityId) {
////		return activityDAO.selectByActivityId(activityId);
//		return activityRepository.findByActivityId(activityId);
//	};
	
	// 活動名稱查詢
	@Transactional
	@Override
	public List<ActivityVO> search(String value) {
		return  activityRepository.findByActivityNameContaining(value);
	}

	@Override
	@Transactional
	public ActivityVO findByActivityId(Integer activityId) {
		return activityRepository.findByActivityId(activityId);
	}

	// 活動申請
	@Override
	@Transactional
	public ActivityVO establish(ActivityVO activityVO) {
		String activityPhotoBase64 = activityVO.getActivityPhotoBase64();
		//解碼base64圖片字串，然後塞回activityPhoto中
		byte[] activityPhoto = Base64Utils.decodeFromString(activityPhotoBase64);
		activityVO.setActivityPhoto(activityPhoto);
		
		try {
			activityRepository.save(activityVO);
		}catch(Exception e){
			System.out.println(e);
			return null;
		}

		return activityVO;
	}

	@Override
	public String get3Activity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ActivityVO activityVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(ActivityVO activityVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void like(SavedActivityVO savedActivityVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dislike(Integer activityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SavedActivityVO> getlike(Integer accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getMembersbyActivityName(String activityName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean selectByAccIdandActivityId(Integer accId, Integer activityId) {
		// TODO Auto-generated method stub
		return false;
	}



}
