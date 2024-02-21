package com.bdv.demo.beats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BeatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeatService.class);
    private final BeatRepository beatRepository;

    @Autowired
    public BeatService(BeatRepository beatRepository) {
        this.beatRepository = beatRepository;
    }

    public List<Beat> getAllBeats() {
        return beatRepository.findAll();
    }

    public Beat getBeatById(Long beatId) {
        if (beatId == null) {
            LOGGER.error("Attempted to get a beat with a null id");
            throw new IllegalArgumentException("Beat id cannot be null");
        }
        return beatRepository.findById(beatId).orElse(null);
    }

    public Beat createBeat(Beat beat) {
        if (beat == null) {
            LOGGER.error("Attempted to create a beat with a null object");
            throw new IllegalArgumentException("Beat object cannot be null");
        }
        return beatRepository.save(beat);
    }

    public Beat updateBeat(Long beatId, Beat beat) {
        if (beat == null || beatId == null) {
            LOGGER.error("Attempted to update a beat with a null object or id");
            throw new IllegalArgumentException("Beat object or id cannot be null");
        }
        return beatRepository.save(beat);
    }

    public boolean deleteBeat(Long beatId) {
        try {
            if (beatId == null) {
                LOGGER.error("Attempted to delete a beat with a null id");
                throw new IllegalArgumentException("Beat id cannot be null");
            }
            beatRepository.deleteById(beatId);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error deleting beat with id: " + beatId, e);
            return false;
        }
    }

    public List<Beat> findByTagsContaining(String tag) {
        if (tag == null) {
            LOGGER.error("Attempted to find beats with a null tag");
            throw new IllegalArgumentException("Tag cannot be null");
        }
        return beatRepository.findByTagsContaining(tag);
    }
}