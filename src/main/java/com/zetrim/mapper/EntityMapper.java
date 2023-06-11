package com.zetrim.mapper;

import com.zetrim.entity.Message;
import com.zetrim.model.MessageDto;
import com.zetrim.model.NewMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    MessageDto messageToMessageDto(Message message);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "sourceCreatedAt", ignore = true)
    Message newMessageDtoToMessage(NewMessageDto newMessageDto);
}
