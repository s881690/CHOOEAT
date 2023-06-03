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
@Table(name = "saved_activity")
public class SavedActivityVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saved_activity_id", updatable = false)
	private Integer savedActivityId;
	@Column(name = "acc_id")
	private Integer accId;
	@Column(name = "activity_id")
	private Integer activityId;

	@OneToOne
	@JoinColumn(name = "acc_id", insertable = false, updatable = false)
	private AccountVO accountVO;

	public SavedActivityVO() {
		super();
	}

	public SavedActivityVO(Integer savedActivityId, Integer accId, Integer activityId, AccountVO accountVO) {
		super();
		this.savedActivityId = savedActivityId;
		this.accId = accId;
		this.activityId = activityId;
		this.accountVO = accountVO;
	}

	public Integer getSavedActivityId() {
		return savedActivityId;
	}

	public void setSavedActivityId(Integer savedActivityId) {
		this.savedActivityId = savedActivityId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public AccountVO getAccountVO() {
		return accountVO;
	}

	public void setAccountVO(AccountVO accountVO) {
		this.accountVO = accountVO;
	}

	@Override
	public String toString() {
		return "SavedActivityVO [savedActivityId=" + savedActivityId + ", accId=" + accId + ", activityId=" + activityId
				+ "]";
	}

}
