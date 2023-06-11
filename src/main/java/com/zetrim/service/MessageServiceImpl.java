package com.zetrim.service;

import com.zetrim.entity.Message;
import com.zetrim.mapper.EntityMapper;
import com.zetrim.model.MessageDto;
import com.zetrim.model.NewMessageDto;
import com.zetrim.repository.MessageRepository;
import com.zetrim.utils.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Page<MessageDto> getMessages(Pageable pageable, String key) {
        var escapedKey = RepositoryUtils.escapeSpecialCharacters(key);
        return messageRepository.findAllByToKeyOrFromKey(pageable, escapedKey, escapedKey)
                .map(EntityMapper.MAPPER::messageToMessageDto);
    }

    @Override
    public Page<MessageDto> getAllMessages(Pageable pageable) {
        return messageRepository.findAllBySource(pageable, null)
                .map(EntityMapper.MAPPER::messageToMessageDto);
    }

    @Override
    public MessageDto addMessage(NewMessageDto newMessageDto) {
        if (newMessageDto == null ||
                StringUtils.isBlank(newMessageDto.getMessage()) ||
                StringUtils.isBlank(newMessageDto.getToKey())) {
            throw new IllegalArgumentException("empty message or to-key");
        }
        Message message = EntityMapper.MAPPER.newMessageDtoToMessage(newMessageDto);
        messageRepository.save(message);
        return EntityMapper.MAPPER.messageToMessageDto(message);
    }
}
