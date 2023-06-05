package chooeat.admin.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chooeat.admin.web.prod.pojo.ProdVO;
import chooeat.admin.web.prod.service.ProdService;

@RestController
@RequestMapping("/adminSearchProd")
public class AdminSearchProd {

	@Autowired
	private ProdService SERVICE;
	
	@GetMapping("/selectAll")
	public List<ProdVO> findAll(){
		return SERVICE.selectAll();		
	}
	
	
	
	
}
