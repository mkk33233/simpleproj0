package com.example.agriculturesystem.repository;

import com.example.agriculturesystem.model.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, Integer> {

    // 根据用户名查询 UserDevice 记录
    List<UserDevice> findByUserUsername(String username);




}
