package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.user_pic = :link where u.username = :username" )
    void updatePhoto(@Param("username") String username, @Param("link") String link);
}
