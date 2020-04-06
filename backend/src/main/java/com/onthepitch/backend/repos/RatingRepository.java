package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {

    @Query("select r from Rating r where r.note_id =:id")
    Rating getByNoteId(@Param("id")Long id);

    List<Rating> findAllByUser(User user);

    @Query("select case when count(r)> 0 then true else false end from Rating r where r.user =:user and r.note_id =:note_id")
    Boolean existsByUserAndAndNote_id(@Param("user") User user, @Param("note_id") Long note_id);

    @Modifying
    @Query("delete from Rating r where r.user=:user and r.note_id=:note_id")
    void deleteByUserAndNote_id(@Param("user") User user,@Param("note_id") Long note_id);
}
