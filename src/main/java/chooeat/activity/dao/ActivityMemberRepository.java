package chooeat.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chooeat.activity.vo.ActivityMemberVO;




public interface ActivityMemberRepository extends JpaRepository<ActivityMemberVO, Integer> {
	List<ActivityMemberVO> findByActivityId(Integer activityId);
	
	void deleteByAccId(Integer accId);
	
	ActivityMemberVO findByAccIdAndActivityId(Integer accId,Integer activityId);
	
//	@Query("SELECT COUNT(am.accId) FROM ActivityMemberVO am WHERE activityId = :activityId ")
//	Integer findByActivityId1(@Param(value = "activityId") Integer activityId);
	
	@Query("SELECT am FROM ActivityMemberVO am JOIN am.accountVO account WHERE account.accName LIKE %:accName% AND am.activityId = :activityId")
	List<ActivityMemberVO> findByAccNameAndActivityId(@Param(value = "accName") String accName, @Param(value = "activityId") Integer activityId);

	Integer countByActivityId(Integer activityId);
}

