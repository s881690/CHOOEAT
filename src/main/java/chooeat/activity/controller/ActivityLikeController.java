package chooeat.activity.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import chooeat.activity.dao.SavedActivityRepository;
import chooeat.activity.service.SavedActivityService;
import chooeat.activity.vo.SavedActivityVO;

@RestController
@RequestMapping("/activity")
public class ActivityLikeController {
	@Autowired
	SavedActivityRepository savedActivityRepository;
	@Autowired
	SavedActivityService savedActivityService;
	
	// 收藏按鈕
	@PostMapping("/like")
	@ResponseBody
	public SavedActivityVO like(@RequestBody SavedActivityVO savedActivityVO) {
		return savedActivityService.like(savedActivityVO);
//		activityService.like(savedActivityVO);
	}

	// 取消收藏
	@Transactional
	@DeleteMapping(value = "/dislike")
	public String dislike(@RequestBody SavedActivityVO savedActivityVO) {
//		System.out.println(savedActivityVO);
//		savedActivityRepository.deleteByAccIdAndActivityId(savedActivityVO.getAccId(), savedActivityVO.getActivityId());
		return savedActivityService.dislike(savedActivityVO);
	}

	@GetMapping("/getlike")
	@ResponseBody
	public List<SavedActivityVO> getlike(@RequestParam(value = "accId") Integer accId) {
//		System.out.println(accId);
		return savedActivityService.getlike(accId);
	}
}
