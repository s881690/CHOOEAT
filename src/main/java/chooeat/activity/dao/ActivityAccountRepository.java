package chooeat.activity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.activity.vo.AccountVO;


public interface ActivityAccountRepository extends JpaRepository<AccountVO, Integer> {

}
