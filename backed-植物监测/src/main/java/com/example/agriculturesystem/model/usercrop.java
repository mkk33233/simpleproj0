package com.example.agriculturesystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usercrop")
public class usercrop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "cropid", referencedColumnName = "id", nullable = false)
    private Crop crop;

    public usercrop() {}
    public usercrop( String username,String password,Crop crop) {
        this.username = username;
        this.password = password;
        this.crop=crop;
    }
    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
