package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.LostAndFound;
import com.cit.backend.domain.repository.LostAndFoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LostAndFoundService {

    @Autowired
    private LostAndFoundRepository lostAndFoundRepository;

    public LostAndFound save(LostAndFound lostObject) {
        return lostAndFoundRepository.save(lostObject);
    }

    public LostAndFound findById(Long id) {
        return lostAndFoundRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        lostAndFoundRepository.deleteById(id);
    }

    public List<LostAndFound> getLostObjects() {
        return lostAndFoundRepository.findAll();
    }
}
