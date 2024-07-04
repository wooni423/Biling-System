package com.jiwoong.bilingsystem.auth.service;

import com.jiwoong.bilingsystem.auth.dto.ROLE;
import com.jiwoong.bilingsystem.auth.dto.UserDTO;
import com.jiwoong.bilingsystem.auth.dto.oauth.GoogleResponse;
import com.jiwoong.bilingsystem.auth.dto.oauth.KakaoResponse;
import com.jiwoong.bilingsystem.auth.dto.oauth.OAuth2Response;
import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final HttpServletRequest request;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("OAuth2User : " + oAuth2User);

        String registerId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registerId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else if (registerId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디 값을 만듬
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        // 이미 존재하는 username인지 check
        Users existUser = userRepository.findByUsername(username);
        String ipAddress = getClientIp(request);

        if (existUser == null) {
            Users userEntity = Users.createUsers(username, oAuth2Response.getName(), oAuth2Response.getEmail(), ipAddress, ROLE.USER);

            userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setName(username);
            userDTO.setRole(ROLE.USER.toString());
            userDTO.setName(oAuth2Response.getName());

            return new CustomOAuth2User(userDTO);
        } else {
            // 정보 업데이트 사항 반영
            existUser.updateExistUser(oAuth2Response.getEmail(), oAuth2Response.getName(), ipAddress);

            userRepository.save(existUser);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existUser.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existUser.getRole().toString());

            return new CustomOAuth2User(userDTO);
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // Handle localhost address
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return ip;
    }
}
