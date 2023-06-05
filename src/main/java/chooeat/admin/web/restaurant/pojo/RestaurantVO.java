package chooeat.admin.web.restaurant.pojo;

import java.sql.Time;
import java.sql.Timestamp;

import chooeat.admin.core.pojo.Core;

public class RestaurantVO extends Core{
	private static final long serialVersionUID = 1L;
	
	private Integer restaurantId;
	private String resAcc;
	private String resPass;
	private Integer resState;
	private String resName;
	private String resAdd;
	private String resTel;
	private String resEmail;
	private String resWeb;
	private Timestamp resTimestamp;
	private Time resStartTime;
	private Time resEndTime;
	private String resTexId;
	private String resOwnerName;
	private Integer resSeatNumber;
	private String resIntro;
	private Boolean singleMeal;
	private Integer resTotalScore;
	private Integer resTotalNumber;
	private Integer resMaxNum;
	private String resType;
	
	public RestaurantVO(Integer restaurantId, String resAcc, String resPass, Integer resState, String resName,
			String resAdd, String resTel, String resEmail, String resWeb, Timestamp resTimestamp, Time resStartTime,
			Time resEndTime, String resTexId, String resOwnerName, Integer resSeatNumber, String resIntro,
			Boolean singleMeal, Integer resTotalScore, Integer resTotalNumber, Integer resMaxNum, String resType) {
		this.restaurantId = restaurantId;
		this.resAcc = resAcc;
		this.resPass = resPass;
		this.resState = resState;
		this.resName = resName;
		this.resAdd = resAdd;
		this.resTel = resTel;
		this.resEmail = resEmail;
		this.resWeb = resWeb;
		this.resTimestamp = resTimestamp;
		this.resStartTime = resStartTime;
		this.resEndTime = resEndTime;
		this.resTexId = resTexId;
		this.resOwnerName = resOwnerName;
		this.resSeatNumber = resSeatNumber;
		this.resIntro = resIntro;
		this.singleMeal = singleMeal;
		this.resTotalScore = resTotalScore;
		this.resTotalNumber = resTotalNumber;
		this.resMaxNum = resMaxNum;
		this.resType = resType;
	}
	
	public RestaurantVO() {
		
	}
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getResAcc() {
		return resAcc;
	}
	public void setResAcc(String resAcc) {
		this.resAcc = resAcc;
	}
	public String getResPass() {
		return resPass;
	}
	public void setResPass(String resPass) {
		this.resPass = resPass;
	}
	public Integer getResState() {
		return resState;
	}
	public void setResState(Integer resState) {
		this.resState = resState;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResAdd() {
		return resAdd;
	}
	public void setResAdd(String resAdd) {
		this.resAdd = resAdd;
	}
	public String getResTel() {
		return resTel;
	}
	public void setResTel(String resTel) {
		this.resTel = resTel;
	}
	public String getResEmail() {
		return resEmail;
	}
	public void setResEmail(String resEmail) {
		this.resEmail = resEmail;
	}
	public String getResWeb() {
		return resWeb;
	}
	public void setResWeb(String resWeb) {
		this.resWeb = resWeb;
	}
	public Timestamp getResTimestamp() {
		return resTimestamp;
	}
	public void setResTimestamp(Timestamp resTimestamp) {
		this.resTimestamp = resTimestamp;
	}
	public Time getResStartTime() {
		return resStartTime;
	}
	public void setResStartTime(Time resStartTime) {
		this.resStartTime = resStartTime;
	}
	public Time getResEndTime() {
		return resEndTime;
	}
	public void setResEndTime(Time resEndTime) {
		this.resEndTime = resEndTime;
	}
	public String getResTexId() {
		return resTexId;
	}
	public void setResTexId(String resTexId) {
		this.resTexId = resTexId;
	}
	public String getResOwnerName() {
		return resOwnerName;
	}
	public void setResOwnerName(String resOwnerName) {
		this.resOwnerName = resOwnerName;
	}
	public Integer getResSeatNumber() {
		return resSeatNumber;
	}
	public void setResSeatNumber(Integer resSeatNumber) {
		this.resSeatNumber = resSeatNumber;
	}
	public String getResIntro() {
		return resIntro;
	}
	public void setResIntro(String resIntro) {
		this.resIntro = resIntro;
	}
	public Boolean getSingleMeal() {
		return singleMeal;
	}
	public void setSingleMeal(Boolean singleMeal) {
		this.singleMeal = singleMeal;
	}
	public Integer getResTotalScore() {
		return resTotalScore;
	}
	public void setResTotalScore(Integer resTotalScore) {
		this.resTotalScore = resTotalScore;
	}
	public Integer getResTotalNumber() {
		return resTotalNumber;
	}
	public void setResTotalNumber(Integer resTotalNumber) {
		this.resTotalNumber = resTotalNumber;
	}
	public Integer getResMaxNum() {
		return resMaxNum;
	}
	public void setResMaxNum(Integer resMaxNum) {
		this.resMaxNum = resMaxNum;
	}

	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
}
