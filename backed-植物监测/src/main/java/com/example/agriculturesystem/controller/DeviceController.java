package com.example.agriculturesystem.controller;

import com.example.agriculturesystem.model.Device;
import com.example.agriculturesystem.model.SensorData;
import com.example.agriculturesystem.model.UserDevice;
import com.example.agriculturesystem.service.CropRadarService;
import com.example.agriculturesystem.service.DeviceService;
import com.example.agriculturesystem.service.SensorDataService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired private SensorDataService sensorDataService;



    @Autowired
   private CropRadarService cropRadarService;



    // 删除设备在 device 表中的记录
    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("deviceId") Long deviceId) {
        try {
            deviceService.deleteDevice(deviceId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






    @PutMapping ("/{deviceId}/control")
    public ResponseEntity<Device> controlDevice(@PathVariable Long deviceId, @RequestBody ControlRequest controlRequest) {
        String status = controlRequest.getStatus();
        deviceService.controlDevice(deviceId, status);

        // 获取更新后的设备信息
        Device updatedDevice = deviceService.getDeviceById(deviceId);

        // 返回设备的最新状态和作物信息
        return ResponseEntity.ok(updatedDevice);
    }

    // 内部静态类，用于接收请求体中的 status 信息
    public static class ControlRequest {
        private String status;

        // Getters and Setters
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    // 获取指定设备的历史数据，按类型分组
    @GetMapping("/{deviceId}/history/grouped")
    public Map<String, List<SensorData>> getGroupedDeviceHistoryData(@PathVariable Long deviceId) {
        List<SensorData> allData = deviceService.getHistoryDataByDeviceId(deviceId);

        // 按照数据类型（dataType）分组
        return allData.stream().collect(Collectors.groupingBy(SensorData::getDataType));
    }

//    // 获取设备历史数据
//    @GetMapping("/{deviceId}/history")
//    public List<SensorData> getDeviceHistoryData(@PathVariable Long deviceId) {
//        return deviceService.getHistoryDataByDeviceId(deviceId);
//    }

//    // 获取设备历史数据在指定时间范围内
//    @GetMapping("/{deviceId}/history/range")
//    public List<SensorData> getDeviceHistoryDataInRange(@PathVariable Long deviceId,
//                                                        @RequestParam String start,
//                                                        @RequestParam String end) {
//        LocalDateTime startDate = LocalDateTime.parse(start);
//        LocalDateTime endDate = LocalDateTime.parse(end);
//        return deviceService.getHistoryDataByDeviceIdAndTimeRange(deviceId, startDate, endDate);
//    }
    // 触发传感器检测
    @PostMapping("/{deviceId}/triggerSensorCheck")
    public ResponseEntity<List<Map<String, Object>>> triggerSensorCheck(@PathVariable Long deviceId) {
        try {
            // 调用设备服务层的方法进行传感器检测
            //记得要先创建senserservice对象
            sensorDataService.collectSensorData111(deviceId);
            ResponseEntity<List<Map<String, Object>>>  xx=sensorDataService.triggerSensorCheck(deviceId);

            return xx;
        } catch (Exception e) {
            return null;
        }
    }



// 触发传感器检测
    @PostMapping("/{deviceId}/latestDatas")
    public ResponseEntity<List<Map<String, Object>>> latestData(@PathVariable Long deviceId) {


        try {
            // 调用设备服务层的方法进行传感器检测
            //记得要先创建senserservice对象

            ResponseEntity<List<Map<String, Object>>>  xx=sensorDataService.Latestsensordata(deviceId);

            return xx;
        } catch (Exception e) {
            return null;
        }
    }




    @GetMapping("/username/{username}")
    public ResponseEntity<List<Device>> getDevicesByUsername(@PathVariable String username) {
        try {
            // 调用 Service 层获取设备信息
            List<Device> devices = deviceService.getDevicesByUsername(username);

            if (devices.isEmpty()) {
             return null; // 如果没有找到设备，返回 404
            }

            return ResponseEntity.ok(devices); // 返回设备信息
        } catch (Exception e) {
            return null; // 返回 500 错误
        }
    }
//
//    @GetMapping("/ids")
//    public List<Device> getDevicesByIds(@RequestParam String deviceIds) {
//        // 将逗号分隔的设备ID字符串转换为设备ID列表
//        List<Integer> deviceIdList = Arrays.stream(deviceIds.split(","))
//                .map(Integer::parseInt)   // 转换成 Integer 类型
//                .collect(Collectors.toList());  // 转为 List<Integer>
//
//        // 使用服务层来查询数据库中对应的设备
//        return deviceService.getDevicesByIds(deviceIdList);
//    }

    // 获取设备信息
    @GetMapping("/{deviceId}")
    public ResponseEntity<Device> getDevice(@PathVariable Long deviceId) {
        Device device = deviceService.getDeviceById(deviceId);
        return ResponseEntity.ok(device);
    }

    // 获取所有设备信息
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }
      @PostMapping("/ud")
      public ResponseEntity<UserDevice> addDevice(@RequestBody UserDevice ud) {

          UserDevice savedDevice = deviceService.addud(ud);
          return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
      }

    // 添加新设备
    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        Device savedDevice = deviceService.addDevice(device);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
    }

    // 更新设备
    @PutMapping("/{deviceId}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long deviceId, @RequestBody Device device) {
        Device updatedDevice = deviceService.updateDevice(deviceId, device);
        return ResponseEntity.ok(updatedDevice);
    }


    @GetMapping("/crop-radar/{username}")
    public Map<Long, Boolean> getAllRadarStatuses(@PathVariable String username) {
        return cropRadarService.getAllRadarStatuses(username);
    }

    // 更新设备
    @GetMapping("/bcrop/{cropid}")
    public ResponseEntity<List<Device>> updateDevice(@PathVariable Long cropid) {
        List<Device> devices = deviceService.findbycropid(cropid);
        System.out.println("SSSSS+DIVICES"+devices);
        return ResponseEntity.ok(devices);
    }












    private static final String SECRET_KEY = "212030317Mark131411";

    @GetMapping("/aa")
    public String getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("mkk ")) {

            String token = authorizationHeader.substring(4);  // 移除 "Bearer " 部分


               System.out.println(token+"token===-=-=-=------------------------------------------------------------------------------------");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();
                // 获取过期时间并进行验证
                Date expiration = claims.getExpiration();
                if (expiration.before(new Date())) {
                    // Token 已过期
                    throw new Exception("超过十秒了不能用了");
                }





                String username = claims.getSubject();
                return "token可用";
            } catch (Exception e) {
                return "token有错误 " + e.getMessage();
            }
        } else {
            return "token开头不对 或者为空";
        }
    }



}
