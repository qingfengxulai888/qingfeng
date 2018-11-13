package com.chat.app.util.easemob.api.impl;


import com.chat.app.util.easemob.api.AuthTokenAPI;
import com.chat.app.util.easemob.comm.TokenUtil;

public class EasemobAuthToken implements AuthTokenAPI {

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
