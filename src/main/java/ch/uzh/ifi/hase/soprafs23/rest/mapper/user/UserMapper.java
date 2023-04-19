package ch.uzh.ifi.hase.soprafs23.rest.mapper.user;

import ch.uzh.ifi.hase.soprafs23.entity.User;

import ch.uzh.ifi.hase.soprafs23.rest.dto.user.UserPostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.user.UserGetDTO;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * DTOMapper.
 * This class is responsible for generating classes that will automatically
 * transform/map the internal representation
 * of an entity (e.g., the User) to the external/API representation (e.g.,
 * UserGetDTO for getting, UserPostDTO for creating)
 * and vice versa.
 * Additional mappers can be defined for new entities.
 * Always created one mapper for getting information (GET) and one mapper for
 * creating information (POST).
 */
@Mapper()
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertUserPostDTOtoEntity(UserPostDTO lobbyPostDTO);

    UserGetDTO convertEntityToUserGetDTO(User user);

}
