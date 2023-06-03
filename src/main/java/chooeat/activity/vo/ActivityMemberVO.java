package chooeat.activity.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "activity_member", catalog = "chooeat")
public class ActivityMemberVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_member_id", updatable = false)
	private Integer activityMemberId;
	@Column(name = "activity_id")
	private Integer activityId;
	@Column(name = "acc_id")
	private Integer accId;
	@Column(name = "group_note")
	private String groupNote;

	@OneToOne
	@JoinColumn(name = "acc_id", insertable = false, updatable = false)
	private AccountVO accountVO;

	public ActivityMemberVO(Integer activityId, Integer accId) {
		super();
		this.activityId = activityId;
		this.accId = accId;
	}

	public ActivityMemberVO() {
		super();
	}

	public ActivityMemberVO(Integer activityMemberId, Integer activityId, Integer accId, String groupNote,
			AccountVO accountVO) {
		super();
		this.activityMemberId = activityMemberId;
		this.activityId = activityId;
		this.accId = accId;
		this.groupNote = groupNote;
		this.accountVO = accountVO;
	}

	public Integer getActivityMemberId() {
		return activityMemberId;
	}

	public void setActivityMemberId(Integer activityMemberId) {
		this.activityMemberId = activityMemberId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getGroupNote() {
		return groupNote;
	}

	public void setGroupNote(String groupNote) {
		this.groupNote = groupNote;
	}

	public AccountVO getAccountVO() {
		return accountVO;
	}

	public void setAccountVO(AccountVO accountVO) {
		this.accountVO = accountVO;
	}

}
