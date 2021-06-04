package com.ds.domain;

public class userRoom {

	int userRoomNumber;		//방 번호
	String userId;	//상대 유저
	String nickName; //방 이름, 상대 유저의 닉네임
	String me;		//로그인한 유저
	
	
	public int getUserRoomNumber() {
		return userRoomNumber;
	}

	public void setUserRoomNumber(int userRoomNumber) {
		this.userRoomNumber = userRoomNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

	@Override
	public String toString() {
		return "Room [userRoomNumber=" + userRoomNumber + ", userId=" + userId+ ", nickName=" + nickName +", me=" + me + "]";
	}


}
