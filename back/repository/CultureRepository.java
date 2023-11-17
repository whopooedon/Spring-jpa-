package com.project05.repository;

import com.project05.domain.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultureRepository extends JpaRepository<Culture, String> { // Type of primary key is String

    @Query("SELECT c FROM Culture c WHERE c.culture_address LIKE %:district%")
    List<Culture> findCultureByAddress(String district);
}
