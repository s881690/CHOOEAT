package chooeat.admin.web.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.admin.web.order.pojo.AdminOrderVO;

public interface OrderRepository extends JpaRepository<AdminOrderVO, Integer>{

}
