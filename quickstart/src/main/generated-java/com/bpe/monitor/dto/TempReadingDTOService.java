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

import com.bpe.monitor.domain.Device;
import com.bpe.monitor.domain.TempReading;
import com.bpe.monitor.domain.TempReading_;
import com.bpe.monitor.dto.support.PageRequestByExample;
import com.bpe.monitor.dto.support.PageResponse;
import com.bpe.monitor.repository.DeviceRepository;
import com.bpe.monitor.repository.TempReadingRepository;

/**
 * A simple DTO Facility for TempReading.
 */
@Service
public class TempReadingDTOService {

    @Inject
    private TempReadingRepository tempReadingRepository;
    @Inject
    private DeviceDTOService deviceDTOService;
    @Inject
    private DeviceRepository deviceRepository;

    @Transactional(readOnly = true)
    public TempReadingDTO findOne(Long id) {
        return toDTO(tempReadingRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<TempReadingDTO> complete(String query, int maxResults) {
        List<TempReading> results = tempReadingRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<TempReadingDTO> findAll(PageRequestByExample<TempReadingDTO> req) {
        Example<TempReading> example = null;
        TempReading tempReading = toEntity(req.example);

        if (tempReading != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(TempReading_.tempType.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(tempReading, matcher);
        }

        Page<TempReading> page;
        if (example != null) {
            page = tempReadingRepository.findAll(example, req.toPageable());
        } else {
            page = tempReadingRepository.findAll(req.toPageable());
        }

        List<TempReadingDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public TempReadingDTO save(TempReadingDTO dto) {
        if (dto == null) {
            return null;
        }

        final TempReading tempReading;

        if (dto.isIdSet()) {
            TempReading tempReadingTmp = tempReadingRepository.findOne(dto.id);
            if (tempReadingTmp != null) {
                tempReading = tempReadingTmp;
            } else {
                tempReading = new TempReading();
                tempReading.setId(dto.id);
            }
        } else {
            tempReading = new TempReading();
        }

        tempReading.setDateRecorded(dto.dateRecorded);

        tempReading.setReading(dto.reading);

        tempReading.setTempType(dto.tempType);

        if (dto.deviceFk == null) {
            tempReading.setDeviceFk(null);
        } else {
            Device deviceFk = tempReading.getDeviceFk();
            if (deviceFk == null || (deviceFk.getId().compareTo(dto.deviceFk.id) != 0)) {
                tempReading.setDeviceFk(deviceRepository.findOne(dto.deviceFk.id));
            }
        }

        return toDTO(tempReadingRepository.save(tempReading));
    }

    /**
     * Converts the passed tempReading to a DTO.
     */
    public TempReadingDTO toDTO(TempReading tempReading) {
        return toDTO(tempReading, 1);
    }

    /**
     * Converts the passed tempReading to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param tempReading
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public TempReadingDTO toDTO(TempReading tempReading, int depth) {
        if (tempReading == null) {
            return null;
        }

        TempReadingDTO dto = new TempReadingDTO();

        dto.id = tempReading.getId();
        dto.dateRecorded = tempReading.getDateRecorded();
        dto.reading = tempReading.getReading();
        dto.tempType = tempReading.getTempType();
        if (depth-- > 0) {
            dto.deviceFk = deviceDTOService.toDTO(tempReading.getDeviceFk(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a TempReading.
     * Convenient for query by example.
     */
    public TempReading toEntity(TempReadingDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a TempReading.
     * Convenient for query by example.
     */
    public TempReading toEntity(TempReadingDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        TempReading tempReading = new TempReading();

        tempReading.setId(dto.id);
        tempReading.setDateRecorded(dto.dateRecorded);
        tempReading.setReading(dto.reading);
        tempReading.setTempType(dto.tempType);
        if (depth-- > 0) {
            tempReading.setDeviceFk(deviceDTOService.toEntity(dto.deviceFk, depth));
        }

        return tempReading;
    }
}