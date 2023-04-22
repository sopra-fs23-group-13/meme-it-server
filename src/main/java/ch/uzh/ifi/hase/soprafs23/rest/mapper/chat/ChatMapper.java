package ch.uzh.ifi.hase.soprafs23.rest.mapper.chat;

import ch.uzh.ifi.hase.soprafs23.entity.Message;
import ch.uzh.ifi.hase.soprafs23.rest.dto.chat.ChatGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(source = "message", target = "message")
    @Mapping(source = "user.name", target = "author")
    @Mapping(source = "createdAt", target = "time")
    ChatGetDTO convertEntityToChatGetDTO(Message m);
}
