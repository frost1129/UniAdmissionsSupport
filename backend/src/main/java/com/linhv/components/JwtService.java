/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.components;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author prodi
 */
@Component
public class JwtService {
    
    public static final String SECRET_KEY = "2n@Ld<QDjODEV9uEeNi`t4bk~ltb$2Tm";
    public static final byte[] SHARED_SECRET_KEY = SECRET_KEY.getBytes();
    public static final int EXPIRE_TIME = 86400000;

    public String generateTokenLogin(String email) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(SHARED_SECRET_KEY);
            
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("email", email);
            
            builder.expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME));
            
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            
            signedJWT.sign(signer);
            token = signedJWT.serialize();
        } catch (JOSEException e) {
            System.out.println(e.getMessage());
        }
        return token;
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SHARED_SECRET_KEY);
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (JOSEException | ParseException e) {
            System.err.println(e.getMessage());
        }
        return claims;
    }
    
    private Date getExpirationDateFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        Date expiration = claims.getExpirationTime();
        return expiration;
    }

    public String getUsernameFromToken(String token) {
        String email = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            email = claims.getStringClaim("email");
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return email;
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String email = getUsernameFromToken(token);
        
        return !(email == null || email.isEmpty() || isTokenExpired(token));
    }
}
