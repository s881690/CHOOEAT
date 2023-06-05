package chooeat.admin.web.restaurant.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

import chooeat.admin.web.restaurant.dao.RestaurantDAO;
import chooeat.admin.web.restaurant.pojo.RestaurantVO;

@Repository
@Import(DataSourceAutoConfiguration.class)
public class AdminRestaurantDAOImpl implements RestaurantDAO{
	
	@Autowired
	private DataSource dataSource;

	@Override
	public int register(List<String> values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByRestaurantId(RestaurantVO RestaurantVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RestaurantVO> searchRestaurants(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantVO> login(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findfollow(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findrestype(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findprod(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findcomment(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findmyself(String resAcc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<RestaurantVO> selectAll() {		
		final String sql = "SELECT r.*, rt.res_type_id, rt.res_type_name\r\n"
				+ "FROM restaurant r\r\n"
				+ "JOIN (\r\n"
				+ "    SELECT restaurant_id, MIN(res_type_id) AS min_res_type_id\r\n"
				+ "    FROM res_type_detail\r\n"
				+ "    GROUP BY restaurant_id\r\n"
				+ ") min_res_type ON r.restaurant_id = min_res_type.restaurant_id\r\n"
				+ "JOIN res_type rt ON min_res_type.min_res_type_id = rt.res_type_id;";
		
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

			List<RestaurantVO> list = new ArrayList<RestaurantVO>();

			try(ResultSet rs = stmt.executeQuery();){
				while (rs.next()) {
					RestaurantVO restaurantVO = new RestaurantVO();
					restaurantVO.setRestaurantId(rs.getInt("restaurant_id"));
		            restaurantVO.setResAcc(rs.getString("res_acc"));
		            restaurantVO.setResPass(rs.getString("res_pass"));
		            restaurantVO.setResState(rs.getInt("res_state"));
		            restaurantVO.setResName(rs.getString("res_name"));
		            restaurantVO.setResAdd(rs.getString("res_add"));
		            restaurantVO.setResTel(rs.getString("res_tel"));
		            restaurantVO.setResEmail(rs.getString("res_email"));
		            restaurantVO.setResWeb(rs.getString("res_web"));
		            restaurantVO.setResTimestamp(rs.getTimestamp("res_timestamp"));
		            restaurantVO.setResStartTime(rs.getTime("res_start_time"));
		            restaurantVO.setResEndTime(rs.getTime("res_end_time"));
		            restaurantVO.setResTexId(rs.getString("res_tex_id"));
		            restaurantVO.setResOwnerName(rs.getString("res_owner_name"));
		            restaurantVO.setResSeatNumber(rs.getInt("res_seat_number"));
		            restaurantVO.setResIntro(rs.getString("res_intro"));
		            restaurantVO.setSingleMeal(rs.getBoolean("single_meal"));
		            restaurantVO.setResTotalScore(rs.getInt("res_total_score"));
		            restaurantVO.setResTotalNumber(rs.getInt("res_total_number"));
		            restaurantVO.setResMaxNum(rs.getInt("res_max_num"));
		            restaurantVO.setResType(rs.getString("res_type_name"));
					list.add(restaurantVO);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RestaurantVO> selectAllByResId(String resId) {
		final String sql = "SELECT r.*, rt.res_type_id, rt.res_type_name\r\n"
				+ "FROM restaurant r\r\n"
				+ "JOIN (\r\n"
				+ "    SELECT restaurant_id, MIN(res_type_id) AS min_res_type_id\r\n"
				+ "    FROM res_type_detail\r\n"
				+ "    GROUP BY restaurant_id\r\n"
				+ ") min_res_type ON r.restaurant_id = min_res_type.restaurant_id\r\n"
				+ "JOIN res_type rt ON min_res_type.min_res_type_id = rt.res_type_id\r\n"
				+ "WHERE r.restaurant_id LIKE ?;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, resId);
			ResultSet rs = stmt.executeQuery();

			List<RestaurantVO> list = new ArrayList<RestaurantVO>();

			while (rs.next()) {
				RestaurantVO restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantId(rs.getInt("restaurant_id"));
	            restaurantVO.setResAcc(rs.getString("res_acc"));
	            restaurantVO.setResPass(rs.getString("res_pass"));
	            restaurantVO.setResState(rs.getInt("res_state"));
	            restaurantVO.setResName(rs.getString("res_name"));
	            restaurantVO.setResAdd(rs.getString("res_add"));
	            restaurantVO.setResTel(rs.getString("res_tel"));
	            restaurantVO.setResEmail(rs.getString("res_email"));
	            restaurantVO.setResWeb(rs.getString("res_web"));
	            restaurantVO.setResTimestamp(rs.getTimestamp("res_timestamp"));
	            restaurantVO.setResStartTime(rs.getTime("res_start_time"));
	            restaurantVO.setResEndTime(rs.getTime("res_end_time"));
	            restaurantVO.setResTexId(rs.getString("res_tex_id"));
	            restaurantVO.setResOwnerName(rs.getString("res_owner_name"));
	            restaurantVO.setResSeatNumber(rs.getInt("res_seat_number"));
	            restaurantVO.setResIntro(rs.getString("res_intro"));
	            restaurantVO.setSingleMeal(rs.getBoolean("single_meal"));
	            restaurantVO.setResTotalScore(rs.getInt("res_total_score"));
	            restaurantVO.setResTotalNumber(rs.getInt("res_total_number"));
	            restaurantVO.setResMaxNum(rs.getInt("res_max_num"));
	            restaurantVO.setResType(rs.getString("res_type_name"));
				list.add(restaurantVO);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<RestaurantVO> selectAllByResName(String resName) {
		final String sql = "SELECT r.*, rt.res_type_id, rt.res_type_name\r\n"
				+ "FROM restaurant r\r\n"
				+ "JOIN (\r\n"
				+ "    SELECT restaurant_id, MIN(res_type_id) AS min_res_type_id\r\n"
				+ "    FROM res_type_detail\r\n"
				+ "    GROUP BY restaurant_id\r\n"
				+ ") min_res_type ON r.restaurant_id = min_res_type.restaurant_id\r\n"
				+ "JOIN res_type rt ON min_res_type.min_res_type_id = rt.res_type_id\r\n"
				+ "WHERE r.res_name LIKE ?;";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, resName);
			ResultSet rs = stmt.executeQuery();

			List<RestaurantVO> list = new ArrayList<RestaurantVO>();

			while (rs.next()) {
				RestaurantVO restaurantVO = new RestaurantVO();
				restaurantVO.setRestaurantId(rs.getInt("restaurant_id"));
	            restaurantVO.setResAcc(rs.getString("res_acc"));
	            restaurantVO.setResPass(rs.getString("res_pass"));
	            restaurantVO.setResState(rs.getInt("res_state"));
	            restaurantVO.setResName(rs.getString("res_name"));
	            restaurantVO.setResAdd(rs.getString("res_add"));
	            restaurantVO.setResTel(rs.getString("res_tel"));
	            restaurantVO.setResEmail(rs.getString("res_email"));
	            restaurantVO.setResWeb(rs.getString("res_web"));
	            restaurantVO.setResTimestamp(rs.getTimestamp("res_timestamp"));
	            restaurantVO.setResStartTime(rs.getTime("res_start_time"));
	            restaurantVO.setResEndTime(rs.getTime("res_end_time"));
	            restaurantVO.setResTexId(rs.getString("res_tex_id"));
	            restaurantVO.setResOwnerName(rs.getString("res_owner_name"));
	            restaurantVO.setResSeatNumber(rs.getInt("res_seat_number"));
	            restaurantVO.setResIntro(rs.getString("res_intro"));
	            restaurantVO.setSingleMeal(rs.getBoolean("single_meal"));
	            restaurantVO.setResTotalScore(rs.getInt("res_total_score"));
	            restaurantVO.setResTotalNumber(rs.getInt("res_total_number"));
	            restaurantVO.setResMaxNum(rs.getInt("res_max_num"));
	            restaurantVO.setResType(rs.getString("res_type_name"));
				list.add(restaurantVO);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
