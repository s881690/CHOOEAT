package chooeat.admin.web.reservation.pojo;

import java.sql.Timestamp;

import chooeat.admin.core.pojo.Core;

public class AdminResCommentPOJO extends Core{
	private static final long serialVersionUID = 1L;
	
	private Integer reservationId;
	private Timestamp restaurantCommentDatetime;
	private Integer restaurantId;
	private Integer accId;
	private String accName;
	private Boolean isComment;
	private Integer restaurantCommentScore;
	private Timestamp restaurantCommentReplyDatetime;
	
	public AdminResCommentPOJO(Integer reservationId, Timestamp restaurantCommentDatetime, Integer restaurantId,
			Integer accId, String accName, Boolean isComment, Integer restaurantCommentScore,
			Timestamp restaurantCommentReplyDatetime) {
		super();
		this.reservationId = reservationId;
		this.restaurantCommentDatetime = restaurantCommentDatetime;
		this.restaurantId = restaurantId;
		this.accId = accId;
		this.accName = accName;
		this.isComment = isComment;
		this.restaurantCommentScore = restaurantCommentScore;
		this.restaurantCommentReplyDatetime = restaurantCommentReplyDatetime;
	}
	
	public AdminResCommentPOJO() {
		
	}
	
	public Integer getreservationId() {
		return reservationId;
	}
	public void setreservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public Timestamp getRestaurantCommentDatetime() {
		return restaurantCommentDatetime;
	}
	public void setRestaurantCommentDatetime(Timestamp restaurantCommentDatetime) {
		this.restaurantCommentDatetime = restaurantCommentDatetime;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Boolean getIsComment() {
		return isComment;
	}
	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
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
	
}
