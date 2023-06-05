package chooeat.admin.web.restaurant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.restaurant.dao.RestaurantDAO;
import chooeat.admin.web.restaurant.pojo.RestaurantVO;
import chooeat.admin.web.restaurant.service.RestaurantService;

@Service
public class AdminRestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantDAO dao;

	@Override
	public RestaurantVO edit(Integer resId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantVO> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<RestaurantVO> searchBySomething(String searchType, String search) {
		String searchCondition = "%" + search + "%";
		if("1".equals(searchType)) {
			return dao.selectAllByResId(searchCondition);
		} else {
			return dao.selectAllByResName(searchCondition);			
		}
	}

}
