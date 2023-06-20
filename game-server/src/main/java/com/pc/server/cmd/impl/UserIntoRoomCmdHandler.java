package com.pc.server.cmd.impl;

import com.pc.common.cmd.ServerCmdHandler;
import com.pc.common.msg.Msg;
import com.pc.common.netty.cache.RoomCache;
import com.pc.common.netty.cache.UserCache;
import com.pc.common.netty.model.RoomServer;
import com.pc.common.netty.model.UserModel;

/**
 * @description: 玩家 进入房间等待
 * @author: pangcheng
 * @time: 2023/6/12 16:08
 */
public class UserIntoRoomCmdHandler implements ServerCmdHandler {

    @Override
    public void doHandle(Msg msg, UserModel userModel) {
        userModel.setUserId(msg.getUserId());
        userModel.setStart(1);

        // 用户绑定通道
        UserCache.bindChannel(userModel.getChannelId(), userModel.getUserId());

        // 建立房间跟 通道的关系
        // 将用户加入到房间中
        RoomServer roomServer = RoomCache.get(msg.getRoomId());
        roomServer.getUser().put(userModel.getUserId(), userModel);
        // 刷新房间数据，TODO 通知房间内的用户，当前进来多少人了
    }

}
