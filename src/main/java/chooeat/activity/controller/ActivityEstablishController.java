package chooeat.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.activity.service.ActivityRestaurantService;
import chooeat.activity.service.ActivityService;
import chooeat.activity.vo.ActivityRestaurantVO;
import chooeat.activity.vo.ActivityVO;

@RestController
@RequestMapping("/activity")
public class ActivityEstablishController {
	@Autowired
	ActivityService activityService;
	@Autowired
	ActivityRestaurantService activityRestaurantService;
	
	
	@PostMapping("/establish")
	public ActivityVO establish(@RequestBody ActivityVO activityVO) {
		return activityService.establish(activityVO);
	}
	

	@GetMapping("/restaurantList")
	public List<ActivityRestaurantVO> restaurantList(){
		return activityRestaurantService.restaurantList();
	}
}
