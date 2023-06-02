package chooeat.reservation.dao;

import java.sql.Date;
import java.util.List;

import chooeat.reservation.model.EmailInfo;
import chooeat.reservation.model.HourlySeat;
import chooeat.reservation.model.ReservationVO;
import chooeat.reservation.model.Result;

public interface ReservationDao {

	Integer insertReservation(ReservationVO reservationVO);

	Boolean updateReservation(ReservationVO reservationVO,int reservationId);

	Boolean deleteReservation(int reservationId);

	List<HourlySeat> selectall(Integer restaurant_id, Date date);

	List<ReservationVO> selectReservation(int memberId, int restaurantId, Date date);

	List<Integer> reservedList(int memberId, int restaurantId, Date date);

	List<EmailInfo> getEmailInfos(int memberId, Integer reservation_id);
	
	Boolean insertComment(int reservationId, String comment);
	
	List<Result> reservationData(int reservationId);
	
	List<Result> selectallReservation(int memberId);

}
