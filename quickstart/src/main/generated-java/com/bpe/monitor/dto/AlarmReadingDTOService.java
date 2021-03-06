/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.bpe.monitor.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpe.monitor.domain.AlarmReading;
import com.bpe.monitor.domain.AlarmReading_;
import com.bpe.monitor.domain.AlarmRule;
import com.bpe.monitor.domain.Device;
import com.bpe.monitor.dto.support.PageRequestByExample;
import com.bpe.monitor.dto.support.PageResponse;
import com.bpe.monitor.repository.AlarmReadingRepository;
import com.bpe.monitor.repository.AlarmRuleRepository;
import com.bpe.monitor.repository.DeviceRepository;

/**
 * A simple DTO Facility for AlarmReading.
 */
@Service
public class AlarmReadingDTOService {

    @Inject
    private AlarmReadingRepository alarmReadingRepository;
    @Inject
    private DeviceDTOService deviceDTOService;
    @Inject
    private DeviceRepository deviceRepository;
    @Inject
    private AlarmRuleDTOService alarmRuleDTOService;
    @Inject
    private AlarmRuleRepository alarmRuleRepository;

    @Transactional(readOnly = true)
    public AlarmReadingDTO findOne(Long id) {
        return toDTO(alarmReadingRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<AlarmReadingDTO> complete(String query, int maxResults) {
        List<AlarmReading> results = alarmReadingRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<AlarmReadingDTO> findAll(PageRequestByExample<AlarmReadingDTO> req) {
        Example<AlarmReading> example = null;
        AlarmReading alarmReading = toEntity(req.example);

        if (alarmReading != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
            ;

            example = Example.of(alarmReading, matcher);
        }

        Page<AlarmReading> page;
        if (example != null) {
            page = alarmReadingRepository.findAll(example, req.toPageable());
        } else {
            page = alarmReadingRepository.findAll(req.toPageable());
        }

        List<AlarmReadingDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public AlarmReadingDTO save(AlarmReadingDTO dto) {
        if (dto == null) {
            return null;
        }

        final AlarmReading alarmReading;

        if (dto.isIdSet()) {
            AlarmReading alarmReadingTmp = alarmReadingRepository.findOne(dto.id);
            if (alarmReadingTmp != null) {
                alarmReading = alarmReadingTmp;
            } else {
                alarmReading = new AlarmReading();
                alarmReading.setId(dto.id);
            }
        } else {
            alarmReading = new AlarmReading();
        }

        alarmReading.setDateRecorded(dto.dateRecorded);

        if (dto.deviceFk == null) {
            alarmReading.setDeviceFk(null);
        } else {
            Device deviceFk = alarmReading.getDeviceFk();
            if (deviceFk == null || (deviceFk.getId().compareTo(dto.deviceFk.id) != 0)) {
                alarmReading.setDeviceFk(deviceRepository.findOne(dto.deviceFk.id));
            }
        }

        if (dto.ruleFk == null) {
            alarmReading.setRuleFk(null);
        } else {
            AlarmRule ruleFk = alarmReading.getRuleFk();
            if (ruleFk == null || (ruleFk.getId().compareTo(dto.ruleFk.id) != 0)) {
                alarmReading.setRuleFk(alarmRuleRepository.findOne(dto.ruleFk.id));
            }
        }

        return toDTO(alarmReadingRepository.save(alarmReading));
    }

    /**
     * Converts the passed alarmReading to a DTO.
     */
    public AlarmReadingDTO toDTO(AlarmReading alarmReading) {
        return toDTO(alarmReading, 1);
    }

    /**
     * Converts the passed alarmReading to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param alarmReading
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public AlarmReadingDTO toDTO(AlarmReading alarmReading, int depth) {
        if (alarmReading == null) {
            return null;
        }

        AlarmReadingDTO dto = new AlarmReadingDTO();

        dto.id = alarmReading.getId();
        dto.dateRecorded = alarmReading.getDateRecorded();
        if (depth-- > 0) {
            dto.deviceFk = deviceDTOService.toDTO(alarmReading.getDeviceFk(), depth);
            dto.ruleFk = alarmRuleDTOService.toDTO(alarmReading.getRuleFk(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a AlarmReading.
     * Convenient for query by example.
     */
    public AlarmReading toEntity(AlarmReadingDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a AlarmReading.
     * Convenient for query by example.
     */
    public AlarmReading toEntity(AlarmReadingDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        AlarmReading alarmReading = new AlarmReading();

        alarmReading.setId(dto.id);
        alarmReading.setDateRecorded(dto.dateRecorded);
        if (depth-- > 0) {
            alarmReading.setDeviceFk(deviceDTOService.toEntity(dto.deviceFk, depth));
            alarmReading.setRuleFk(alarmRuleDTOService.toEntity(dto.ruleFk, depth));
        }

        return alarmReading;
    }
}