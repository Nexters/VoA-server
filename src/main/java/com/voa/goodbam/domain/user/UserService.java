package com.voa.goodbam.domain.user;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.support.authentication.AuthService;
import com.voa.goodbam.support.authentication.JwtService;
import com.voa.goodbam.support.authentication.JwtToken;
import com.voa.goodbam.repository.UserRepository;
import com.voa.goodbam.domain.login.LoginRequest;
import com.voa.goodbam.domain.login.LoginResponse;
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
        Optional<User> optionalUser = userRepository.findUserByKakaoId(kakaoId);
        if(!optionalUser.isPresent()){
            User newUser = User.builder()
                    .kakaoId(kakaoId)
                    .isAppUser(loginRequest.isAppUser())
                    .name(loginRequest.getUserName())
                    .os(platform)
                    .profileImage(profileImage).build();
            optionalUser = Optional.of(userRepository.save(newUser));
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
