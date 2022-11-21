package com.example.json.carDealer.services.impl;

import com.example.json.carDealer.repositories.PartRepository;
import com.example.json.carDealer.services.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, Gson gson) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }
}
