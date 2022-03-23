package com.educpo.ip.repository;

import com.educpo.ip.entity.DevicePollingInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDevicePollingInformationRepo extends JpaRepository<DevicePollingInformation, Long> {

}