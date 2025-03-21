package com.cit.backend.api.mapper;

import com.cit.backend.api.request.CommonAreaRequest;
import com.cit.backend.api.response.CommonAreaResponse;
import com.cit.backend.domain.entity.CommonArea;
import com.cit.backend.domain.service.CondominiumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class CommonAreaMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CondominiumService condominiumService;

    public CommonArea toCommonArea(CommonAreaRequest request) {
        CommonArea commonArea = modelMapper.map(request, CommonArea.class);
        if (request.getCondominiumId() != null)
            commonArea.setCondominium(condominiumService.findById(request.getCondominiumId()));
        return commonArea;
    }

    public CommonAreaResponse toCommonAreaResponse(CommonArea commonArea) {
        CommonAreaResponse response = modelMapper.map(commonArea, CommonAreaResponse.class);
        response.setCondominiumId(commonArea.getCondominium().getId());
        return response;
    }

    public List<CommonAreaResponse> toCommonAreaResponse(List<CommonArea> commonAreas) {
        return commonAreas.stream()
                .map(this::toCommonAreaResponse)
                .toList();
    }

    // TODO avaliar outras formas e decidir a mais apropriada
    public CommonArea fillNullFields(CommonArea current, CommonArea target) {
        List<Method> getters = Arrays.stream(CommonArea.class.getMethods()).filter(method -> method.getName().startsWith("get")).toList();
        List<Method> setters = Arrays.stream(CommonArea.class.getMethods()).filter(method -> method.getName().startsWith("set")).toList();
        getters.forEach(getter -> {
            try {
                if (getter.invoke(current) == null) {
                    String fieldName = getter.getName().substring(3);
                    Method setter = setters.stream().filter(method -> method.getName().substring(3).equals(fieldName)).findFirst().orElse(null);
                    if (setter != null) setter.invoke(current, getter.invoke(target));
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return current;
    }
}
