package com.bezkoder.spring.login.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "paragrafe")
public class Paragrafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String text;

    @NotBlank
    private String poza;

    private int ok;

    public Paragrafe() {
    }

    public Paragrafe(String text, String poza) {
        this.text = text;
        this.poza = poza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "Paragrafe{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", poza='" + poza + '\'' +
                '}';
    }
}
