package com.bezkoder.spring.login.payload.request;

import javax.validation.constraints.NotBlank;

public class ParagrafeRequest {
    @NotBlank
    private String text;

    @NotBlank
    private String order;

    @NotBlank
    private String poza;

    public ParagrafeRequest() {
    }

    public ParagrafeRequest(String text, String poza) {
        this.text = text;
        this.poza = poza;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }
}
