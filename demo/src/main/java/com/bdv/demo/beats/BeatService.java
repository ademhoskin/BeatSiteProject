package com.bdv.demo.beats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BeatService {

    private final BeatRepository beatRepository;

    @Autowired
    public BeatService(BeatRepository beatRepository) {
        this.beatRepository = beatRepository;
    }

    public List<Beat> getAllBeats() {
        return beatRepository.findAll();
    }

    public Beat getBeatById(Long beatId) {
        return beatRepository.findById(beatId).orElse(null);
    }

    public Beat createBeat(Beat beat) {
        if (beatRepository.existsById(beat.getBeatId())) {
            return null;
        }
        return beatRepository.save(beat);
    }

    public Beat updateBeat(Long beatId, Beat beat) {
        Beat existingBeat = beatRepository.findById(beatId).orElse(null);
        if (existingBeat != null) {
            return beatRepository.save(beat);
        } else {
            return null;
        }
    }

    public boolean deleteBeat(Long beatId) {
        if (!beatRepository.existsById(beatId)) {
            return false;
        }
        beatRepository.deleteById(beatId);
        return true;
    }

    public List<Beat> findByTagsContaining(String tag) {
        return beatRepository.findByTagsContaining(tag);
    }

}
