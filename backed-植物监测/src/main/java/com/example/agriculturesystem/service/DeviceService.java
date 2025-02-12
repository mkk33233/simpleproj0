package com.example.agriculturesystem.service;

import com.example.agriculturesystem.exception.ResourceNotFoundException;
import com.example.agriculturesystem.model.Device;
//import com.example.agriculturesystem.model.DeviceStatus;  // 引入 DeviceStatus 枚举
import com.example.agriculturesystem.model.SensorData;
import com.example.agriculturesystem.model.UserDevice;
import com.example.agriculturesystem.repository.DeviceRepository;
import com.example.agriculturesystem.repository.SensorDataRepository;
import com.example.agriculturesystem.repository.UserDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    @Autowired
    private SensorDataRepository sensorDataRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserDeviceRepository userdeviceRepository;
    // 获取设备信息
    public Device getDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    }



    // 获取所有设备信息
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // 添加新设备
    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    // 更新设备信息
    public Device updateDevice(Long deviceId, Device device) {
        Device existingDevice = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));

        existingDevice.setName(device.getName());
        existingDevice.setStatus(device.getStatus());  // 假设 Device 有 status 字段
        // 更新其他字段

        return deviceRepository.save(existingDevice);
    }

    // 删除设备
    public void deleteDevice(Long deviceId) {
        Device existingDevice = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        deviceRepository.delete(existingDevice);
    }

    // 控制设备状态
    public void controlDevice(Long deviceId, String status) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));


        device.setStatus(status);
        deviceRepository.save(device);
    }

    public List<Device> getDevicesByIds(List<Integer> deviceIds) {
        // 查询所有设备ID在给定列表中的设备信息
        return deviceRepository.findByIdIn(deviceIds);
    }
    // 通过用户名获取设备
    public List<Device> getDevicesByUsername(String username) {
        // 获取与用户名相关的设备记录
        List<UserDevice> userDevices = userdeviceRepository.findByUserUsername(username);

        // 如果没有找到设备，直接返回空列表
        if (userDevices.isEmpty()) {
            return new ArrayList<>();
        }

        // 提取设备ID并转换为 List<Long>
        List<Long> deviceIds = userDevices.stream()
                .map(userDevice -> userDevice.getDevice().getId())  // 确保返回 Long 类型
                .collect(Collectors.toList());

        // 根据设备ID查询设备信息
        return deviceRepository.findAllById(deviceIds);  // 传入 List<Long>
    }
    // 获取指定设备的历史数据
    public List<SensorData> getHistoryDataByDeviceId(Long deviceId) {
        return sensorDataRepository.findByDeviceId(deviceId);
    }

    // 获取指定设备在特定时间范围内的历史数据
    public List<SensorData> getHistoryDataByDeviceIdAndTimeRange(Long deviceId, LocalDateTime start, LocalDateTime end) {
        return sensorDataRepository.findByDeviceIdAndTimestampBetween(deviceId, start, end);
    }

    public UserDevice addud(UserDevice ud) {
        return  userdeviceRepository.save(ud);
    }

    public List<Device> findbycropid(Long cropid) {
        return deviceRepository.findByCropId(cropid);
    }
}
