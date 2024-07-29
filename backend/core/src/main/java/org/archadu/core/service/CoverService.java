package org.archadu.core.service;

import org.archadu.core.config.RabbitMqConfig;
import org.archadu.core.model.Cover;
import org.archadu.core.repository.CoverRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverService {
    private static final Logger log = LoggerFactory.getLogger(CoverService.class);
    private final CoverRepo coverRepo;
    @Autowired
    public CoverService(CoverRepo coverRepo) {
        this.coverRepo = coverRepo;
    }

    public Cover getCoverById(String id) {
        if(id == null || id.isEmpty()){
            return new Cover();
        }
        return coverRepo.findById(id).orElse(null);
    }

    public Cover getCoverByRelease(String release) {
        if(release == null || release.isEmpty()){
            return new Cover();
        }
        return coverRepo.findByRelease(release);
    }

    @RabbitListener(queues = RabbitMqConfig.COVER_QUEUE)
    public void saveCover(Cover cover) {
        if(cover == null){
            return;
        }
        log.info("Received cover: " + cover.getId());
        coverRepo.save(cover);
        log.info("Cover saved: " + cover.getId());
    }
}
