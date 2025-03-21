package com.cit.backend.api.mapper;

import com.cit.backend.api.request.LostAndFoundRequest;
import com.cit.backend.api.response.LostAndFoundResponse;
import com.cit.backend.domain.entity.CommonArea;
import com.cit.backend.domain.entity.LostAndFound;
import com.cit.backend.domain.service.LostAndFoundService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class LostAndFoundMapper {

    @Autowired
    private ModelMapper modelMapper;

    private LostAndFoundService lostAndFoundService;

    public LostAndFound toLostAndFound (LostAndFoundRequest lostObject) {
        return modelMapper.map(lostObject, LostAndFound.class);
    }

    public LostAndFoundResponse toLostAndFoundResponse(LostAndFound lostObject) {
        return modelMapper.map(lostObject, LostAndFoundResponse.class);
    }

    public List<LostAndFoundResponse> toLostAndFoundResponse(List <LostAndFound> lostObjects) {
        return lostObjects.stream().map(this::toLostAndFoundResponse)
                .toList();
    }

    public LostAndFound fillNullFields(LostAndFound current, LostAndFound target) {
        List<Method> getters = Arrays.stream(LostAndFound.class.getMethods()).filter(method -> method.getName().startsWith("get")).toList();
        List<Method> setters = Arrays.stream(LostAndFound.class.getMethods()).filter(method -> method.getName().startsWith("set")).toList();
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
