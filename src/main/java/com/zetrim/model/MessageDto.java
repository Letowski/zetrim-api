package com.zetrim.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageDto {
    private UUID id;
    private String fromKey;
    private String toKey;
    private String message;
    private String metadata;
    private LocalDateTime createdAt;
    private String source;
    private LocalDateTime sourceCreatedAt;
}
