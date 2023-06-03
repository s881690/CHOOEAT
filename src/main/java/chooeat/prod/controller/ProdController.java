package chooeat.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import chooeat.prod.dao.ProdDao;
import chooeat.prod.dao.impl.ProdDaoImpl;
import chooeat.prod.model.vo.Prod;
import chooeat.prod.service.ProdService;

@WebServlet("/prod/Prod/getall")
public class ProdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProdService prodService;
	@Autowired
	private ProdDaoImpl prodDaoImpl;

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
		String action = req.getParameter("action");
		// 回傳搜尋餐券
		if ("getByCompositeQuery".equals(action)) {
			List<Prod> list = null;
			String search_text = req.getParameter("search").toString();
			HttpSession session = req.getSession();
			session.setAttribute("search_text", search_text);
			list = prodDaoImpl.getByCompositeQuery(search_text);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(list);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.flush();
		} else if ("sortBy".equals(action)) {
			String sortParam = req.getParameter("sort");
			HttpSession session = req.getSession();
			String search_text = (String) session.getAttribute("search_text");
			List<Prod> list = null;
			list = prodDaoImpl.selectAll();
			if (search_text != null && !search_text.isEmpty()) {
				list = prodDaoImpl.getByCompositeQuery(search_text);
			}
			List<Prod> sortedProducts = prodService.getSortedProducts(list, sortParam);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(sortedProducts);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.flush();
			session.removeAttribute("search_text");
		} else {
			// 回傳全部餐券
			List<Prod> list = null;
			list = prodDaoImpl.selectAll();
			Gson gson = new Gson();
			String jsonStr = gson.toJson(list);
			res.getWriter().write(jsonStr);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}
}
