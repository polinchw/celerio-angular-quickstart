/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package com.bpe.monitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple DTO for AlarmRule.
 */
public class AlarmRuleDTO {
    public Long id;
    public Integer alarmType;
    public String emailToAlert;
    public Float high;
    public Float low;
    public AccountDTO accountFk;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}