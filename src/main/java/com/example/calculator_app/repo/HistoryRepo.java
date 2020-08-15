package com.example.calculator_app.repo;

import com.example.calculator_app.entity.History;
import com.example.calculator_app.entity.XUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepo extends JpaRepository<History,Long> {

//    @Transactional
//    @Transactional
//    @Query(value = "UPDATE URL SET is_active = 0 WHERE expiration_date <= NOW() AND is_active = 1", nativeQuery = true)
//
//    @Query("SELECT u FROM Url u WHERE CONCAT(u.shortUrl, u.fullUrl) LIKE %:keyword% AND u.user.id = :id")
//    List<Url> search(@Param("keyword") String keyword, @Param("id") Long id);


    @Query(value = "SELECT * FROM history WHERE user_id = ?1", nativeQuery = true)
    Optional<List<History>> findAllUserId(@Param("user_id")Long user_id);
}
