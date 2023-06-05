package chooeat.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.activity.vo.ActivityVO;



public interface ActivityRepository extends JpaRepository<ActivityVO, Integer> {
	List<ActivityVO> findByActivityNameContaining(String activityName);
	ActivityVO findByActivityId(Integer activityId);
//	List<ActivityVO> findByActivityId(Integer activityId);

}
