package com.example.HotelManagerment.Security.jwt;

import com.example.HotelManagerment.Security.UserRegistrationDetails;
import com.example.HotelManagerment.Security.UserRegistrationDetailsService;
import com.example.HotelManagerment.User.UserResponse;
import com.example.HotelManagerment.User.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserRegistrationDetailsService userRegistrationDetailsService;

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String userName = null;
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer") )  {
            token = authHeader.substring(7);
            userName  = jwtService.extractUsernameFromToken(token);
        }
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails user = userRegistrationDetailsService.loadUserByUsername(userName);
            var authToken = new UsernamePasswordAuthenticationToken(token,null, user.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);

    }
}
