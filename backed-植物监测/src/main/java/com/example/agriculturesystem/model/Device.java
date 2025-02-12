package com.example.agriculturesystem.model;

import jakarta.persistence.*;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    private String status;  // active / inactive


    @ManyToOne(fetch = FetchType.EAGER)  // 使用 EAGER 使得 crop 会立即加载
    @JoinColumn(name = "crop_id")
    private Crop crop;


    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
