package com.bdv.demo.beats;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Beat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long beatId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer duration; // in secs

    @Column(nullable = false)
    private String bpm;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private Double price;

    @ElementCollection
    @CollectionTable(name = "beat_tags", joinColumns = @JoinColumn(name = "beat_id"))
    @Column(name = "tag")
    private List<String> tags;


    @Column(nullable = false)
    private String youTubeLink;

    @Column(nullable = false)
    private String s3ObjectLink;

    public Beat() {
    }
    //getters and setters

    public Long getBeatId() {
        return beatId;
    }

    public void setBeatId(Long beatId) {
        this.beatId = beatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getS3ObjectLink() {
        return s3ObjectLink;
    }

    public void setS3ObjectLink(String s3ObjectLink) {
        this.s3ObjectLink = s3ObjectLink;
    }

}
