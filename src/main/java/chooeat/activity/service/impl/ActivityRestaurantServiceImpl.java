package chooeat.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chooeat.activity.dao.ActivityRestaurantRepository;
import chooeat.activity.service.ActivityRestaurantService;
import chooeat.activity.vo.ActivityRestaurantVO;


@Component
public class ActivityRestaurantServiceImpl implements ActivityRestaurantService{
	@Autowired
	ActivityRestaurantRepository restaurantRepository;
	
	// 找到所有餐廳
	@Override
	public List<ActivityRestaurantVO> restaurantList() {
		return restaurantRepository.findAll();
	};
	
}
