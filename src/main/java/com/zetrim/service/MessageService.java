package com.zetrim.service;

import com.zetrim.model.MessageDto;
import com.zetrim.model.NewMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    Page<MessageDto> getMessages(Pageable pageable, String key);

    Page<MessageDto> getAllMessages(Pageable pageable);

    MessageDto addMessage(NewMessageDto newMessageDto);
}
