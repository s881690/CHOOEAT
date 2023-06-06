package chooeat.admin.web.reservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.admin.web.reservation.pojo.AdminReservationVO;

public interface AdminReservationRepository extends JpaRepository<AdminReservationVO, Integer>{

	
	
}
