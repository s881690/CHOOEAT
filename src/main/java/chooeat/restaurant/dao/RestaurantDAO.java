
package chooeat.restaurant.dao;

import java.io.InputStream;
import java.util.List;

import chooeat.restaurant.model.vo.ProdVO;
import chooeat.restaurant.model.vo.ReservationVO;
import chooeat.restaurant.model.vo.RestaurantVO;

public interface RestaurantDAO {
	int restaurantregister(List<String> values);		
	List<RestaurantVO> searchrestaurantbyname(String searchString);	
	List<RestaurantVO> login(String account,String password);	
	List<Object> findfollow(String resAcc);	
	List<ReservationVO> restaurantfindreservation(String resAcc);	
	List<Object> findrestype(String resAcc);	
	List<Object> findprod(String resAcc);	
	List<Object> findcomment(String resAcc);	
	List<Object> findmyself(String resAcc);	
	List<ProdVO> findpprod(String resAcc);
	List<Object> findaccount(String resAcc);
	int restaurantuploadprod(String key, String prodname, String prodprice, String prodnumber, String prodruler,
			String proddiscribe, String prodstatus, InputStream prodimageStream);
	int restaurantdeleteprod(String prodName, String restaurantId);
	int restaurantdeletefollow(String accName, String restaurantId);
	int restaurantforgetpassword(String account, String mail);
	int restaurantforgetpasswordupdatepassword(int b ,String account);
	int restaurantdeletereservation(String reservationId, String restaurantId);
	List<Object> restauranthomepagemyself(String resAcc);
	int restauranthomepageupdatebasic(String resAcc,String resPass, String resName, String resAdd, String resTel, String resEmail,
			String resSeatNumber, String resStartTime, String resEndTime);
	int restaurantuploaddayoff(String restaurantId, String date);
	int restaurantdeletedayoff(String restaurantId);
	int restaurantddeletetype(String restaurantId);
	int restaurantuploadtype(String restaurantId, String date);
	int restaurantuploadintro(String restaurantId, String intro);
	int restaurantuploadimage(String restaurantId, byte[] image);
	
}