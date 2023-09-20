package com.example.book.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());


    private MyUserDetailsService myUserDetailsService;
    private JWTUtils jwtUtils;

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void setJwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXJlc2gyQGdhLmNvbSIsImlhdCI6MTY5NDgwMDAzNiwiZXhwIjoxNjk0ODg2NDM2fQ.z3smvkvDJqOYz7699UjvH5JQ51MuWL-KXffegc1UxWU


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXJlc2gyQGdhLmNvbSIsImlhdCI6MTY5NDgwMDAzNiwiZXhwIjoxNjk0ODg2NDM2fQ.z3smvkvDJqOYz7699UjvH5JQ51MuWL-KXffegc1UxWU
        if (StringUtils.hasLength(headerAuth) && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7);
        }
        logger.info("no header");
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username); // email address
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.info("cannot set user authentication token");
        }
        filterChain.doFilter(request, response);
    }
}