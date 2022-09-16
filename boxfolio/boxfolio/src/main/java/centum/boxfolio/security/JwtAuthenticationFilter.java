package centum.boxfolio.security;

import centum.boxfolio.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String userId = null;
            String jwt = getJwtFromCookie(request);
            log.info("jwt: {}", jwt);
            if (StringUtils.isNotEmpty(jwt) && jwtTokenProvider.validateToken(jwt)) {
                userId = jwtTokenProvider.getUserIdFromJWT(jwt);
                log.info("userId: {}", userId);

                UserAuthentication authentication = new UserAuthentication(userId, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                if (StringUtils.isEmpty(jwt)) {
                    request.setAttribute("unauthorization", "401 인증키 없음.");
                }

                if (jwtTokenProvider.validateToken(jwt)) {
                    request.setAttribute("unauthorization", "401-001 인증키 만료.");
                }
            }
            if (userId != null) {
                jwtTokenProvider.reissueRefreshToken(userId);
            }
        } catch (Exception e) {
            log.error("Could not set user authentication in security context");
            log.error("[jwtRequestFilter] Exception: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        String bearerToken = request.getHeader("Authorization");
        log.info("token: {}", bearerToken);
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer+")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private String getJwtFromCookie(HttpServletRequest request) throws UnsupportedEncodingException {
        String bearerToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("token"))
                .findAny().map(Cookie::getValue)
                .orElse(null);
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer+")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
