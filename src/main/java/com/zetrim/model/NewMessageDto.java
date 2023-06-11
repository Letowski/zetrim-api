package com.zetrim.model;

import lombok.Data;

@Data
public class NewMessageDto {
    private String fromKey;
    private String toKey;
    private String message;
    private String metadata;
}
