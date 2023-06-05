package chooeat.admin.web.resType.pojo;

import chooeat.admin.core.pojo.Core;

public class ResTypeVO extends Core{
	private static final long serialVersionUID = 1L;
	
	private Integer resTypeId;
	private String resTypeName;
	private Integer resTypeCount;
	
	public ResTypeVO(Integer resTypeId, String resTypeName, Integer resTypeCount) {
		this.resTypeId = resTypeId;
		this.resTypeName = resTypeName;
		this.resTypeCount = resTypeCount;
	}
	
	public ResTypeVO() {
		
	}
	
	public Integer getResTypeId() {
		return resTypeId;
	}
	
	public void setResTypeId(Integer resTypeId) {
		this.resTypeId = resTypeId;
	}
	
	public String getResTypeName() {
		return resTypeName;
	}
	
	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}

	public Integer getResTypeCount() {
		return resTypeCount;
	}

	public void setResTypeCount(Integer resTypeCount) {
		this.resTypeCount = resTypeCount;
	}
	
}
