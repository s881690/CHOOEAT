package chooeat.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.activity.service.ActivityMemberService;
import chooeat.activity.service.ActivityService;
import chooeat.activity.vo.ActivityMemberVO;
import chooeat.activity.vo.ActivityVO;

@RestController
@RequestMapping("/activity")
public class ActivityDetailController {
	@Autowired
	ActivityMemberService activityMemberService;
	@Autowired
	ActivityService activityService;
	
	@GetMapping( "/detail/{activityId}")
	public ActivityVO detail(@PathVariable Integer activityId){
		return activityService.findByActivityId(activityId);
	}
	
	// 放在detail.js
	@GetMapping("/isSignup")
	public String isSignup(Integer accId,Integer activityId){
		return activityMemberService.isSignUp(accId, activityId);
	}

	@PostMapping("/signup")
	public Integer signup(@RequestBody ActivityMemberVO activityMemberVO){
		return activityMemberService.SignUp(activityMemberVO);
//		return new ObjectMapper().writeValueAsString(activityService.signup(activityMemberVO));
	}
	
	// 進行報名，會讓ActivityVO中的activityNumber ++
	@GetMapping("/addActivityMember")
	public Integer addActivityMember(Integer activityId){
		return activityService.addActivityMember(activityId);
//		return new ObjectMapper().writeValueAsString(activityService.signup(activityMemberVO));
	}
	

}
