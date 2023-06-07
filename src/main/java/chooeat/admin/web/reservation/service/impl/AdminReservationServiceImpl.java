package chooeat.admin.web.reservation.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.reservation.dao.AdminReservationRepository;
import chooeat.admin.web.reservation.pojo.AdminReservationVO;
import chooeat.admin.web.reservation.service.ReservationService;

@Service
public class AdminReservationServiceImpl implements ReservationService {

	@Autowired
	private AdminReservationRepository adminReservationRepository;

	@Override
	public List<AdminReservationVO> selectAll() {
		List<AdminReservationVO> reservationList = adminReservationRepository.findAll();
		
		Iterator<AdminReservationVO> iterator = reservationList.iterator();

		while (iterator.hasNext()) {
		    AdminReservationVO i = iterator.next();
		    if (i.getRestaurantCommentDatetime() == null) {
		        iterator.remove();
		    }
		}
		return reservationList;
	}
	
	
}
