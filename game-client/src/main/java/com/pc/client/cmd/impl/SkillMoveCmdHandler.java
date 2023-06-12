package com.pc.client.cmd.impl;

import com.alibaba.fastjson.JSON;
import com.pc.client.cache.LocalGameInfo;
import com.pc.client.cmd.CmdHandler;
import com.pc.client.model.SkillModel;
import com.pc.client.gui.GamePanel;
import com.pc.client.gui.RoomPanel;
import com.pc.common.msg.Msg;
import com.pc.common.msg.SkillMsgData;

import java.util.Map;

/**
 * @description: 技能移动消息处理
 * @author: pangcheng
 * @time: 2023/6/12 14:12
 */
public class SkillMoveCmdHandler  implements CmdHandler {
    /**
     * 技能移动  应只收到 坐标消息
     * @param msg 消息对象
     * @param gamePanel 游戏面板
     * @param roomPanel 房间面板
     */
    @Override
    public void doHandle(Msg msg, GamePanel gamePanel, RoomPanel roomPanel) {
        Map<String, SkillModel> stringSkillModelMap = LocalGameInfo.stringSkillModelMap;
        SkillMsgData skillMsgData = JSON.parseObject(msg.getData(), SkillMsgData.class);
        SkillModel skillModel = stringSkillModelMap.get(skillMsgData.getSkillId());
        skillModel.setX(skillMsgData.getX());
        skillModel.setY(skillMsgData.getY());
    }
}
