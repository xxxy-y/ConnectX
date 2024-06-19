package cn.edu.tyut.connectx.wx.controller;

import cn.edu.tyut.connectx.wx.handler.WeChatMsgFactory;
import cn.edu.tyut.connectx.wx.handler.WeChatMsgHandler;
import cn.edu.tyut.connectx.wx.utils.MessageUtil;
import cn.edu.tyut.connectx.wx.utils.Sha1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/19
 */
@RestController
@RequestMapping("/wx/")
@Slf4j
public class CallbackController {
    private static final String TOKEN = "uDXiEEPrLzETcqqWXizhDAMeGCYVwZlpbiqQDGiupqL";
    private WeChatMsgFactory weChatMsgFactory;

    @Autowired
    public void setWeChatMsgFactory(WeChatMsgFactory weChatMsgFactory) {
        this.weChatMsgFactory = weChatMsgFactory;
    }

    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }

    /**
     * 验证URL的回调函数
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串，返回则表示验证通过
     * @return 返回echostr则表示验证通过，其他都为验证未通过
     */
    @GetMapping("callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数----singature: {}, timestamp: {}, nonce: {}, echostr: {}",
                signature, timestamp, nonce, echostr);
        String sha1 = Sha1.getSha1(TOKEN, timestamp, nonce, "");
        if (Objects.equals(sha1, signature)) {
            // 返回这个随机的字符串，表示验证通过
            return echostr;
        }
        // 表示验证未通过
        return "unknown";
    }

    @PostMapping("callback")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("get验签请求参数----requestBody: {}, singature: {}, timestamp: {}, nonce: {}",
                requestBody, signature, timestamp, nonce);
        // 解析从微信服务器传来的xml格式的消息体
        Map<String, String> messageMap = MessageUtil.parseXml(requestBody);
        String msgType = messageMap.get("MsgType");
        String event = messageMap.get("Event") == null ? "" : "." + messageMap.get("Event");
        String msgTypeKey = msgType + event;
        log.info("msgType: {}, event: {}, msgTypeKey: {}", msgTypeKey, event, msgTypeKey);
        WeChatMsgHandler weChatMsgHandler = weChatMsgFactory.getWeChatMsgHandler(msgTypeKey);
        if (Objects.isNull(weChatMsgHandler)) {
            return "unknown";
        }
        String msg = weChatMsgHandler.dealMsg(messageMap);
        log.info("replyContent: {}", msg);
        return msg;
    }
}
