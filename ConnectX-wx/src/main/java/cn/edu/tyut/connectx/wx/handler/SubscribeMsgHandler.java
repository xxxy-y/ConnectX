package cn.edu.tyut.connectx.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/19
 */
@Component
@Slf4j
public class SubscribeMsgHandler implements WeChatMsgHandler {
    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealMsg(@NotNull Map<String, String> messageMap) {
        log.info("触发用户关注事件！");
        String toUserName = messageMap.get("ToUserName");
        String fromUserName = messageMap.get("FromUserName");
        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好,欢迎关注]]></Content>\n" +
                "</xml>";
    }
}
