package chooeat.activity.service;

import java.util.List;

import chooeat.activity.vo.ActivityMemberVO;

public interface ActivityMemberService {
	public String isSignUp(Integer accId, Integer activityId);
	public String SignUp(ActivityMemberVO activityMemberVO);
	public String deleteMember(Integer accId);
	public List<ActivityMemberVO> memberList(Integer activityId);
	public List<ActivityMemberVO> findByAccNameAndActivityId(String accName, Integer activityId);
	
}
