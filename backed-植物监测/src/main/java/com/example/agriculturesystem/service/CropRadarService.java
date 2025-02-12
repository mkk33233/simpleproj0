package com.example.agriculturesystem.service;

import com.example.agriculturesystem.model.SensorData;
import com.example.agriculturesystem.repository.CropRepository;
import com.example.agriculturesystem.repository.SensorDataRepository;
import com.example.agriculturesystem.repository.UserCropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CropRadarService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private UserCropRepository usercropRepository;
    /**
     * 获取所有植物的雷达状态
     * @return 每个植物ID及其是否为红点的状态
     */
    public Map<Long, Boolean> getAllRadarStatuses(String username) {
        Map<Long, Boolean> radarStatuses = new HashMap<>();

        List<Long> cropIds1 = cropRepository.find();
        List<Long> cropIds2 = usercropRepository.findIds(username);
        System.out.println(cropIds1);
        System.out.println(cropIds2);
        System.out.println("↑↑sdasdsadsa");
        // 假设这是从数据库中获取的两个列表

        // 使用 Set 来计算交集
        Set<Long> set1 = new HashSet<>(cropIds1);
        Set<Long> set2 = new HashSet<>(cropIds2);

        // 计算交集
        set1.retainAll(set2);

        // 将交集转换回 List
        List<Long> cropIds = set1.stream().collect(Collectors.toList());
     System.out.println(cropIds+"----------------------------- dadads");
        // findAllCropIds(); // 获取所有植物ID

        for (Long cropId : cropIds) {
            List<SensorData> s = cropRepository.findByCropId(cropId);
            long warningCount = s.stream() // 将列表转换为流
                    .sorted((sd1, sd2) -> sd2.getTimestamp().compareTo(sd1.getTimestamp())) // 按照timestamp降序排序
                    .limit(5) // 取前五条数据
                    .filter(sd -> "警告".equals(sd.getStatus())) // 过滤出status为"警告"的SensorData
                    .count(); // 统计数量
            radarStatuses.put(cropId, warningCount >=2); // true 为红点，false 为绿点
        }
        return radarStatuses;
    }

}
