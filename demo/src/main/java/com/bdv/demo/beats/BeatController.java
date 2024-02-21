package com.bdv.demo.beats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beats")
public class BeatController {

    private final BeatService beatService;

    @Autowired
    public BeatController(BeatService beatService) {
        this.beatService = beatService;
    }

    @GetMapping
    public ResponseEntity<List<Beat>> getAllBeats() {
        List<Beat> beats = beatService.getAllBeats();
        return new ResponseEntity<>(beats, HttpStatus.OK);
    }

    @GetMapping("/{beatId}")
    public ResponseEntity<Beat> getBeatById(@PathVariable Long beatId) {
        Beat beat = beatService.getBeatById(beatId);
        if (beat != null) {
            return new ResponseEntity<>(beat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Beat> createBeat(@RequestBody Beat beat) {
        Beat createdBeat = beatService.createBeat(beat);
        if (createdBeat != null) {
            return new ResponseEntity<>(createdBeat, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{beatId}")
    public ResponseEntity<Beat> updateBeat(@PathVariable Long beatId, @RequestBody Beat beat) {
        Beat updatedBeat = beatService.updateBeat(beatId, beat);
        if (updatedBeat != null) {
            return new ResponseEntity<>(updatedBeat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{beatId}")
    public ResponseEntity<HttpStatus> deleteBeat(@PathVariable Long beatId) {
        boolean deleted = beatService.deleteBeat(beatId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByTags")
    public ResponseEntity<List<Beat>> findByTags(@RequestParam(name = "tag") String tag) {
        List<Beat> beats = beatService.findByTagsContaining(tag);
        return new ResponseEntity<>(beats, HttpStatus.OK);
    }
}