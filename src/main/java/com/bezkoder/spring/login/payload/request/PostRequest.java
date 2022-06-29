package com.bezkoder.spring.login.payload.request;


import com.bezkoder.spring.login.models.Paragrafe;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class PostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String cover;

    private Set<Paragrafe> paragrafe;

    public PostRequest() {
    }

    public PostRequest(String title, String cover, Set<Paragrafe> paragrafe) {
        this.title = title;
        this.cover = cover;
        this.paragrafe = paragrafe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Set<Paragrafe> getParagrafe() {
        return paragrafe;
    }

    public void setParagrafe(Set<Paragrafe> paragrafe) {
        this.paragrafe = paragrafe;
    }
}
