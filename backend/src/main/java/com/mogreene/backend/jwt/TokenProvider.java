package com.mogreene.backend.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @name : TokenProvider
 * @author : Mo-Greene
 * @date : 2023/07/05
 * 토큰 생성, 토큰 유효성 검증
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token-validity-in-seconds}")
    private long tokenValidMillisecond;
    private final UserDetailsService userDetailsService;
    private Key key;

    // SecretKey Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * secret key 를 base64 디코드 하기위해 생성
     */
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 토큰 생성
    public String createToken(Long userNo, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(Long.toString(userNo));
        claims.put("roles", roles);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS256) // 암호화
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond)) // 토큰 만료일 설정
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 유저 이름 추출
    public String getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // JWT 토큰 유효성 체크
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (SecurityException | MalformedJwtException | IllegalArgumentException exception) {
            log.info("잘못된 Jwt 토큰입니다");
        } catch (ExpiredJwtException exception) {
            log.info("만료된 Jwt 토큰입니다");
        } catch (UnsupportedJwtException exception) {
            log.info("지원하지 않는 Jwt 토큰입니다");
        }

        return false;
    }
}
