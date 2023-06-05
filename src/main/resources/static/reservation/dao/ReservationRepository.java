package chooeat.reservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chooeat.reservation.model.ReservationVO;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationVO, Integer> {
	

}
