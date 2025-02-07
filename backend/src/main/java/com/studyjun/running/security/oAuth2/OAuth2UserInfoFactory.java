package com.studyjun.running.security.oAuth2;

import com.studyjun.running.common.Provider;
import com.studyjun.running.entity.oAuth2.Google;
import com.studyjun.running.entity.oAuth2.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(Provider.google.toString())) {
            return new Google(attributes);
        } else {
            throw new IllegalArgumentException("지원하지 않는 OAuth2 제공자입니다: " + registrationId);
        }
    }
}