package chooeat.admin.web.restaurant.dao;

import java.util.List;

import chooeat.admin.web.restaurant.pojo.RestaurantVO;

public interface RestaurantDAO {

	int register(List<String> values);	

	int updateByRestaurantId(RestaurantVO RestaurantVO);
	
	List<RestaurantVO> searchRestaurants(String searchString);
	
	List<RestaurantVO> login(String account,String password);
	
	List<Object> findfollow(String resAcc);
	
	List<Object> findrestype(String resAcc);
	
	List<Object> findprod(String resAcc);
	
	List<Object> findcomment(String resAcc);
	
	List<Object> findmyself(String resAcc);
	
	List<RestaurantVO> selectAll (); 
	
	List<RestaurantVO> selectAllByResId (String resId);
	
	List<RestaurantVO> selectAllByResName (String resName);
}
