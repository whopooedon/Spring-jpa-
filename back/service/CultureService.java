package com.project05.service;

import com.project05.repository.CultureRepository;
import com.project05.domain.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureService {
	
    @Autowired
    private CultureRepository cultureRepository; // Using CultureRepository instead of CultureMapper
    
    public List<Culture> findCultureByAddress(String district) {
        // Implement the logic to retrieve culture information for a given address
        return cultureRepository.findCultureByAddress(district);
    }
}
