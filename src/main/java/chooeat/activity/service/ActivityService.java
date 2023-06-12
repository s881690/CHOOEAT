package chooeat.activity.service;

import java.util.List;
import java.util.Map;

import chooeat.activity.vo.ActivityVO;
import chooeat.activity.vo.SavedActivityVO;


public interface ActivityService {
	
	public List<ActivityVO> all();

	public List<ActivityVO> sellectAll();

	public List<ActivityVO> search(String value);
	
	public ActivityVO findByActivityId(Integer activityId);
	
	public Object establish(Map<String, String> map);
	
	public ActivityVO findEdit(Integer activityId);
	
	public Integer addActivityMember(Integer activityId);
	
	
	
}
