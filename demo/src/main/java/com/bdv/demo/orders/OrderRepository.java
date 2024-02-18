package com.bdv.demo.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<BeatOrder, Long> {
    @Query("SELECT o FROM BeatOrder o WHERE o.customerEmail = :customerEmail")
    List<BeatOrder> findByCustomerEmail(@Param("customerEmail") String customerEmail);

}