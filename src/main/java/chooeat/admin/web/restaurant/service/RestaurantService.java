package chooeat.admin.web.restaurant.service;

import java.util.List;

import chooeat.admin.web.restaurant.pojo.AdminRestaurantPOJO;
import chooeat.admin.web.restaurant.pojo.AdminRestaurantVO;

public interface RestaurantService {
	
	AdminRestaurantVO edit(Integer resId);
	
	List<AdminRestaurantPOJO> findAll();
		
	List<AdminRestaurantPOJO> searchBySomething(String searchType, String searchCondition);

}
