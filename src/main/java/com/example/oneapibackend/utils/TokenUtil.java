package com.example.oneapibackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.oneapibackend.model.dto.user.UserLoginDto;

import java.util.Date;

public class TokenUtil {
    private static final long EXPIRE_TIME= 24*60*60*1000;   //24小时
    private static final String TOKEN_SECRET="123456";  //密钥盐
    /**
     * 签名生成
     * @param userInfo
     * @return
     */
    public static String sign(UserLoginDto user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", user.getUsername())
                    .withClaim("id", user.getId())

//                    .withAudience(staff.getUsername())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
//        try {
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
//            DecodedJWT jwt = verifier.verify(token);
//            System.err.println("认证通过：");
////            System.err.println("Username: " + jwt.getClaim("username").asString());
////            System.err.println("id: " + jwt.getClaim("id").asString());
//            System.err.println("过期时间：      " + jwt.getExpiresAt());
//            return true;
//        } catch (Exception e){
//            return false;
//        }
        return  true;
    }

    public static String extractParams(String token, String claim){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            Claim res = jwt.getClaim(claim);
            return res.toString();
        } catch (Exception e){
            return "";
        }
    }

    public static void main(String[] args) {
//        UserLoginDto userLoginDto = new UserLoginDto();
//        userLoginDto.setRole("admin");
//        userLoginDto.setUsername("fxxxxxx");
//        userLoginDto.setId(123);
//
//        String token  =sign(userLoginDto);
//        System.out.println("token is " + token);
//        verify(token);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlkIjo3LCJleHAiOjE2OTI5NTc1MDEsInVzZXJuYW1lIjoicXdlcnR5In0.SNxrWtIU2DZ6tlXDZ_6eyjC37Zx-1IuIEmtO1hi73AA";
        String role = extractParams(token, "id");
        System.out.println("role " + role);

    }

}
