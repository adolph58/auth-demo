package com.example.demo.auth;

import com.example.demo.config.AccessPaths;
import com.example.demo.config.JwtProperties;
import com.example.demo.handle.BusinessException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT token 认证过滤器
 */
@Slf4j
public class JwtAuthFilter extends GenericFilterBean {

    private JwtProperties jwtProperties;

    private HandlerExceptionResolver handlerExceptionResolver;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtAuthFilter(JwtProperties jwtProperties, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtProperties = jwtProperties;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!needFilter(servletRequest)) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("Authorization");
        if (!StringUtils.hasText(jwtToken)) {
            BusinessException exception = new BusinessException(401, "未认证", "未认证");
            handlerExceptionResolver.resolveException(req, (HttpServletResponse) servletResponse, null, exception);
            return;
        }
        if (!isValidToken(jwtToken)) {
            BusinessException exception = new BusinessException(401, "无效凭证", "当前凭证已失效");
            handlerExceptionResolver.resolveException(req, (HttpServletResponse) servletResponse, null, exception);
            return;
        }
        filterChain.doFilter(req,servletResponse);
    }

    private boolean isValidToken(String jwtToken) {
        try {
            // 验证签名 和 过期时间
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(jwtToken.replace("Bearer", ""))
                    .getBody();
            return true;
        } catch (JwtException e) {
            log.warn("JWT token check failure, message: {}", e.getMessage());
            return false;
        }
    }

    private boolean needFilter(ServletRequest request) {
        HttpServletRequest hsr = (HttpServletRequest) request;
        String path = hsr.getServletPath();
        for (String whitePath : AccessPaths.AUTH_WHITELIST) {
            if (pathMatcher.match(whitePath, path)) {
                return false;
            }
        }
        for (String staticSource : AccessPaths.STATIC_RESOURCE) {
            if (path.endsWith(staticSource)) {
                return false;
            }
        }
        return true;
    }
}
