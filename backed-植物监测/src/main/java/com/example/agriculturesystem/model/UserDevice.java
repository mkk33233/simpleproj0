package com.example.agriculturesystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userdevice")  // 显式指定数据库表名
public class UserDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 外键：指向 User 表的 username
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    // 外键：指向 Device 表的 id
    @ManyToOne
    @JoinColumn(name = "ded", referencedColumnName = "id", nullable = false)
    private Device device;

    // 构造函数
    public UserDevice() {}

    public UserDevice(User user, Device device) {
        this.user = user;
        this.device = device;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "UserDevice{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", device=" + device.getId() +
                '}';
    }
}
