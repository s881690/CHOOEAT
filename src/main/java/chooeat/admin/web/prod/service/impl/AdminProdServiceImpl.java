package chooeat.admin.web.prod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chooeat.admin.web.prod.dao.ProdRepository;
import chooeat.admin.web.prod.pojo.ProdVO;
import chooeat.admin.web.prod.service.ProdService;

@Service
public class AdminProdServiceImpl implements ProdService{

	@Autowired
	private ProdRepository prodRepository;
	
	@Override
	public List<ProdVO> selectAll() {
		List<ProdVO> prodList = prodRepository.findAll();
		return prodList;
	}

	@Override
	public ProdVO findByProdId(Integer prodId) {
		// TODO Auto-generated method stub
		return null;
	}

}
