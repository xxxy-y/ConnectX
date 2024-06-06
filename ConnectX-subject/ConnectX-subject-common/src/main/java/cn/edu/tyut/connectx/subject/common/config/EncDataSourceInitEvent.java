package cn.edu.tyut.connectx.subject.common.config;

import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.event.DataSourceInitEvent;
import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * dynamic-datasource 解密配置类
 * TODO 将所有的配置类都放在common中吗?
 *
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Configuration
public class EncDataSourceInitEvent implements DataSourceInitEvent {
    /**
     * 加密正则 实现自定义的 默认为ENC
     */
    private static final Pattern ENC_PATTERN = Pattern.compile("^ENC\\((.*)\\)$");

    @Override
    public void beforeCreate(DataSourceProperty dataSourceProperty) {
        String publicKey = dataSourceProperty.getPublicKey();
        if (StringUtils.hasText(publicKey)) {
            dataSourceProperty.setUrl(decrypt(publicKey, dataSourceProperty.getUrl()));
            dataSourceProperty.setUsername(decrypt(publicKey, dataSourceProperty.getUsername()));
            dataSourceProperty.setPassword(decrypt(publicKey, dataSourceProperty.getPassword()));
        }
    }

    @Override
    public void afterCreate(DataSource dataSource) {

    }

    /**
     * 字符串解密
     */
    private String decrypt(String publicKey, String cipherText) {
        if (StringUtils.hasText(cipherText)) {
            Matcher matcher = ENC_PATTERN.matcher(cipherText);
            if (matcher.find()) {
                try {
                    return CryptoUtils.decrypt(publicKey, matcher.group(1));
                } catch (Exception e) {
                    throw new RuntimeException("DynamicDataSourceProperties.decrypt error ", e);
                }
            }
        }
        return cipherText;
    }
}
