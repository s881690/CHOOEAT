package chooeat.admin.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.reservation.service.ReservationService;
import chooeat.admin.web.reservation.pojo.AdminReservationVO;

@RestController
@RequestMapping("/adminSearchReservation")
public class AdminSearchReservation {
	
	@Autowired
	private ReservationService SERVICE;
	
	@GetMapping("/selectAll")
	public List<AdminReservationVO> findAll(){
		return SERVICE.selectAll();
	}

}
