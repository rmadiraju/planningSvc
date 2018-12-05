package com.avo.planning.service.impl;

import com.avo.planning.domain.Instrument;
import com.avo.planning.repository.AttributeRepository;
import com.avo.planning.repository.InstrumentRepository;
import com.avo.planning.service.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Instrument.
 */
@Service
public class InstrumentServiceImpl extends AbstractServiceImpl implements InstrumentService {

    private final Logger log = LoggerFactory.getLogger(InstrumentServiceImpl.class);

    private final InstrumentRepository instrumentRepository;


    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    /**
     * Save a instrument.
     *
     * @param instrument the entity to save
     * @return the persisted entity
     */
    @Override
    public Instrument save(Instrument instrument) {
        log.debug("Request to save Instrument : {}", instrument);

        instrument.getAttributes().forEach(a -> a.setId(attributeService.save(a).getId()));

        if (instrument.getSourceTemplate() != null)
            instrument.getSourceTemplate().setId(templateService.save(instrument.getSourceTemplate()).getId());

        Instrument inst = instrumentRepository.findFirstByName(instrument.getName());
        if (inst != null)
            instrument.setId(inst.getId());
        return instrumentRepository.save(instrument);
    }

    /**
     * Get all the instruments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Instrument> findAll(Pageable pageable) {
        log.debug("Request to get all Instruments");
        return instrumentRepository.findAll(pageable);
    }


    /**
     * Get one instrument by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Instrument> findOne(String id) {
        log.debug("Request to get Instrument : {}", id);
        return instrumentRepository.findById(id);
    }

    /**
     * Delete the instrument by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Instrument : {}", id);
        instrumentRepository.deleteById(id);
    }
}
