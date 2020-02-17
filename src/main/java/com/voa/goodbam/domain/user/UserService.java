package com.voa.goodbam.domain.user;

import com.voa.goodbam.domain.login.*;
import com.voa.goodbam.support.authentication.AuthService;
import com.voa.goodbam.support.authentication.JwtService;
import com.voa.goodbam.support.authentication.JwtToken;
import com.voa.goodbam.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final AuthService authService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    public UserService(final UserRepository userRepository, final AuthService authService, final JwtService jwtService){
        this.userRepository = userRepository;
        this.authService = authService;
        this.jwtService = jwtService;
    }
    public DefaultResponse login(LoginRequest loginRequest, String platform) {
        User kakaoUser = authService.checkKaKaoAccessToken(loginRequest.getKakaoToken());
        if(kakaoUser==null){
            return DefaultResponse.of(StatusCode.UNAUTHORIZED, Message.LOGIN_FAIL);
        }
        String kakaoId = kakaoUser.getKakaoId();
        String profileImage = kakaoUser.getProfileImage();
        boolean isAppUser = true;
        if(platform.equals("Web")){
            isAppUser = false;
        }
        Optional<User> optionalUser = userRepository.findUserByKakaoId(kakaoId);
        if(!optionalUser.isPresent()){
            User newUser = User.builder()
                    .kakaoId(kakaoId)
                    .isAppUser(isAppUser)
                    .name(loginRequest.getUserName())
                    .os(platform)
                    .profileImage(profileImage)
                    .kakaoAccessToken(loginRequest.getKakaoToken()).build();
            optionalUser = Optional.of(userRepository.save(newUser));
        }else{
            User user = optionalUser.get();
            //update accessToken
            user.setAppUser(isAppUser);
            user.setOs(platform);
            user.setProfileImage(profileImage);
            if(user.getKakaoAccessToken()==null
                    || !user.getKakaoAccessToken().equals(loginRequest.getKakaoToken())){
                user.setKakaoAccessToken(loginRequest.getKakaoToken());
            }
            userRepository.save(user);
        }

        User user = optionalUser.get();
        JwtToken jwtToken = jwtService.generateToken(kakaoId);

        LoginResponse loginResponse = LoginResponse.builder()
                .jwt(jwtToken)
                .userName(user.getName())
                .userId(user.getId())
                .profileURL(profileImage).build();
        return DefaultResponse.of(StatusCode.CREATED, Message.LOGIN_SUCCESS, loginResponse);
    }
}
