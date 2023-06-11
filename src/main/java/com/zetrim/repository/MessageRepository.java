package com.zetrim.repository;

import com.zetrim.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    Page<Message> findAllByToKeyOrFromKey(Pageable pageable, String toKey, String fromKey);
    Page<Message> findAllBySource(Pageable pageable, String source);
}
