//package com.example.agriculturesystem.model;
//
//public enum DeviceStatus {
//    ACTIVE("active"),
//    INACTIVE("inactive");
//
//    private final String status;
//
//    DeviceStatus(String status) {
//        this.status = status;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public static DeviceStatus fromString(String status) {
//        for (DeviceStatus deviceStatus : DeviceStatus.values()) {
//            if (deviceStatus.status.equalsIgnoreCase(status)) {
//                return deviceStatus;
//            }
//        }
//        throw new IllegalArgumentException("Unknown status: " + status);
//    }
//}
