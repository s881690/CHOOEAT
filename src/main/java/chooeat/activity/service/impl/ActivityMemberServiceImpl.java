package chooeat.activity.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chooeat.activity.dao.ActivityMemberRepository;
import chooeat.activity.service.ActivityMemberService;
import chooeat.activity.vo.ActivityMemberVO;


@Component
public class ActivityMemberServiceImpl implements ActivityMemberService{
	@Autowired
	ActivityMemberRepository activityMemberRepository;
	
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

	// 報名活動
	@Override
	public String SignUp(ActivityMemberVO activityMemberVO) {
		try {
			activityMemberRepository.save(activityMemberVO);
			return "true";
		}catch( Exception e){
			e.printStackTrace();
			return "false";
		}


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
