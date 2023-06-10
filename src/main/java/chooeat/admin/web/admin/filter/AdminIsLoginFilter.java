package chooeat.admin.web.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
//@WebFilter(filterName = "", urlPatterns = { "/admin" })
//@Order(value = 1)
public class AdminIsLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		
		String uri = new String(req.getRequestURI());
		
		if (uri.contains("/login")) {
	        // 如果是 Login Controller，直接執行後續的過濾器或控制器
	        chain.doFilter(request, response);
	        return;
	    }
		
		
//		int adminId = (Integer)session.getAttribute("adminId");
		
//		System.out.println(adminId);
		System.out.println("filter執行");

		

	}

}
