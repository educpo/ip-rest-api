package com.educpo.ip.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "device_result")
@Setter
@Getter
@ToString
public class DevicePollingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private Long lastSuccessCommTimestamp;
}
