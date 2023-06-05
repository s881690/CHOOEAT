package chooeat.admin.web.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.admin.core.pojo.Core;
import chooeat.admin.core.util.CommonUtil;
import chooeat.admin.web.restaurant.pojo.RestaurantVO;
import chooeat.admin.web.restaurant.service.RestaurantService;

@WebServlet("/admin/searchRestaurant")
public class AdminSearchRestaurant extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RestaurantService SERVICE;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");

		res.setContentType("application/json; charset=utf-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String searchType = req.getParameter("searchType");
		String search = req.getParameter("search");
		
		List<RestaurantVO> resList = new ArrayList<>();
		
		if("0".equals(searchType) && "".equals(search)) {
			resList = SERVICE.findAll();
		} else {
			resList = SERVICE.searchBySomething(searchType, search);
		}
		
		if(resList.size() == 0) {
			Core core = new Core();
			core.setMessage("查無資料");
			core.setSuccessful(false);
			commonUtil.writePojo2Json(res, core);
			return;
		}
		
		commonUtil.writePojo2Json(res, resList);
	}
}
