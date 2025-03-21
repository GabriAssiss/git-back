package com.cit.backend.api.mapper;

import com.cit.backend.api.request.ProfileRequest;
import com.cit.backend.domain.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Profile toProfile(ProfileRequest profile) {
        return modelMapper.map(profile, Profile.class);
    }
}
