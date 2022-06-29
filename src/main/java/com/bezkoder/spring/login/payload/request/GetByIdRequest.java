package com.bezkoder.spring.login.payload.request;

import org.springframework.lang.Nullable;

public class GetByIdRequest {
    private Long id;

    @Nullable
    private  String ip;

    public GetByIdRequest() {
    }

    public GetByIdRequest(Long id) {
        this.id = id;
    }

    @Nullable
    public String getIp() {
        return ip;
    }

    public void setIp(@Nullable String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
