package chooeat.activity.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chooeat.activity.dao.ActivityMemberRepository;
import chooeat.activity.dao.ActivityRepository;
import chooeat.activity.service.ActivityMemberService;
import chooeat.activity.vo.ActivityMemberVO;
import chooeat.activity.vo.ActivityVO;


@Component
public class ActivityMemberServiceImpl implements ActivityMemberService{
	@Autowired
	ActivityMemberRepository activityMemberRepository;
	@Autowired
	ActivityRepository activityRepository;
	
	@Override
	// 檢核是否報名
	public String isSignUp(Integer accId, Integer activityId) {
		// 判斷是否已經有這筆資料
		ActivityMemberVO activityMemberVO = activityMemberRepository.findByAccIdAndActivityId(accId,activityId);
		if(activityMemberVO == null) {
			return "false";
		}
		return "true";
		
	}

	// 報名活動，並同時讓該活動對應的activityVO中，activityNumber++
	@Override
	public Integer SignUp(ActivityMemberVO activityMemberVO) {

			activityMemberRepository.save(activityMemberVO);
			Integer activityId = activityMemberVO.getActivityId();
			Integer activityNumber = activityMemberRepository.countByActivityId(activityId);
			activityNumber ++;
			// 將更新後的activityNumber存回資料庫中
			ActivityVO activityVO = activityRepository.findByActivityId(activityId);
			activityVO.setActivityNumber(activityNumber);
			activityRepository.save(activityVO);
			return activityNumber;


	}

	@Transactional
	@Override
	public String deleteMember(Integer accId) {
		try {
			activityMemberRepository.deleteByAccId(accId);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		
		return "true";
	}

	@Override
	public List<ActivityMemberVO> memberList(Integer activityId) {
		List<ActivityMemberVO> list;
		try {
			list = activityMemberRepository.findByActivityId(activityId);
		} catch (Exception e) {
			return null;
		}
		
		return list;
	}

	@Override
	public List<ActivityMemberVO> findByAccNameAndActivityId(String accName, Integer activityId) {
		List<ActivityMemberVO> list = activityMemberRepository.findByAccNameAndActivityId(accName, activityId);
		return list;
	};
	
}
