package com.educpo.ip.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "device_info")
@Setter  //para cada propriedade
@Getter
@ToString
public class DevicePollingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private  Integer pollInterval;


}
