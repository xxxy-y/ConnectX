package cn.edu.tyut.connectx.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/19
 */
@Component
public class WeChatMsgFactory implements InitializingBean {
    private final Map<WeChatMsgTypeEnum, WeChatMsgHandler> weChatMsgHandlerMap = new HashMap<>();
    private List<WeChatMsgHandler> weChatMsgHandlerList;

    /**
     * 将WeChatMsgHandler接口下的实现类都自动注入进来
     */
    @Autowired
    public void setWeChatMsgHandlerList(List<WeChatMsgHandler> weChatMsgHandlerList) {
        this.weChatMsgHandlerList = weChatMsgHandlerList;
    }

    public WeChatMsgHandler getWeChatMsgHandler(String weChatMsgType) {
        WeChatMsgTypeEnum msgType = WeChatMsgTypeEnum.getByMsgType(weChatMsgType);
        return weChatMsgHandlerMap.get(msgType);
    }

    @Override
    public void afterPropertiesSet() {
        weChatMsgHandlerList.forEach(weChatMsgHandler ->
                weChatMsgHandlerMap.put(weChatMsgHandler.getMsgType(), weChatMsgHandler));
    }
}
