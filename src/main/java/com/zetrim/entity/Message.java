package com.zetrim.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "from_key")
    private String fromKey;

    @Column(name = "to_key")
    private String toKey;

    private String message;

    private String metadata;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String source;

    @Column(name = "source_created_at")
    private LocalDateTime sourceCreatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
