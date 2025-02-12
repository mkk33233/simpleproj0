package com.example.agriculturesystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")//数据库表 实体类名称不一致需要注释
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataType;  // e.g., temperature, humidity
    private float value;
    private LocalDateTime timestamp;

    @ManyToOne

    @JoinColumn(name = "device_id")
    private Device device;

    // 添加状态字段，用于存储数据的状态
    private String status; // 状态字段，例如：正常、警告

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    // 新增 status getter 和 setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
