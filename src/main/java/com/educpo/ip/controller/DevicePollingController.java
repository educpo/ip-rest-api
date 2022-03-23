package com.educpo.ip.controller;

import com.educpo.ip.entity.DevicePollingInformation;
import com.educpo.ip.repository.IDevicePollingInformationRepo;
import com.educpo.ip.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DevicePollingController {

    @Autowired
    AsyncService asyncService;

    @Autowired
    IDevicePollingInformationRepo devicePollingInformationRepo;

    @GetMapping("/device-polling-information")
    public ResponseEntity<List<DevicePollingInformation>> getAllDevicePollingInformation(){
        try {
            List<DevicePollingInformation> list = devicePollingInformationRepo.findAll();
            if (list.isEmpty() || list.size() == 0){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/device-polling-information/{id}")
    public ResponseEntity<DevicePollingInformation> getDevicePollingInformation(@PathVariable Long id){
        Optional<DevicePollingInformation> devicePollingInformation = devicePollingInformationRepo.findById(id);
        if (devicePollingInformation.isPresent()){
            return new ResponseEntity<>(devicePollingInformation.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/device-polling-information")
    public ResponseEntity<List<DevicePollingInformation>> saveDevicePollingInformation(@RequestBody List<DevicePollingInformation> devicePollingInformation){
        try {
            List<DevicePollingInformation> saved = devicePollingInformationRepo.saveAll(devicePollingInformation);
            saved.stream().forEach(device -> asyncService.executeAsynchronously(device.getId()));
            return new ResponseEntity<List<DevicePollingInformation>>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/device-polling-information")
    public ResponseEntity<DevicePollingInformation> updateDevicePollingInformation(@RequestBody DevicePollingInformation devicePollingInformation){
        try {
            return new ResponseEntity<>(devicePollingInformationRepo.save(devicePollingInformation), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/device-polling-information/{id}")
    public ResponseEntity<HttpStatus> deleteDevicePollingInformation(@PathVariable Long id){
        try {
            Optional<DevicePollingInformation> devicePollingInformation = devicePollingInformationRepo.findById(id);
            if (devicePollingInformation.isPresent()){
                devicePollingInformationRepo.delete(devicePollingInformation.get());
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
