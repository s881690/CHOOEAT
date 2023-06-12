package chooeat.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import chooeat.activity.vo.ActivityVO;



public interface ActivityRepository extends JpaRepository<ActivityVO, Integer> {
	List<ActivityVO> findByActivityNameContaining(String activityName);
	ActivityVO findByActivityId(Integer activityId);
//	List<ActivityVO> findByActivityId(Integer activityId);
	 
	@Query("SELECT a FROM ActivityVO a WHERE (a.activityStatus != 2 AND DATE(a.regesterationEndingTime) > CURRENT_DATE) OR (a.activityStatus != 2 AND activityNumber < maxNumber  AND DATE(a.regesterationEndingTime) > CURRENT_DATE)")
	List<ActivityVO> findActivitys();

}
