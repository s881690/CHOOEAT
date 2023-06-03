package chooeat.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.prod.dao.ProdDao;
import chooeat.prod.dao.impl.ProdDaoImpl;
import chooeat.prod.model.vo.Prod;
@WebServlet("/prod/prod/details")
public class ProdDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProdDaoImpl prodDaoImpl;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設置接收格式
		req.setCharacterEncoding("utf-8");
		// 設置跨域
		res.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		res.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		res.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式	
		res.setContentType("application/json; charset=utf-8");
		String prodIdS = req.getParameter("id");
//		System.out.println(prodIdS);
		int prodId = Integer.parseInt(prodIdS);
	
		Prod prod1 = prodDaoImpl.selectByProdId(prodId);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(prod1);
		PrintWriter out = res.getWriter();
	    out.print(jsonStr);
	    out.flush();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
