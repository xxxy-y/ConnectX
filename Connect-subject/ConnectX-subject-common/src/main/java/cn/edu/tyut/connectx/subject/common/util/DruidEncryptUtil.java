package cn.edu.tyut.connectx.subject.common.util;

import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;

/**
 * 对数据库的用户名、密码、连接地址的加密
 *
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
public class DruidEncryptUtil extends CryptoUtils {
    public static void encrypt() throws Exception {
        System.out.println("-------------------------------------自定义key-------------------------------------");
        String[] pair = CryptoUtils.genKeyPair(512);
        System.out.println("privateKey:" + pair[0]);
        System.out.println("publicKey:" + pair[1]);
        // 按道理应该用公钥加密，私钥解密，作者直接抄的druid 的，把这个不规范的写法也给抄过来了，不影响效果，只觉得怪怪的。
        System.out.println("username:" + CryptoUtils.encrypt(pair[0], "root"));
        System.out.println("password:" + CryptoUtils.encrypt(pair[0], "123456"));
        System.out.println("url:" + CryptoUtils.encrypt(pair[0], "jdbc:mysql://192.168.17.129:3306/ConnectX"));
    }

    public static void main(String[] args) throws Exception {
        encrypt();
    }
}
