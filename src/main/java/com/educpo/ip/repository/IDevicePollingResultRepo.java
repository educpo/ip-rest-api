package com.educpo.ip.repository;

import com.educpo.ip.entity.DevicePollingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDevicePollingResultRepo extends JpaRepository<DevicePollingResult, Long> {

}
