package ch.uzh.ifi.hase.soprafs23.controller;

import ch.uzh.ifi.hase.soprafs23.entity.Message;
import ch.uzh.ifi.hase.soprafs23.rest.dto.chat.ChatGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.chat.ChatPostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.chat.ChatMapper;
import ch.uzh.ifi.hase.soprafs23.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {
    private final ChatService chatService;


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat/{lobbyCode}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<ChatGetDTO> writeNewMessageInProximityChat(@PathVariable  String lobbyCode, @RequestBody ChatPostDTO chatPostDTO) {
        List<ChatGetDTO> dto = new ArrayList<>();
        for (Message m : chatService.writeNewProximityChatMessage(lobbyCode, chatPostDTO)) {
            dto.add(ChatMapper.INSTANCE.convertEntityToChatGetDTO(m));
        }
        return dto;
    }

    @GetMapping("/chat/{lobbyCode}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ChatGetDTO> getAllMessagesFromProximityChat(@PathVariable String lobbyCode) {
        List<ChatGetDTO> dto = new ArrayList<>();
        for (Message m : chatService.getProximityChatMessages(lobbyCode)) {
            dto.add(ChatMapper.INSTANCE.convertEntityToChatGetDTO(m));
        }
        return dto;
    }
}