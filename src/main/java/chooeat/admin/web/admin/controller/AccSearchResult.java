package chooeat.admin.web.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import chooeat.account.service.AccountService;
import chooeat.admin.core.util.CommonUtil;
import chooeat.admin.web.acc.pojo.AccountVO;

@WebServlet("/admin/adminSearchAccResult")
public class AccSearchResult extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountService SERVICE;
	
	@Autowired
	private CommonUtil COMMONUTIL;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Allow-Credentials", "true");

		res.setContentType("application/json; charset=utf-8");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		AccountVO account = COMMONUTIL.json2Pojo(req, AccountVO.class);
		
		if(account == null) {
			account = new AccountVO();
			account.setMessage("無此會員資訊");
			account.setSuccessful(false);
			COMMONUTIL.writePojo2Json(res, account);
			return;
		}
		
		

	}

}
