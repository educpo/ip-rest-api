package com.educpo.ip.threads;


import com.educpo.ip.entity.DevicePollingInformation;
import com.educpo.ip.entity.DevicePollingResult;
import com.educpo.ip.repository.IDevicePollingInformationRepo;
import com.educpo.ip.repository.IDevicePollingResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Optional;

@Component
@Scope("prototype")
public class PingIp implements Runnable{

    Long id;
    @Autowired
    IDevicePollingInformationRepo devicePollingInformationRepo;

    @Autowired
    IDevicePollingResultRepo devicePollingResultRepo;


    public PingIp(Long id){

        this.id = id;
    }


    @Override
    public void run() {

        System.out.println(id + " is running");
        try{
            Optional<DevicePollingInformation> device = devicePollingInformationRepo.findById(this.id);
            if (device.isPresent()){
                String ip = device.get().getIp();
                Integer pollingInterval = device.get().getPollInterval();
                while (true){
                    InetAddress address = InetAddress.getByName(ip);
                    boolean reachable = address.isReachable(pollingInterval);
                    if (reachable){
                        DevicePollingResult devicePollingResult = new DevicePollingResult();
                        devicePollingResult.setIp(ip);
                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        devicePollingResult.setLastSuccessCommTimestamp(time.getTime());
                        devicePollingResultRepo.save(devicePollingResult);
                    }
                    Thread.sleep(pollingInterval*1000);
                }
            }else {
                System.out.println("CANT FIND IN DB");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(id + " is running");
    }
}