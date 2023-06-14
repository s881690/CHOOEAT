package chooeat.activity.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import chooeat.activity.service.ActivityMemberService;
import chooeat.activity.service.ActivityService;
import chooeat.activity.vo.ActivityMemberVO;

// 使用 @Controller 定義 Bean
@RestController
@RequestMapping("/activity")
public class ActivityMemberController {
	@Autowired
	ActivityService activityService;
	@Autowired
	ActivityMemberService activityMemberService;

	@GetMapping("/memberList/{activityId}")
	public List<ActivityMemberVO> memberList(@PathVariable Integer activityId) {
		return activityMemberService.memberList(activityId);
	}

	@DeleteMapping("/deleteMember/{accId}/{activityId}")
	public String deleteMember(@PathVariable Integer activityId,@PathVariable Integer accId) {
		return activityMemberService.deleteMember(activityId, accId);
	}
	
	@GetMapping("/findMember")
	public List<ActivityMemberVO> findMember(@RequestParam String accName, @RequestParam Integer activityId) {
		return activityMemberService.findByAccNameAndActivityId(accName, activityId)
;	}
}
