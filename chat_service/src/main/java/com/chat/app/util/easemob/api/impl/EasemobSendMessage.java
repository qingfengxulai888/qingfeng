package com.chat.app.util.easemob.api.impl;


import com.chat.app.util.easemob.api.SendMessageAPI;
import com.chat.app.util.easemob.comm.EasemobAPI;
import com.chat.app.util.easemob.comm.OrgInfo;
import com.chat.app.util.easemob.comm.ResponseHandler;
import com.chat.app.util.easemob.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.MessagesApi;
import io.swagger.client.model.Msg;

public class EasemobSendMessage implements SendMessageAPI {
    private ResponseHandler responseHandler = new ResponseHandler();
    private MessagesApi api = new MessagesApi();
    @Override
    public Object sendMessage(final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameMessagesPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, TokenUtil.getAccessToken(), (Msg) payload);
            }
        });
    }
}
