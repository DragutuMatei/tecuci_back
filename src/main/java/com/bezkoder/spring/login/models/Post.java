package com.bezkoder.spring.login.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Table(name = "posts")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private int views;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @NotBlank
    private String cover;

//    @NotBlank
    private Long id_user;

    private boolean postat;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "post_paragrafe",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "paragrafe_id"))
    private List<Paragrafe> paragrafe = new ArrayList<>();

    public Post(String title, String cover, List<Paragrafe> paragrafe, Long id) {
        this.title = title;
        this.views = 0;
        this.cover = cover;
        this.paragrafe = paragrafe;
        this.id_user = id;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String o_data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.date = LocalDate.parse(o_data, formatter);
    }

    public Post() {
    }

    public boolean isPostat() {
        return postat;
    }

    public void setPostat(boolean postat) {
        this.postat = postat;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Paragrafe> getParagrafe() {
        return paragrafe;
    }

    public void setParagrafe(List<Paragrafe> paragrafe) {
        this.paragrafe = paragrafe;
    }

}
