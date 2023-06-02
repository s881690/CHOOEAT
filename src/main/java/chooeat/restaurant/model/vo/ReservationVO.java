package chooeat.restaurant.model.vo;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class ReservationVO {
    private Integer reservationId;
    private Integer accId;
    private Integer restaurantId;
    private Integer reservationNumber;
    private Date reservationDate;
    private Time reservationStartTime;
    private Time reservationEndTime;
    private String reservationNote;
    private Integer reservationState;
    private Timestamp restaurantCommentDatetime;
    private String restaurantCommentText;
    private Integer restaurantCommentScore;
    private Timestamp restaurantCommentReplyDatetime;
    private String restaurantCommentReplyText;
    private Boolean isComment;
    
    public ReservationVO() {

	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(Integer reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Time getReservationStartTime() {
		return reservationStartTime;
	}

	public void setReservationStartTime(Time reservationStartTime) {
		this.reservationStartTime = reservationStartTime;
	}

	public Time getReservationEndTime() {
		return reservationEndTime;
	}

	public void setReservationEndTime(Time reservationEndTime) {
		this.reservationEndTime = reservationEndTime;
	}

	public String getReservationNote() {
		return reservationNote;
	}

	public void setReservationNote(String reservationNote) {
		this.reservationNote = reservationNote;
	}

	public Integer getReservationState() {
		return reservationState;
	}

	public void setReservationState(Integer reservationState) {
		this.reservationState = reservationState;
	}

	public Timestamp getRestaurantCommentDatetime() {
		return restaurantCommentDatetime;
	}

	public void setRestaurantCommentDatetime(Timestamp restaurantCommentDatetime) {
		this.restaurantCommentDatetime = restaurantCommentDatetime;
	}

	public String getRestaurantCommentText() {
		return restaurantCommentText;
	}

	public void setRestaurantCommentText(String restaurantCommentText) {
		this.restaurantCommentText = restaurantCommentText;
	}

	public Integer getRestaurantCommentScore() {
		return restaurantCommentScore;
	}

	public void setRestaurantCommentScore(Integer restaurantCommentScore) {
		this.restaurantCommentScore = restaurantCommentScore;
	}

	public Timestamp getRestaurantCommentReplyDatetime() {
		return restaurantCommentReplyDatetime;
	}

	public void setRestaurantCommentReplyDatetime(Timestamp restaurantCommentReplyDatetime) {
		this.restaurantCommentReplyDatetime = restaurantCommentReplyDatetime;
	}

	public String getRestaurantCommentReplyText() {
		return restaurantCommentReplyText;
	}

	public void setRestaurantCommentReplyText(String restaurantCommentReplyText) {
		this.restaurantCommentReplyText = restaurantCommentReplyText;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

	

}