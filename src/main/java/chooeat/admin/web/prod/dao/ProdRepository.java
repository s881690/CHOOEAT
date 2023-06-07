package chooeat.admin.web.prod.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.admin.web.prod.pojo.ProdVO;

public interface ProdRepository extends JpaRepository<ProdVO, Integer>{

	
}
