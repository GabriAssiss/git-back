package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.CommonArea;
import com.cit.backend.domain.entity.CommonAreaSchedule;
import com.cit.backend.domain.repository.CommonAreaScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonAreaScheduleService {
    @Autowired
    private CommonAreaScheduleRepository commonAreaScheduleRepository;

    public List<CommonAreaSchedule> saveAll(List<CommonAreaSchedule> commonAreaSchedules, CommonArea commonArea) {
        commonAreaSchedules.forEach(schedule -> schedule.setCommonArea(commonArea));
        return commonAreaScheduleRepository.saveAll(commonAreaSchedules);
    }
}
