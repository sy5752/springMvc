package kr.or.ddit.user.model;

import java.util.List;

public class UsersVo {

	private List<UserVo> userVoList;

	public List<UserVo> getUserVoList() {
		return userVoList;
	}

	public void setUserid(List<UserVo> userVoList) {
		this.userVoList = userVoList;
	}

	@Override
	public String toString() {
		return "UsersVo [userVoList=" + userVoList + "]";
	}
	
}
