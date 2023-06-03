package chooeat.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chooeat.activity.dao.SavedActivityRepository;
import chooeat.activity.service.SavedActivityService;
import chooeat.activity.vo.SavedActivityVO;


@Component
public class SavedActivityServiceImpl implements SavedActivityService{
	
	@Autowired
	SavedActivityRepository savedActivityRepository;

	@Override
	public SavedActivityVO like(SavedActivityVO savedActivityVO) {
		return savedActivityRepository.save(savedActivityVO);
		
	}

	@Override
	public String dislike(SavedActivityVO savedActivityVO) {
		 savedActivityRepository.deleteByAccIdAndActivityId(savedActivityVO.getAccId(), savedActivityVO.getActivityId());
		 return "true";
	}

	@Override
	public List<SavedActivityVO> getlike(Integer accId) {
		List<SavedActivityVO> list = savedActivityRepository.findByAccId(accId);
		return list;
	}



	
}
