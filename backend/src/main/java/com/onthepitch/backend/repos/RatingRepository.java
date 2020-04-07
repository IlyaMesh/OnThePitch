package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select r from Rating r where r.note_id =:id")
    List<Rating> getAllByNote_id(@Param("id") Long id);

    @Query("select r from Rating r where r.user =:user and r.note_id=:note_id")
    Optional<Rating> findRateByUserAndNote(@Param("user") User user, @Param("note_id") Long note_id);

    List<Rating> findAllByUser(User user);

    @Query("select case when count(r)> 0 then true else false end from Rating r where r.user =:user and r.note_id =:note_id")
    Boolean existsByUserAndAndNote_id(@Param("user") User user, @Param("note_id") Long note_id);

    @Modifying
    @Query("delete from Rating r where r.user=:user and r.note_id=:note_id")
    void deleteByUserAndNote_id(@Param("user") User user, @Param("note_id") Long note_id);
}
