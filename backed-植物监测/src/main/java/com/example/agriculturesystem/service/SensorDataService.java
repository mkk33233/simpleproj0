package com.example.agriculturesystem.service;

import com.example.agriculturesystem.exception.ResourceNotFoundException;
import com.example.agriculturesystem.model.Device;
import com.example.agriculturesystem.model.SensorData;
import com.example.agriculturesystem.repository.DeviceRepository;
import com.example.agriculturesystem.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class SensorDataService {

    @Autowired
    private  SensorDataRepository sensorDataRepository;

    @Autowired
    private  DeviceRepository deviceRepository;

    private  final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 获取所有传感器数据
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    // 获取单个传感器数据
    public SensorData getSensorDataById(Long sensorId) {
        return sensorDataRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("SensorData not found"));
    }

    // 获取某设备下的所有传感器数据
    public List<SensorData> getSensorDataByDeviceId(Long deviceId) {
        return sensorDataRepository.findByDeviceId(deviceId);
    }

    // 根据时间范围获取传感器数据
    public List<SensorData> getSensorDataByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return sensorDataRepository.findByTimestampBetween(startTime, endTime);
    }

    // 添加传感器数据
    public SensorData addSensorData(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    // 更新传感器数据
    public SensorData updateSensorData(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    // 删除传感器数据
    public void deleteSensorData(Long sensorId) {
        SensorData sensorData = sensorDataRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("SensorData not found"));
        sensorDataRepository.delete(sensorData);
    }


    //首次访问这个页面数据库最新数据

    @Transactional(readOnly = true)
    //设置为只读防止jpa插入更新新数据
    public ResponseEntity<List<Map<String, Object>>> Latestsensordata(Long deviceId) {

        // 获取指定设备
        Device device = deviceRepository.findById(deviceId).orElse(null);
        if (device == null) {
            System.out.println("设备未找到！");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }




        // 获取该设备的所有传感器数据并按时间降序排列
        List<SensorData> sensorDataList = sensorDataRepository.findByDeviceId(deviceId);
        sensorDataList.sort((sd1, sd2) -> sd2.getTimestamp().compareTo(sd1.getTimestamp()));  // 按时间降序排序

        // 如果设备类型是 "综合"，则获取最新的两条记录；否则，只取最新的一个
        List<SensorData> dataToProcess;
        if ("综合".equals(device.getType()) && sensorDataList.size() > 1) {
            dataToProcess = sensorDataList.subList(0, 2);  // 获取最新的两条数据
        } else {
            dataToProcess = sensorDataList.subList(0, Math.min(1, sensorDataList.size()));  // 获取最新的一条数据
        }

        // 返回给前端
        List<Map<String, Object>> responseData = new ArrayList<>();
        for (SensorData latestSensorData : dataToProcess) {


            if(latestSensorData.getDataType().equals("土质PH") ){
                if (latestSensorData.getValue() > 11){ System.out.println("土地偏碱性");latestSensorData.setStatus("土质偏碱");}
                else if(latestSensorData.getValue() <=5) {System.out.println("土质偏酸");
                    latestSensorData.setStatus("土质偏酸");}
                else{ System.out.println("土地酸碱度正常"); latestSensorData.setStatus("土质正常");}


            }
            else if (latestSensorData.getValue() > 50.0) {
                System.out.println("警告！" + latestSensorData.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + latestSensorData.getDataType() + " 数据超标：" + latestSensorData.getValue() + '\n');
                latestSensorData.setStatus("警告");
            } else {
                System.out.println(latestSensorData.getDataType() + " 数据正常：" + latestSensorData.getValue() + '\n');
                latestSensorData.setStatus("正常");
            }





            Map<String, Object> sensorMap = new HashMap<>();
            sensorMap.put("value", latestSensorData.getValue());
            sensorMap.put("DataType", latestSensorData.getDataType());
            sensorMap.put("timestamp", latestSensorData.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            sensorMap.put("status", latestSensorData.getStatus());
            responseData.add(sensorMap);
        }
        System.out.println("---------------------");
        //System.out.println(ResponseEntity.ok(responseData));
        System.out.println("-=----------------");
        // 发送响应给前端（可以用 REST Controller）
        // 假设使用的是 Spring Boot，返回的类型是 List<Map<String, Object>>
        return ResponseEntity.ok(responseData);
    }








    // 触发单个传感器检测
    public ResponseEntity<List<Map<String, Object>>>   triggerSensorCheck(Long deviceId) {

        // 获取指定设备的传感器数据
        Device device = deviceRepository.findById(deviceId).orElse(null);
        if (device == null) {
            System.out.println("设备未找到！");
            return null ;
        }
        System.out.println("开始执行" + device.getName() + "数据检测...");

        // 获取当前时间戳，作为最新检测时间
        LocalDateTime latestDetectionTime = LocalDateTime.now();
        String formattedTime = latestDetectionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("当前时间：" + formattedTime + "     开始检测");

        // 进行传感器数据检测
        System.out.println("设备 " + device.getName() + "（ID: " + device.getId() + "）的检测结果（最新检测时间：" + formattedTime + "）：");

        // 获取该设备的所有传感器数据并按时间降序排列
        List<SensorData> sensorDataList = sensorDataRepository.findByDeviceId(deviceId);
        sensorDataList.sort((sd1, sd2) -> sd2.getTimestamp().compareTo(sd1.getTimestamp()));  // 按时间降序排序

        // 如果设备类型是 "综合"，则获取最新的两条记录；否则，只取最新的一个
        List<SensorData> dataToProcess;
        if ("综合".equals(device.getType()) && sensorDataList.size() > 1) {
            dataToProcess = sensorDataList.subList(0, 2);  // 获取最新的两条数据
        } else {
            dataToProcess = sensorDataList.subList(0, Math.min(1, sensorDataList.size()));  // 获取最新的一条数据
        }

        // 返回给前端
        List<Map<String, Object>> responseData = new ArrayList<>();
        for (SensorData latestSensorData : dataToProcess) {


            if(latestSensorData.getDataType().equals("土质PH") ){
                if (latestSensorData.getValue() > 11){ System.out.println("土地偏碱性");latestSensorData.setStatus("土质偏碱");}
                else if(latestSensorData.getValue() <=5) {System.out.println("土质偏酸");
                    latestSensorData.setStatus("土质偏酸");}
                else{ System.out.println("土地酸碱度正常"); latestSensorData.setStatus("土质正常");}


            }
            else if (latestSensorData.getValue() > 50.0) {
                System.out.println("警告！" + latestSensorData.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + latestSensorData.getDataType() + " 数据超标：" + latestSensorData.getValue() + '\n');
                latestSensorData.setStatus("警告");
            } else {
                System.out.println(latestSensorData.getDataType() + " 数据正常：" + latestSensorData.getValue() + '\n');
                latestSensorData.setStatus("正常");
            }

            // 保存处理后的传感器数据
            sensorDataRepository.save(latestSensorData);





            Map<String, Object> sensorMap = new HashMap<>();
            sensorMap.put("value", latestSensorData.getValue());
            sensorMap.put("DataType", latestSensorData.getDataType());
            sensorMap.put("timestamp", latestSensorData.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            sensorMap.put("status", latestSensorData.getStatus());

            responseData.add(sensorMap);
        }

        // 发送响应给前端（可以用 REST Controller）
        // 假设使用的是 Spring Boot，返回的类型是 List<Map<String, Object>>
        return ResponseEntity.ok(responseData);

    }


    //单个插入传感器数据

    public void collectSensorData111(long deviceId) {
        Random random = new Random();
        LocalDateTime timestamp = LocalDateTime.now();

        // 获取设备信息
        Device device = deviceRepository.findById(deviceId).get();

        String type=device.getType();
        // 根据type进行传感器测

        if (type.equals("温度")) {
            float temperature = 20 + random.nextFloat() * 10; // 生成 20 到 30 之间的随机数
            saveSensorData(device, "温度", temperature, timestamp);
        }
        if (type.equals("湿度")) {
            float humidity = 50 + random.nextFloat() * 20; // 生成 50 到 70 之间的随机数
            saveSensorData(device, "湿度", humidity, timestamp);
        }
        if (type.equals("水质")) {
            float waterQuality = 0 + random.nextFloat() * 100; // 生成 0 到 100 之间的随机数
            saveSensorData(device, "水质", waterQuality, timestamp);
        }
        if (type.equals("综合")) {
            float yangfen = 0 + random.nextFloat() * 100; // 生成 0 到 100 之间的随机数
            float tuzhi = 0 + random.nextFloat() * 14; // 生成 0 到 100 之间的随机数
            saveSensorData(device, "养分", yangfen, timestamp);
            saveSensorData(device, "土质PH", tuzhi, timestamp);
        }



        System.out.println("模拟传感器数据并插入数据库完成！");
    }





















    // 自动检测传感器数据并按设备分组输出检测结果
    public void detectSensorData() {
        System.out.println("开始执行传感器数据检测...");

        // 获取当前时间戳，作为最新检测时间
        LocalDateTime latestDetectionTime = LocalDateTime.now();
        String formattedTime = latestDetectionTime.format(formatter);
        System.out.println("当前时间：" + formattedTime + "     开始检测");

        // 获取所有状态为 "active" 的设备
        List<Device> activeDevices = deviceRepository.findByStatus("active");

        if (activeDevices.isEmpty()) {
            System.out.println("没有活跃设备进行检测");
            return;
        }

        // 按设备分组传感器数据并进行检测
        for (Device device : activeDevices) {
            System.out.println("设备 " + device.getName() + "（ID: " + device.getId() + "）的检测结果（最新检测时间：" + formattedTime + "）：");

            // 获取该设备所有的传感器数据并按时间降序排列
            List<SensorData> sensorDataList = sensorDataRepository.findByDeviceId(device.getId());
            sensorDataList.sort((sd1, sd2) -> sd2.getTimestamp().compareTo(sd1.getTimestamp()));

            // 输出每个传感器的检测结果
            for (SensorData sensorData : sensorDataList) {
                LocalDateTime sensorDetectionTime = sensorData.getTimestamp();
                String formattedSensorDetectionTime = sensorDetectionTime.format(formatter);

                System.out.println("  检测时间: " + formattedSensorDetectionTime);
                System.out.println("  传感器类型: " + sensorData.getDataType());
                System.out.println("  数据值: " + sensorData.getValue());

                // 假设阈值为 50.0，进行超标检测

                if(sensorData.getDataType().equals("土质PH") ){
                    if (sensorData.getValue() > 11){ System.out.println("土地偏碱性");sensorData.setStatus("土质偏碱");}
                       else if(sensorData.getValue() <=5) {System.out.println("土质偏酸");
                        sensorData.setStatus("土质偏酸");}
                       else{ System.out.println("土地酸碱度正常"); sensorData.setStatus("土质正常");}


                }
                else if (sensorData.getValue() > 50.0) {
                    System.out.println("警告！" + formattedSensorDetectionTime + " " + sensorData.getDataType() + " 数据超标：" + sensorData.getValue() + '\n');
                    sensorData.setStatus("警告");
                } else {
                    System.out.println(sensorData.getDataType() + " 数据正常：" + sensorData.getValue() + '\n');
                    sensorData.setStatus("正常");
                }

                // 保存处理后的传感器数据
                sensorDataRepository.save(sensorData);
            }

            System.out.println("设备 " + device.getName() + "（ID: " + device.getId() + "）检测完成。\n");
        }

        // 所有检测完成
        System.out.println("所有传感器数据检测完成。");
    }

    // 模拟收集传感器数据并插入数据库
    public void collectSensorData() {
        // 设备 ID 和对应的传感器数据类型

        Random random = new Random();
        LocalDateTime timestamp = LocalDateTime.now();

        // 遍历所有设备
        for (Device device : deviceRepository.findAll()) {
            // 获取设备信息


            // 只对状态为 "active" 的设备进行数据采集
            if ("active".equals(device.getStatus())) {
                if (device.getType().equals("温度")) {
                    float temperature = 20 + random.nextFloat() * 10; // 生成 20 到 30 之间的随机数
                    saveSensorData(device, "温度", temperature, timestamp);
                }
                if (device.getType().equals("湿度")) {
                    float humidity = 50 + random.nextFloat() * 20; // 生成 50 到 70 之间的随机数
                    saveSensorData(device, "湿度", humidity, timestamp);
                }
                if (device.getType().equals("水质")) {
                    float waterQuality = 0 + random.nextFloat() * 100; // 生成 0 到 100 之间的随机数
                    saveSensorData(device, "水质", waterQuality, timestamp);
                }
                if (device.getType().equals("综合")) {
                    float yangfen = 0 + random.nextFloat() * 100; // 生成 0 到 100 之间的随机数
                    float tuzhi = 0 + random.nextFloat() * 14; // 生成 0 到 100 之间的随机数
                    saveSensorData(device, "养分", yangfen, timestamp);
                    saveSensorData(device, "土质PH", tuzhi, timestamp);
                }
            }
        }

        System.out.println("模拟传感器数据并插入数据库完成！");
    }







    // 提取插入数据的公共方法
    private void saveSensorData(Device device, String dataType, float value, LocalDateTime timestamp) {
        SensorData sensorData = new SensorData();
        sensorData.setDataType(dataType);
        sensorData.setValue(value);
        sensorData.setTimestamp(timestamp);
        sensorData.setDevice(device);
        sensorDataRepository.save(sensorData);
    }
}
