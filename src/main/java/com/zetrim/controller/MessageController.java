package com.zetrim.controller;

import com.zetrim.model.MessageDto;
import com.zetrim.model.MessageFilter;
import com.zetrim.model.NewMessageDto;
import com.zetrim.model.PageableFilter;
import com.zetrim.model.Pagination;
import com.zetrim.model.SuccessResult;
import com.zetrim.service.MessageService;
import com.zetrim.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public SuccessResult<List<MessageDto>> getMessages(MessageFilter filter) {
        Pageable pageRequest = ControllerUtils.getPageRequest(filter);
        Page<MessageDto> clients = messageService.getMessages(pageRequest, filter.getKey());
        return new SuccessResult<>(clients.getContent(), new Pagination(clients));
    }

    @GetMapping(value = "/all")
    public SuccessResult<List<MessageDto>> getAllMessages(PageableFilter filter) {
        Pageable pageRequest = ControllerUtils.getPageRequest(filter);
        Page<MessageDto> clients = messageService.getAllMessages(pageRequest);
        return new SuccessResult<>(clients.getContent(), new Pagination(clients));
    }

    @PostMapping
    public SuccessResult<MessageDto> addMessage(@RequestBody NewMessageDto newMessageDto) {
        return new SuccessResult<>(messageService.addMessage(newMessageDto));
    }
}
