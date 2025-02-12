package com.example.agriculturesystem.dto;

import java.time.LocalDateTime;

public class SensorDataDTO {
    private Long id;
    private String data_Type;  // 保持与实体类一致
    private float value;
    private LocalDateTime timestamp;
    private DeviceDTO device;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData_Type() {
        return data_Type;
    }

    public void setData_Type(String data_Type) {
        this.data_Type = data_Type;
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

    public DeviceDTO getDevice() {
        return device;
    }

    public void setDevice(DeviceDTO device) {
        this.device = device;
    }
}
