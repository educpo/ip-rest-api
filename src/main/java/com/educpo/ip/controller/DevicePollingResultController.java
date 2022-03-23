package com.educpo.ip.controller;

import com.educpo.ip.entity.DevicePollingResult;
import com.educpo.ip.repository.IDevicePollingResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DevicePollingResultController {

    @Autowired
    IDevicePollingResultRepo devicePollingResultRepo;

    @GetMapping("/device-polling-result")
    public ResponseEntity<List<DevicePollingResult>> getAllDevicePollingResult(){
        try {
            List<DevicePollingResult> list = devicePollingResultRepo.findAll();
            if (list.isEmpty() || list.size() == 0){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/device-polling-result/{id}")
    public ResponseEntity<DevicePollingResult> getDevicePollingResult(@PathVariable Long id){
        Optional<DevicePollingResult> devicePollingResult = devicePollingResultRepo.findById(id);
        if (devicePollingResult.isPresent()){
            return new ResponseEntity<>(devicePollingResult.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/device-polling-result")
    public ResponseEntity<List<DevicePollingResult>> saveDevicePollingResult(@RequestBody List<DevicePollingResult> devicePollingResult){
        try {
            return new ResponseEntity<List<DevicePollingResult>>(devicePollingResultRepo.saveAll(devicePollingResult), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
