package chooeat.admin.web.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.admin.core.util.CommonUtil;
import chooeat.admin.web.admin.service.AdminService;

@WebServlet("/admin/getAllAdmin")
public class AdminGetAllAdmin extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdminService SERVICE;
	
	@Autowired
	private CommonUtil commonUtil;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");

		res.setContentType("application/json; charset=utf-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		commonUtil.writePojo2Json(res, SERVICE.findAll());
	}
}
