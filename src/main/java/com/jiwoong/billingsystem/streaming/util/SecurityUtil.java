package com.jiwoong.billingsystem.streaming.util;

import com.jiwoong.billingsystem.streaming.dto.request.UserDTO;
import com.jiwoong.billingsystem.streaming.service.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public static UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
            return user.getUserDTO();
        }
        return null;
    }
}
