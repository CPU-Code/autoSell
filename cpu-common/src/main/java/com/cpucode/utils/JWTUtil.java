package com.cpucode.utils;

import com.cpucode.http.view.TokenObject;
import io.jsonwebtoken.*;
import lombok.Data;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

/**
 * @author : cpucode
 * @date : 2021/10/17 22:44
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class JWTUtil {

    @Data
    public static class VerifyResult{
        /**
         * 是否有效
         */
        private Boolean isValidate;

        /**
         * 5001:token过期;5002:无效token;5003:token校验异常
         */
        private Integer code;

        /**
         * JWT令牌包装对象
         */
        private TokenObject tokenObject;

        public VerifyResult(Boolean isValidate, Integer code){
            this.isValidate = isValidate;
            this.code = code;
        }
    }

    /**
     * 签发JWT
     * @param tokenObject
     * @param secret
     * @return
     * @throws IOException
     */
    public static String createJWTByObj(TokenObject tokenObject, String secret) throws IOException {
        SecretKey secretKey = generalKey(secret);

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = LocalDateTime.now().plusDays(7).atZone(zoneId);

        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setId("cpu")       //签发应用Id
                .setIssuedAt(Date.from(zdt.toInstant()))        //签发时间
                .setHeaderParam("alg", "HS256")           //加密算法
                .addClaims(JsonUtil.convertToMap(tokenObject))
                .setExpiration(Date.from(zdt.toInstant()))      //设置过期时间
                .signWith(SignatureAlgorithm.HS256, secretKey);  //用密钥签名

        //生成JWT
        return builder.compact();
    }


    /**
     * 验证jwt
     */
    public static VerifyResult verifyJwt(String token, String secret) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key =  generalKey(secret);

        try {
//            ZoneId zoneId = ZoneId.systemDefault();
//            ZonedDateTime zdt = LocalDateTime.now().atZone(zoneId);
            Jwt jwt = Jwts.parser().setSigningKey(key).parse(token);

            java.util.Date date = ((Claims) jwt.getBody()).getExpiration();
            if (date == null) {
                return new VerifyResult(false, 5002);
            }

            LocalDateTime expires = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            if (expires.isBefore(LocalDateTime.now())) {
                return new VerifyResult(false, 5001);
            }

            return new VerifyResult(true, 200);
        } catch (Exception e) {
            e.printStackTrace();

            return new VerifyResult(false, 5002);
        }

        //设置需要解析的jwt
    }

    public static TokenObject decode(String token) throws IOException {
        String bodyData = token.split("\\.")[1];
        String bodyStr = new String(Base64.getDecoder().decode(bodyData), StandardCharsets.UTF_8);

        return JsonUtil.getByJson(bodyStr, TokenObject.class);
    }

    /**
     * 生成key
     *
     * @param jwtSecret
     * @return
     */
    private static SecretKey generalKey(String jwtSecret) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] encodedKey = Base64.getMimeDecoder().decode(jwtSecret);
        SecretKey key = new SecretKeySpec(encodedKey, signatureAlgorithm.getJcaName());

        return key;
    }
}
