package chooeat.admin.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.order.pojo.AdminOrderVO;
import chooeat.admin.web.order.service.OrderService;

@RestController
@RequestMapping("/adminSearchOrder")
public class AdminSearchOrder {
	
	@Autowired
	private OrderService SERVICE;
	
	@GetMapping("/selectAll")
	public List<AdminOrderVO> findAll(){
		return SERVICE.selectAll();
	}

}
