package com.jiwoong.billingsystem.streaming.service;

import com.jiwoong.billingsystem.streaming.dto.ROLE;
import com.jiwoong.billingsystem.streaming.dto.request.UserDTO;
import com.jiwoong.billingsystem.streaming.dto.response.oauth.GoogleResponse;
import com.jiwoong.billingsystem.streaming.dto.response.oauth.KakaoResponse;
import com.jiwoong.billingsystem.streaming.dto.response.oauth.OAuth2Response;
import com.jiwoong.billingsystem.streaming.entity.Users;
import com.jiwoong.billingsystem.streaming.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersRepository userRepository;
    private final HttpServletRequest request;

    @Override
    @Transactional("streamingTransactionManager")
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("OAuth2User : " + oAuth2User);

        String registerId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        if (registerId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else if (registerId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            oAuth2Response = null;
            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디 값을 만듬
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        // 이미 존재하는 username인지 check
        Optional<Users> optionalUser = userRepository.findByUsername(username);

        Users userEntity;
        if (optionalUser.isEmpty()) {
            userEntity = Users.createUsers(username, oAuth2Response.getName(), oAuth2Response.getEmail(), ROLE.USER);
            userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setRole(ROLE.USER.toString());
            userDTO.setName(oAuth2Response.getName());

            return new CustomOAuth2User(userDTO);
        } else {
            Users existUser = optionalUser.get();
            // 정보 업데이트 사항 반영
            existUser.updateExistUser(oAuth2Response.getEmail(), oAuth2Response.getName());
            userRepository.save(existUser);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existUser.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existUser.getRole().toString());

            return new CustomOAuth2User(userDTO);
        }
    }
}
