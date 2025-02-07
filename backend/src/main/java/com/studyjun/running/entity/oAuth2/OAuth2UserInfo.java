package com.studyjun.running.entity.oAuth2;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getProvider();

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();
}
