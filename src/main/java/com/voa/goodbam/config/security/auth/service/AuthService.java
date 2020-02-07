package com.voa.goodbam.config.security.auth.service;

import com.voa.goodbam.user.domain.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

@Service
public class AuthService
{
    public User checkKaKaoAccessToken(String accessToken) {
        try {
            final ResponseEntity kakaoResponse = requestKaKaoAuth(accessToken);
            if(kakaoResponse==null){
                return null;
            }
            JSONParser jsonParse = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParse.parse(kakaoResponse.getBody().toString());
            String kakaoId = jsonObj.get("id").toString();
            String profileImage = ((JSONObject)jsonParse.parse(jsonObj.get("properties").toString())).get("profile_image").toString();
            return User.builder().kakaoId(kakaoId).profileImage(profileImage).build();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        } catch(HttpClientErrorException httpClientErrorException){
            httpClientErrorException.printStackTrace();
            return null;
        }
    }

    private ResponseEntity requestKaKaoAuth(String accesToken){
        String url = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        headers.setBearerAuth(accesToken);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .build(false);

        ResponseEntity response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
        return response;
    }
}