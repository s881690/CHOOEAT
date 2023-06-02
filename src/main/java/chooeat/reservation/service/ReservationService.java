package chooeat.reservation.service;

import java.util.List;
import java.util.Optional;

import chooeat.reservation.model.HourlySeat;
import chooeat.reservation.model.ReservationVO;
import chooeat.reservation.model.RestaurantVO;
import chooeat.reservation.model.Result;

public interface ReservationService {
	Boolean isDayOff(Integer restaurant_id, String date);
	
	List<HourlySeat> findHourlySeats(int restaurantId, String date);
	
	List<Integer> reservedData(int memberId, int restaurantId, String date);
	
	Optional<RestaurantVO> selectResInfo(int restaurantId);
	
	
	Integer reservation(ReservationVO reservationVO);
	
	Boolean reservationUpdate(ReservationVO reservationVO, int reservationId);
	
	Boolean reservationDelete(int reservationId);
	
	
	 void sendMail(int memberId, int reservationId);
	 
	 
	 List<Result> selectallReservation(int memberId);
	 
	 Boolean isSavedComment(int reservationId, String comment);
	 
	 
	 List<Result> reservationInfo(int reservationId);
	 
	 String memberName(int memberId);
	 

	
	

}
