package com.example.spring_security.core.auth.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class LoggingFilter  extends OncePerRequestFilter {

    @Value("${INSTANCE_NAME:unknown}")
    private String instanceName;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("[" + this.instanceName + "] " + request.getMethod() + " " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}
