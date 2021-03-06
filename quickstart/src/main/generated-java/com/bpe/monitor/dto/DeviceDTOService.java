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

import com.bpe.monitor.domain.Account;
import com.bpe.monitor.domain.Device;
import com.bpe.monitor.domain.Device_;
import com.bpe.monitor.dto.support.PageRequestByExample;
import com.bpe.monitor.dto.support.PageResponse;
import com.bpe.monitor.repository.AccountRepository;
import com.bpe.monitor.repository.DeviceRepository;

/**
 * A simple DTO Facility for Device.
 */
@Service
public class DeviceDTOService {

    @Inject
    private DeviceRepository deviceRepository;
    @Inject
    private AccountDTOService accountDTOService;
    @Inject
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public DeviceDTO findOne(Long id) {
        return toDTO(deviceRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<DeviceDTO> complete(String query, int maxResults) {
        List<Device> results = deviceRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<DeviceDTO> findAll(PageRequestByExample<DeviceDTO> req) {
        Example<Device> example = null;
        Device device = toEntity(req.example);

        if (device != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(Device_.description.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(Device_.name.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(device, matcher);
        }

        Page<Device> page;
        if (example != null) {
            page = deviceRepository.findAll(example, req.toPageable());
        } else {
            page = deviceRepository.findAll(req.toPageable());
        }

        List<DeviceDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public DeviceDTO save(DeviceDTO dto) {
        if (dto == null) {
            return null;
        }

        final Device device;

        if (dto.isIdSet()) {
            Device deviceTmp = deviceRepository.findOne(dto.id);
            if (deviceTmp != null) {
                device = deviceTmp;
            } else {
                device = new Device();
                device.setId(dto.id);
            }
        } else {
            device = new Device();
        }

        device.setDescription(dto.description);

        device.setHeightAboveSeaLevel(dto.heightAboveSeaLevel);

        device.setLat(dto.lat);

        device.setLongitue(dto.longitue);

        device.setName(dto.name);

        if (dto.accountFk == null) {
            device.setAccountFk(null);
        } else {
            Account accountFk = device.getAccountFk();
            if (accountFk == null || (accountFk.getId().compareTo(dto.accountFk.id) != 0)) {
                device.setAccountFk(accountRepository.findOne(dto.accountFk.id));
            }
        }

        return toDTO(deviceRepository.save(device));
    }

    /**
     * Converts the passed device to a DTO.
     */
    public DeviceDTO toDTO(Device device) {
        return toDTO(device, 1);
    }

    /**
     * Converts the passed device to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param device
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public DeviceDTO toDTO(Device device, int depth) {
        if (device == null) {
            return null;
        }

        DeviceDTO dto = new DeviceDTO();

        dto.id = device.getId();
        dto.description = device.getDescription();
        dto.heightAboveSeaLevel = device.getHeightAboveSeaLevel();
        dto.lat = device.getLat();
        dto.longitue = device.getLongitue();
        dto.name = device.getName();
        if (depth-- > 0) {
            dto.accountFk = accountDTOService.toDTO(device.getAccountFk(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a Device.
     * Convenient for query by example.
     */
    public Device toEntity(DeviceDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a Device.
     * Convenient for query by example.
     */
    public Device toEntity(DeviceDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Device device = new Device();

        device.setId(dto.id);
        device.setDescription(dto.description);
        device.setHeightAboveSeaLevel(dto.heightAboveSeaLevel);
        device.setLat(dto.lat);
        device.setLongitue(dto.longitue);
        device.setName(dto.name);
        if (depth-- > 0) {
            device.setAccountFk(accountDTOService.toEntity(dto.accountFk, depth));
        }

        return device;
    }
}