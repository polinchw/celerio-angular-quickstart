/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package com.bpe.monitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple DTO for Device.
 */
public class DeviceDTO {
    public Long id;
    public String description;
    public Float heightAboveSeaLevel;
    public Float lat;
    public Float longitue;
    public String name;
    public AccountDTO accountFk;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}