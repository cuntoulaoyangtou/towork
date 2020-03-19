package config;

import io.jsonwebtoken.Claims;

import java.util.UUID;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 手机号正则
     */
    public static final String REGEX_PHONE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "towork ";
    /**
     * 生成的token
     */
    public static final String JWT_ID = UUID.randomUUID().toString();
    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "ctlyttowork";
    /**
     * 签发人
     */
    public static final String JWT_ISSUER = "ctlyt";
    /**
     * 过期时间
     */
    public static final int JWT_TTL = 60*60*1000;
}
