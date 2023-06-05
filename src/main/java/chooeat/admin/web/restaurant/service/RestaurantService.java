package chooeat.admin.web.restaurant.service;

import java.util.List;

import chooeat.admin.web.restaurant.pojo.RestaurantVO;

public interface RestaurantService {
	
	RestaurantVO edit(Integer resId);
	
	List<RestaurantVO> findAll();
		
	List<RestaurantVO> searchBySomething(String searchType, String searchCondition);

}
