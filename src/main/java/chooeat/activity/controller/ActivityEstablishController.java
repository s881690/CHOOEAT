package chooeat.activity.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Object establish(@RequestBody Map<String, String> map) {
		System.out.println(map);
		return activityService.establish(map);
	}
	

	@GetMapping("/restaurantList")
	public List<ActivityRestaurantVO> restaurantList(){
		return activityRestaurantService.restaurantList();
	}
	
	@GetMapping("/findEdit/{activityId}")
	public Object findEdit(@PathVariable(value = "activityId") Integer activityId) {
		System.out.println(activityId);
		return activityService.findEdit(activityId);
	}
}
