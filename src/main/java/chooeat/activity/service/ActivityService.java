package chooeat.activity.service;

import java.util.List;
import java.util.Map;

import chooeat.activity.vo.ActivityVO;
import chooeat.activity.vo.SavedActivityVO;


public interface ActivityService {


	public List<ActivityVO> sellectAll();

	public List<ActivityVO> search(String value);
	
	public ActivityVO findByActivityId(Integer activityId);

	public String get3Activity();

	public void update(ActivityVO activityVO);

	public void apply(ActivityVO activityVO);

	public void like(SavedActivityVO savedActivityVO);
	
	public void dislike(Integer activityId);
	
	public List<SavedActivityVO> getlike(Integer accId);

	public void getMembersbyActivityName(String activityName);
	
	public boolean selectByAccIdandActivityId(Integer accId,  Integer activityId);
	
	public Object establish(Map<String, String> map);
	
	public ActivityVO findEdit(Integer activityId);
	
	
	
}
