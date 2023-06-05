package chooeat.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.activity.vo.ActivityMemberVO;



public interface ActivityMemberRepository extends JpaRepository<ActivityMemberVO, Integer> {
	List<ActivityMemberVO> findByActivityId(Integer activityId);
	void deleteByAccId(Integer accId);
	ActivityMemberVO findByAccIdAndActivityId(Integer accId,Integer activityId);
}
