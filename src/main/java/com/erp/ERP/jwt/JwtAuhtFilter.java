package com.erp.ERP.jwt;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import org.springframework.http.HttpHeaders;
import jakarta.servlet.ServletException;
import io.jsonwebtoken.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.*;

@Component
public class JwtAuhtFilter extends OncePerRequestFilter {
 
    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        final String token = getTokenFromRequest(request);

        if (token != null) {
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

}