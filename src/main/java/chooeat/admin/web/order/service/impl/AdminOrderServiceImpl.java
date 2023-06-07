package chooeat.admin.web.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.order.dao.OrderRepository;
import chooeat.admin.web.order.pojo.AdminOrderVO;
import chooeat.admin.web.order.service.OrderService;

@Service
public class AdminOrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<AdminOrderVO> selectAll() {
		List<AdminOrderVO> orderList = orderRepository.findAll();
		return orderList;
	}

	@Override
	public AdminOrderVO findByProdId(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminOrderVO> searchBySomethingId(Integer searchType, Integer id) {
		
		String stringId = "%" + String.valueOf(id) + "%";
		
		if(searchType == 1) {
			return orderRepository.findByOrderIdLike(stringId);
		} else if (searchType == 2) {
			return orderRepository.findByAccIdLike(stringId);
		} else {
			return null;
		}
	}

	@Override
	public List<AdminOrderVO> searchByAcc(Integer searchType, String search) {
		
		if(searchType == 3) {
			return orderRepository.findByAccAccLike(search);
		} else if (searchType == 4) {
			return orderRepository.findByAccNameLike(search);
		} else {
			return null;
		}
	}
	
}
