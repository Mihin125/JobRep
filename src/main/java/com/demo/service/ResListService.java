package com.demo.service;

import com.demo.model.RedList;
import com.demo.repository.BlackListRepository;
import com.demo.repository.RedListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResListService {
    @Autowired
    RedListRepository redListRepository;

    public void save(RedList blackList){
        redListRepository.save(blackList);
    }
    public RedList findByEmail(String email){
        return redListRepository.findByEmail(email);
    }
}
