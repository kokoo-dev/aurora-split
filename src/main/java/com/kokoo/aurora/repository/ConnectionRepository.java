package com.kokoo.aurora.repository;

import com.kokoo.aurora.domain.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Split, Long> {
}
