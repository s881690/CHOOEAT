package chooeat.admin.web.reservation.service;

import java.util.List;

import chooeat.admin.web.reservation.pojo.AdminReservationVO;

public interface ReservationService {
	
	List<AdminReservationVO> selectAll();

}
