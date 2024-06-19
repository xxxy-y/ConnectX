package cn.edu.tyut.connectx.wx.handler;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/19
 */
@Getter
public enum WeChatMsgTypeEnum {
    // 微信消息类型
    SUBSCRIBE("event.subscribe", "用户关注事件"),
    TEXT_MSG("text","接收用户文本消息");
    private final String msgType;
    private final String description;

    @Contract(pure = true)
    WeChatMsgTypeEnum(String msgType, String desc) {
        this.msgType = msgType;
        this.description = desc;
    }

    @Contract(pure = true)
    public static @Nullable WeChatMsgTypeEnum getByMsgType(String msgType) {
        for (WeChatMsgTypeEnum weChatMsgTypeEnum : WeChatMsgTypeEnum.values()) {
            if (weChatMsgTypeEnum.getMsgType().equals(msgType)) {
                return weChatMsgTypeEnum;
            }
        }
        return null;
    }
}
