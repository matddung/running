package com.studyjun.running.security.oAuth2;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class OAuth2Config {

    @Getter
    @Component
    public static class Auth {
        @Value("${jwt.secret-key}")
        private String tokenSecret;

        @Value("${jwt.access.expiration}")
        private long accessTokenExpirationMsec;

        @Value("${jwt.refresh.expiration}")
        private long refreshTokenExpirationMsec;

        @PostConstruct
        public void init() {
            if (tokenSecret == null) {
                throw new IllegalArgumentException("토큰 시크릿이 null일 수 없습니다.");
            }
            if (accessTokenExpirationMsec == 0) {
                throw new IllegalArgumentException("액세스 토큰 만료 시간이 0일 수 없습니다.");
            }
            if (refreshTokenExpirationMsec == 0) {
                throw new IllegalArgumentException("리프레시 토큰 만료 시간이 0일 수 없습니다.");
            }
        }

    }

    @Getter
    @Component
    public static final class OAuth2 {
        // Getter
        private List<String> authorizedRedirectUris = new ArrayList<>();

        @Value("${app.oauth2.authorizedRedirectUris}")
        public void setAuthorizedRedirectUris(String authorizedRedirectUris) {
            this.authorizedRedirectUris = Arrays.asList(authorizedRedirectUris.split(","));
        }

    }

    @Getter
    @Component
    public static class OAuth2ConfigHolder {
        private final Auth auth;
        private final OAuth2 oauth2;

        public OAuth2ConfigHolder(Auth auth, OAuth2 oauth2) {
            this.auth = auth;
            this.oauth2 = oauth2;
        }

    }
}