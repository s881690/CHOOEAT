package chooeat.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chooeat.activity.service.ActivityMemberService;
import chooeat.activity.service.ActivityService;
import chooeat.activity.vo.ActivityVO;

// 使用 @Controller 定義 Bean
@RestController
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	ActivityService activityService;
	@Autowired
	ActivityMemberService activityMemberService;

	// activity.js
	@GetMapping("/activityList")
	public List<ActivityVO> activityList() {
		return activityService.sellectAll();
	}
	
	// activity.js
	@GetMapping("/activitys")
	public List<ActivityVO> activitys() {
		return activityService.all();
	}

	@GetMapping("/search")
	public List<ActivityVO> search(@RequestParam(value = "search_value", required = false) String value) {
		return activityService.search(value);
	}
}
