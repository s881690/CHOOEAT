package chooeat.admin.web.admin.controller;

import java.util.Collections;
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
	public List<AdminOrderVO> findAll(Integer searchType, String search){
		
		if(searchType == 0) {
			return SERVICE.selectAll();			
		} else if (searchType == 1 || searchType == 2) {
			try {
				int id = Integer.parseInt(search);
				return SERVICE.searchBySomethingId(searchType, id);
			} catch (NumberFormatException e) {
				return Collections.emptyList();
			}
		} else if (searchType == 3 || searchType == 4) {
			return SERVICE.searchByAcc(searchType, search);
		} else {
			return Collections.emptyList();
		}
	}

}
