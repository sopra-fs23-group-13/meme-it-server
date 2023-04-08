package ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby;

import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.GetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { DTOFactory.class, /* EntityFactory.class */ })
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertUserPostDTOtoEntity(PostDTO userPostDTO);
    //@Mapping()
    //@Mapping()
    //@Mapping()
    //@Mapping()

    GetDTO convertEntityToUserGetDTO(User user);

}
