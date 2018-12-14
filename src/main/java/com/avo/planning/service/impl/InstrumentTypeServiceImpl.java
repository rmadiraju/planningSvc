package com.avo.planning.service.impl;

import com.avo.planning.domain.InstrumentType;
import com.avo.planning.repository.InstrumentRepository;
import com.avo.planning.repository.InstrumentTypeRepository;
import com.avo.planning.service.InstrumentTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Interface for managing InstrumentType.
 */
@Service
public class InstrumentTypeServiceImpl implements InstrumentTypeService {


    private final Logger log = LoggerFactory.getLogger(InstrumentTypeServiceImpl.class);

    private final InstrumentTypeRepository instrumentTypeRepository;


    public InstrumentTypeServiceImpl(InstrumentTypeRepository instrumentTypeRepository) {
        this.instrumentTypeRepository = instrumentTypeRepository;
    }

    @Override
    public InstrumentType save(InstrumentType instrumentType) {
        return instrumentTypeRepository.save(instrumentType);
    }

    @Override
    public Page<InstrumentType> findAll(Pageable pageable) {
        return instrumentTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<InstrumentType> findOne(String id) {
        return instrumentTypeRepository.findById(id);
    }

    @Override
    public void delete(String id) {
         instrumentTypeRepository.deleteById(id);

    }


}
