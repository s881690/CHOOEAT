package chooeat.prod.dao.impl;

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

import chooeat.prod.dao.ProdDao;
import chooeat.prod.model.vo.Prod;

@Repository
@Import(DataSourceAutoConfiguration.class)
public class ProdDaoImpl implements ProdDao{
	@Autowired
	private DataSource dataSource;
	@Override
	public int insert(Prod prod) {
		String sql ="insert into PROD values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
		try(
				Connection conn = dataSource.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setInt(1,prod.getProdId());
			pstmt.setInt(2, prod.getRestaurantId());
			pstmt.setString(3, prod.getProdName());
			pstmt.setString(4, prod.getProdText());
			pstmt.setString(5, prod.getProdUserguide());
			pstmt.setInt(6,prod.getProdPrice());
			pstmt.setInt(7, prod.getProdQty());
			pstmt.setInt(8, prod.getProdState());
			pstmt.setByte(9,prod.getProdPic());
			pstmt.setInt(10,prod.getProdCommentNumber());
			pstmt.setInt(11,prod.getProdCommentScore());
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteByprodId(Integer prodId) {
		String sql="delete from PROD_ID where PROD = ?";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, prodId);
			return pstmt.executeUpdate();
		}
		catch(Exception e){
					e.printStackTrace();
					}
		return -1;
	}

	@Override
	public int updateByprodId(Prod prod) {
		String sql ="update PROD "
				+"set"
				+"	RESTAURANT_ID = ?,"
				+"	PROD_NAME = ?,"
				+"	PROD_TEXT = ?,"
				+"	PROD_USERGUIDE = ?,"
				+"	PROD_PRICE = ?,"
				+"	PROD_QTY = ?,"
				+"	PROD_STATE = ?,"
				+"	PROD_PIC = ?,"
				+"	PROD_COMMENT_NUMBER = ?,"
				+"	PRO_DCOMMENT_SCORE = ? "
				+"where PROD_ID = ?";
		try(
			Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1,prod.getProdId());
			pstmt.setInt(2, prod.getRestaurantId());
			pstmt.setString(3, prod.getProdName());
			pstmt.setString(4, prod.getProdText());
			pstmt.setString(5, prod.getProdUserguide());
			pstmt.setInt(6,prod.getProdPrice());
			pstmt.setInt(7, prod.getProdQty());
			pstmt.setInt(8, prod.getProdState());
			pstmt.setByte(9,prod.getProdPic());
			pstmt.setInt(10,prod.getProdCommentNumber());
			pstmt.setInt(11,prod.getProdCommentScore());
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Prod> getByCompositeQuery(String searchText) {
		String sql = "SELECT prod_id,"
				+ "r.restaurant_id,"
				+ "prod_name,"
				+ "prod_text,"
				+ "prod_userguide,"
				+ "prod_price,prod_qty,"
				+ "prod_state,prod_pic,"
				+ "prod_comment_number,"
				+ "prod_comment_score,"
				+ "res_acc,"
				+ "res_name,"
				+ "res_add,"
				+ "GROUP_CONCAT(res_type_name SEPARATOR ' / ') AS category_names "
				+ "FROM "
				+ "((chooeat.prod p JOIN chooeat.RESTAURANT r ON p.RESTAURANT_ID = r.RESTAURANT_ID) "
				+ "JOIN chooeat.RES_TYPE_DETAIL rtd ON p.RESTAURANT_ID = rtd.RESTAURANT_ID) "
				+ "JOIN chooeat.RES_TYPE rt ON rtd.RES_TYPE_ID = rt.RES_TYPE_ID "
				+ "where PROD_NAME LIKE ? "
				+ "GROUP BY p.prod_id, r.RESTAURANT_ID, res_name;";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, "%"+searchText+"%");
			ResultSet rs = pstmt.executeQuery();
			List<Prod> list = new ArrayList<Prod>();
			while(rs.next()) {
				Prod prod = new Prod();
				prod.setProdId(rs.getInt("PROD_ID"));
				prod.setRestaurantId(rs.getInt("RESTAURANT_ID"));
				prod.setProdName(rs.getString("PROD_NAME"));
				prod.setProdText(rs.getString("PROD_TEXT"));
				prod.setProdUserguide(rs.getString("PROD_USERGUIDE"));
				prod.setProdPrice(rs.getInt("PROD_PRICE"));
				prod.setProdQty(rs.getInt("PROD_QTY"));
				prod.setProdState(rs.getInt("PROD_STATE"));
				prod.setProdPic(rs.getByte("PROD_PIC"));
				prod.setProdCommentNumber(rs.getInt("PROD_COMMENT_NUMBER"));
				prod.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
				prod.setResName(rs.getString("RES_NAME"));
				prod.setResAdd(rs.getString("RES_ADD"));
				prod.setResType(rs.getString("category_names"));
				list.add(prod);
				}
			return list;
			} catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<Prod> selectAll() {
		String sql = "SELECT prod_id,"
				+ "r.restaurant_id,"
				+ "prod_name,"
				+ "prod_text,"
				+ "prod_userguide,"
				+ "prod_price,prod_qty,"
				+ "prod_state,prod_pic,"
				+ "prod_comment_number,"
				+ "prod_comment_score,"
				+ "res_acc,"
				+ "res_name,"
				+ "res_add,"
				+ "GROUP_CONCAT(res_type_name SEPARATOR ' / ') AS category_names "
				+ "FROM "
				+ "((chooeat.prod p JOIN chooeat.RESTAURANT r ON p.RESTAURANT_ID = r.RESTAURANT_ID) "
				+ "JOIN chooeat.RES_TYPE_DETAIL rtd ON p.RESTAURANT_ID = rtd.RESTAURANT_ID) "
				+ "JOIN chooeat.RES_TYPE rt ON rtd.RES_TYPE_ID = rt.RES_TYPE_ID "
				+ "GROUP BY p.prod_id, r.RESTAURANT_ID, res_name;";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		){
			List<Prod> list = new ArrayList<Prod>();
			while(rs.next()) {
				Prod prod = new Prod();
				prod.setProdId(rs.getInt("PROD_ID"));
				prod.setRestaurantId(rs.getInt("RESTAURANT_ID"));
				prod.setProdName(rs.getString("PROD_NAME"));
				prod.setProdText(rs.getString("PROD_TEXT"));
				prod.setProdUserguide(rs.getString("PROD_USERGUIDE"));
				prod.setProdPrice(rs.getInt("PROD_PRICE"));
				prod.setProdQty(rs.getInt("PROD_QTY"));
				prod.setProdState(rs.getInt("PROD_STATE"));
				prod.setProdPic(rs.getByte("PROD_PIC"));
				prod.setProdCommentNumber(rs.getInt("PROD_COMMENT_NUMBER"));
				prod.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
				prod.setResName(rs.getString("RES_NAME"));
				prod.setResAdd(rs.getString("RES_ADD"));
				prod.setResType(rs.getString("category_names"));
				list.add(prod);
				}
			return list;
			} catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public long getTotal() {
		 long totalCount = 0;
		 String sql = "SELECT COUNT(*) AS total FROM PROD";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		){
			List<Prod> list = new ArrayList<Prod>();
			while(rs.next()) {
				totalCount = rs.getLong("total");
				}
			return 2;
			} catch(Exception e){
				e.printStackTrace();
			}
		return totalCount;
	}
	
	@Override
	public Prod selectByProdId(Integer prodId) {
		String sql = "SELECT prod_id,"
				+ "r.restaurant_id,"
				+ "prod_name,"
				+ "prod_text,"
				+ "prod_userguide,"
				+ "prod_price,prod_qty,"
				+ "prod_state,prod_pic,"
				+ "prod_comment_number,"
				+ "prod_comment_score,"
				+ "res_acc,"
				+ "res_name,"
				+ "res_add,"
				+ "GROUP_CONCAT(res_type_name SEPARATOR ' / ') AS category_names "
				+ "FROM "
				+ "((chooeat.prod p JOIN chooeat.RESTAURANT r ON p.RESTAURANT_ID = r.RESTAURANT_ID) "
				+ "JOIN chooeat.RES_TYPE_DETAIL rtd ON p.RESTAURANT_ID = rtd.RESTAURANT_ID) "
				+ "JOIN chooeat.RES_TYPE rt ON rtd.RES_TYPE_ID = rt.RES_TYPE_ID "
				+ "where PROD_Id = ? "
				+ "GROUP BY p.prod_id, r.RESTAURANT_ID, res_name;";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, prodId);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Prod prod = new Prod();
					prod.setProdId(rs.getInt("PROD_ID"));
					prod.setRestaurantId(rs.getInt("RESTAURANT_ID"));
					prod.setProdName(rs.getString("PROD_NAME"));
					prod.setProdText(rs.getString("PROD_TEXT"));
					prod.setProdUserguide(rs.getString("PROD_USERGUIDE"));
					prod.setProdPrice(rs.getInt("PROD_PRICE"));
					prod.setProdQty(rs.getInt("PROD_QTY"));
					prod.setProdState(rs.getInt("PROD_STATE"));
					prod.setProdPic(rs.getByte("PROD_PIC"));
					prod.setProdCommentNumber(rs.getInt("PROD_COMMENT_NUMBER"));
					prod.setProdCommentScore(rs.getInt("PROD_COMMENT_SCORE"));
					prod.setResName(rs.getString("RES_NAME"));
					prod.setResAdd(rs.getString("RES_ADD"));
					prod.setResType(rs.getString("category_names"));
					return prod;
				}
			}
		}
		catch(Exception e){
				e.printStackTrace();
		}
		return null;
	}
}
