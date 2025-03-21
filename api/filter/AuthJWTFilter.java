package com.cit.backend.api.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cit.backend.domain.entity.Profile;
import com.cit.backend.domain.service.AuthTokenService;
import com.cit.backend.domain.service.ProfileService;
import com.cit.backend.exceptions.InvalidAuthenticationHeaderException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthJWTFilter extends OncePerRequestFilter {
    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private ApplicationContext applicationContext;

    private ProfileService getProfileService() {
        return applicationContext.getBean(ProfileService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        if(context.getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token;
        String email;
        if (authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            email = authTokenService.getSubject(token);
        } else throw new InvalidAuthenticationHeaderException();

        if (email == null) throw new JWTVerificationException("Invalid token");

        try {
            Profile userDetails = (Profile) getProfileService().loadUserByUsername(email);
            authTokenService.validateToken(token, userDetails);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(usernamePasswordAuthenticationToken);
        } catch (InternalAuthenticationServiceException e) {
            throw new JWTVerificationException("Invalid token");
        }

        filterChain.doFilter(request, response);
    }
}
