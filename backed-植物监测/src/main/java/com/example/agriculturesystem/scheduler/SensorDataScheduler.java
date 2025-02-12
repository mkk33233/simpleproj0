package com.example.agriculturesystem.scheduler;

import com.example.agriculturesystem.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SensorDataScheduler {

    @Autowired
    private SensorDataService sensorDataService;

    // 每隔 1 小时执行一次（60 * 60 * 1000 毫秒）
    @Scheduled(fixedRate = 300,initialDelay = 300) // 1小时执行一次
    public void autoCollectAndDetectSensorData() {
        // 1. 自动收集并插入新的传感器数据
        System.out.println("自动收集传感器数据...");
        sensorDataService.collectSensorData();

        // 2. 然后自动检测传感器数据
        System.out.println("自动检测传感器数据...");
        sensorDataService.detectSensorData();
    }

    // 每天凌晨 1 点执行一次
    @Scheduled(cron = "0 0 1 * * ?")
    public void collectAndDetectDailySensorData() {
        // 1. 每天凌晨收集并插入新的传感器数据
        System.out.println("每天凌晨收集传感器数据...");
        sensorDataService.collectSensorData();

        // 2. 然后检测传感器数据
        System.out.println("自动检测传感器数据（每天一次）...");
        sensorDataService.detectSensorData();
    }
}
