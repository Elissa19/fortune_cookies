package com.example.demo.repository;

import com.example.demo.core.model.Cookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CookieRepository extends JpaRepository<Cookie, Long> {
    @Query(value = "SELECT id, creation_date, description FROM cookie ORDER BY random() LIMIT 1",
            nativeQuery = true)
    Cookie getRandom();

    @Modifying
//    @Transactional
    @Query(value = "UPDATE cookie SET description = :description WHERE id = :id",
            nativeQuery = true)
    void update(Long id, String description);

}
