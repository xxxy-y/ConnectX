package cn.edu.tyut.connectx.wx.handler;

import java.util.Map;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/19
 */
public interface WeChatMsgHandler {
    /**
     * 获取到消息类型
     *
     * @return 返回消息类型
     */
    WeChatMsgTypeEnum getMsgType();

    /**
     * 输出的消息
     *
     * @param messageMap 消息表单
     * @return 返回消息
     */
    String dealMsg(Map<String, String> messageMap);
}
