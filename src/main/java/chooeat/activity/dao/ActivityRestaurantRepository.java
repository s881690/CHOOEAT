package chooeat.activity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.activity.vo.ActivityRestaurantVO;


public interface ActivityRestaurantRepository extends JpaRepository<ActivityRestaurantVO, Integer> {
}
