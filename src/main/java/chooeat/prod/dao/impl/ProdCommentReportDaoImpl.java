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

import chooeat.prod.dao.ProdCommentReportDao;
import chooeat.prod.model.vo.ProdCommentReport;
@Repository
@Import(DataSourceAutoConfiguration.class)
public class ProdCommentReportDaoImpl implements ProdCommentReportDao{
	@Autowired
	private DataSource dataSource;
	@Override
	public int updateByprodCommentReportId(ProdCommentReport orderDetailId) {
		String sql ="update PROD_COMMENT_REPORT "
				+"set"
				+"PROD_COMMENT_REPORT_ID = ?,"
				+"ACC_ID = ?,"
				+"PROD_COMMENT_REPORT_REASON = ?,"
				+"where ORDER_DETAIL_ID = ?";
		try(
			Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1,orderDetailId.getProdCommentReportId());
			pstmt.setInt(2, orderDetailId.getAccId());
			pstmt.setInt(3, orderDetailId.getOrderDetailId());
			pstmt.setString(4, orderDetailId.getProdCommentReportReason());
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<ProdCommentReport> selectAll() {
		String sql="select * from PROD_COMMENT_REPORT";
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		){
			List<ProdCommentReport> list = new ArrayList<ProdCommentReport>();
			while(rs.next()) {
				ProdCommentReport prodCommentReport = new ProdCommentReport();
				prodCommentReport.setProdCommentReportId(rs.getInt("PROD_COMMENT_REPORT_ID"));
				prodCommentReport.setAccId(rs.getInt("ACC_ID"));
				prodCommentReport.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
				prodCommentReport.setProdCommentReportReason(rs.getString("PROD_COMMENT_REPORT_REASON"));
				list.add(prodCommentReport);
				}
			return list;
			} catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}

}
