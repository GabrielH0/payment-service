package org.example.paymentService.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.privateKey}")
    private String privateKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String token = resolveToken(request);
            if (token == null) {
                chain.doFilter(request, response);
                return;
            }
            Claims claims = Jwts.parser().setSigningKey(privateKey.getBytes()).parseClaimsJws(token.replace("Bearer ","")).getBody();

            if (claims != null && isValidClaims(claims)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(), Lists.newArrayList(), new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            chain.doFilter(request, response);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    private boolean isValidClaims(Claims claims) {
        return claims.getExpiration().after(new java.util.Date());
    }

}
