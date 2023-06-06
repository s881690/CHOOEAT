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

	
	
}
