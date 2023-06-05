package chooeat.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import chooeat.activity.vo.SavedActivityVO;


public interface SavedActivityRepository extends JpaRepository<SavedActivityVO, Integer> {
	List<SavedActivityVO> findByActivityId(Integer activityId);
	List<SavedActivityVO> findByAccId(Integer accId);
	void deleteByAccIdAndActivityId(Integer accId, Integer activityId);
}
