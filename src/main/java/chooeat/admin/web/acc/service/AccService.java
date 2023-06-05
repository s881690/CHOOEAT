package chooeat.admin.web.acc.service;

import java.util.List;

import chooeat.admin.web.acc.pojo.AccountVO;

public interface AccService {

	AccountVO edit();
	
	List<AccountVO> findAll();
	
	List<AccountVO> searchBySomething(String searchType, String searchCondition);
	
}
