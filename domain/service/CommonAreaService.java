package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.repository.CommonAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommonAreaService {
    @Autowired
    private CommonAreaRepository commonAreaRepository;

    @Autowired
    private CommonAreaScheduleService commonAreaScheduleService;

    public CommonArea save(CommonArea commonArea) {
        CommonArea result = commonAreaRepository.save(commonArea);
        commonAreaScheduleService.saveAll(commonArea.getSchedule().stream().toList(), commonArea);
        return result;
    }

    public List<CommonArea> getCommonAreasByCondominiumId(long condominiumId) {
        return commonAreaRepository.findAllByCondominiumId(condominiumId);
    }

    public List<CommonArea> getCommonAreas(Resident resident) {
        Condominium condominium = resident.getApartment().getUnit().getBlock().getCondominium();
        return getCommonAreasByCondominiumId(condominium.getId());
    }

    public List<CommonArea> getCommonAreas() {
        return commonAreaRepository.findAll();
    }

    public CommonArea findById(Long id) {
        return commonAreaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        commonAreaRepository.deleteById(id);
    }
}
