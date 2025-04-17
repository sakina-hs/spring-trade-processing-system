package com.goldman.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.goldman.auth.entity.TradeUser;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<TradeUser, Long> {

    Optional<TradeUser> findByEmail(String email);

    Optional<TradeUser> findByUsername(String username);
}