package cn.edu.tyut.connectx.wx.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/19
 */
public class MessageUtil {
    private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);

    /**
     * 解析微信发来的请求（XML）.
     *
     * @param msg 消息
     * @return map
     */
    public static Map<String, String> parseXml(final String msg) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>(16);
        // 从request中取得输入流
        try (InputStream inputStream = new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8))) {
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            log.error("解析微信服务器传来的xml error", e);
        }
        return map;
    }
}
