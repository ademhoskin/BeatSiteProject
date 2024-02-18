package com.bdv.demo.beats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface BeatRepository extends JpaRepository<Beat, Long> {
    @Query("SELECT b FROM Beat b WHERE :tag MEMBER OF b.tags")
    List<Beat> findByTagsContaining(@Param("tag") String tag);
}
