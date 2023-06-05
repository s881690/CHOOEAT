package chooeat.activity.vo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class ActivityRestaurantVO implements Serializable {

	private static final long serialVersionUID = 2099521162026557829L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id", updatable = false)
	private Integer restaurantId; // 餐廳流水編號（主鍵）
	@Column(name = "res_acc")
	private String resAcc; // 餐廳會員帳號
	@Column(name = "res_pass")
	private String resPass; // 餐廳會員密碼
	@Column(name = "res_state")
	private Integer resState; // 餐廳帳號狀態
	@Column(name = "res_name")
	private String resName; // 餐廳名稱
	@Column(name = "res_add")
	private String resAdd; // 餐廳地址
	@Column(name = "res_tel")
	private String resTel; // 餐廳電話
	@Column(name = "res_email")
	private String resEmail; // 餐廳電子信箱
	@Column(name = "res_web")
	private String resWeb; // 餐廳連結
	@Column(name = "res_timestamp")
	private Timestamp resTimestamp; // 註冊日期
	@Column(name = "res_start_time")
	private Time resStartTime; // 開始營業時間
	@Column(name = "res_end_time")
	private Time resEndTime; // 結束營業時間
	@Column(name = "res_tex_id")
	private String resTexId; // 統一編號
	@Column(name = "res_owner_name")
	private String resOwnerName; // 餐廳負責人姓名
	@Column(name = "res_seat_number")
	private Integer resSeatNumber; // 座位總數
	@Column(name = "res_intro")
	private String resIntro; // 餐廳簡介
	@Column(name = "single_meal")
	private Boolean singleMeal; // 是否接受單一客人
	@Column(name = "res_total_score")
	private Integer resTotalScore; // 餐廳評論總分
	@Column(name = "res_total_number")
	private Integer resTotalNumber; // 餐廳評論總人數
	@Column(name = "res_max_num")
	private Integer resMaxNum; // 每時段可訂位座位上限
	
	public ActivityRestaurantVO() {
		super();
	}

	public ActivityRestaurantVO(Integer restaurantId, String resAcc, String resPass, Integer resState, String resName,
			String resAdd, String resTel, String resEmail, String resWeb, Timestamp resTimestamp, Time resStartTime,
			Time resEndTime, String resTexId, String resOwnerName, Integer resSeatNumber, String resIntro,
			Boolean singleMeal, Integer resTotalScore, Integer resTotalNumber, Integer resMaxNum) {
		super();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
	
	
}