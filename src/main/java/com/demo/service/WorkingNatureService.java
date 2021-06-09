package com.demo.service;

import com.demo.model.WorkingNature;
import com.demo.repository.WorkingNatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingNatureService {

    @Autowired
    WorkingNatureRepository workingNatureRepository;

    public WorkingNature findWorkingNatureByName(String workingNature) {
        return workingNatureRepository.findWorkingNatureByName(workingNature);
    }

}
