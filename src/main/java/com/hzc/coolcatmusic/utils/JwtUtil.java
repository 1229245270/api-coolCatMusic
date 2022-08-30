package com.hzc.coolcatmusic.utils;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    /**
     * 生成签名,15分钟后过期
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String account,String password){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header).withClaim("account",account)
                .withClaim("password",password).withExpiresAt(date).sign(algorithm);
    }


    public static String verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            BASE64Decoder decoder = new BASE64Decoder();
            String string = new String(decoder.decodeBuffer(jwt.getPayload()), StandardCharsets.UTF_8);
            string = string.substring(0,string.lastIndexOf("}") + 1);
            Map map = (Map) JSON.parse(string);
            return (String) map.get("account");
        } catch (Exception e) {
            return null;
        }

    }
}
