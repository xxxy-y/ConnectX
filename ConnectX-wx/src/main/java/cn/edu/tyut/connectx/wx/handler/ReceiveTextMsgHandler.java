package cn.edu.tyut.connectx.wx.handler;

import cn.edu.tyut.connectx.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/19
 */
@Component
@Slf4j
public class ReceiveTextMsgHandler implements WeChatMsgHandler {
    private static final String KEY_WORD = "验证码";
    private static final String LOGIN_PREFIX = "loginCode";
    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(@NotNull Map<String, String> messageMap) {
        log.info("触发文本！");
        // 服务器的openid
        String toUserName = messageMap.get("ToUserName");
        // 用户的openid
        String fromUserName = messageMap.get("FromUserName");
        String content = messageMap.get("Content");
        String numContent = String.format("%04d", new Random().nextInt(10000));
        String numKey = redisUtil.buildKey(LOGIN_PREFIX, String.valueOf(numContent));
        Boolean b = redisUtil.setNx(numKey, fromUserName, 5L, TimeUnit.MINUTES);
        if (!b) {
            throw new RuntimeException("redis新增失败");
        }
        log.info("numKey: {}", numKey);
        if (KEY_WORD.equals(content)) {
            return "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[" + numContent + "]]></Content>\n" + "</xml>";
        }
        return "";
    }
}
