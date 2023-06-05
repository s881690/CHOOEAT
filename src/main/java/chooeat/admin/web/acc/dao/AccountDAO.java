package chooeat.admin.web.acc.dao;

import java.util.List;

import chooeat.admin.core.dao.CoreDao;
import chooeat.admin.web.acc.pojo.AccountVO;

public interface AccountDAO extends CoreDao<AccountVO, Integer>{
	
	List<AccountVO> selectAllByAccId (String accId);
	
	List<AccountVO> selectAllByAccAcc (String accAcc);
	
	List<AccountVO> selectAllByAccName (String accName);
	
}
