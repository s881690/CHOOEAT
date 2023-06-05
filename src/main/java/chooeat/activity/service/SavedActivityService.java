package chooeat.activity.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import chooeat.activity.vo.SavedActivityVO;


public interface SavedActivityService {

	public SavedActivityVO like(SavedActivityVO savedActivityVO);
	public String dislike(@RequestBody SavedActivityVO savedActivityVO);
	public List<SavedActivityVO> getlike(@RequestParam(value = "accId") Integer accId);
}
