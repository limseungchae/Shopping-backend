package seung.shopping.backend.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtServiceImpl implements JwtService {

    // JWT 시크릿 키
    private String secretKey = "adfbdokfjifj@@@Sdasda!!!kdjwekdjf4156746";

    @Override
    public String getToken(String key, Object value) {

        // 토큰 만료 시간 설정 (현재 시간으로부터 5분 후)
        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 5);

        // Base64로 인코딩된 시크릿 키를 바이트 배열로 디코딩
        byte[] secretByteKey = Base64.getDecoder().decode(secretKey);

        // 시그니처에 사용할 키 생성
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        // JWT 헤더 설정
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        // JWT 페이로드 설정
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put(key, value);

        // JWT 빌더 생성 및 헤더, 페이로드, 만료 시간, 시그니처 설정
        JwtBuilder builder = Jwts.builder()
                .setHeader(headerMap)
                .setClaims(payloadMap)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        // 최종적으로 생성된 JWT 반환
        return builder.compact();
    }
}
