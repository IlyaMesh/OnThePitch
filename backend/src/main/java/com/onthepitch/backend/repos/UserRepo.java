package com.onthepitch.backend.repos;

import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.query.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select u from User u where u.active = true ")
    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query(QueryUtils.UPDATE_USER)
    void updatePhoto(@Param("username") String username, @Param("link") String link);
}
