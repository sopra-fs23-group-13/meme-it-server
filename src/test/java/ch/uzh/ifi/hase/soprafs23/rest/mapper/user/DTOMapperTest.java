package ch.uzh.ifi.hase.soprafs23.rest.mapper.user;

import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.rest.dto.user.GetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.user.PostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.user.UserMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * D
 * Tests if the
 * representation
 * * works.
 */
public class DTOMapperTest {
    @Test
    public void testCreateUser_fromUserPostDTO_toUser_success() {
        // create UserPostDTO
        PostDTO userPostDTO = new PostDTO();
        userPostDTO.setName("name");

        // MAP -> Create user
        User user = UserMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

        // check content
        assertEquals(userPostDTO.getName(), user.getName());
    }

    @Test
    public void testGetUser_fromUser_toUserGetDTO_success() {
        // create User
        User user = new User();
        user.setName("Firstname Lastname");
        user.setToken("1");

        // MAP -> Create UserGetDTO
        GetDTO userGetDTO = UserMapper.INSTANCE.convertEntityToUserGetDTO(user);

        // check content
        assertEquals(user.getName(), userGetDTO.getName());
        assertEquals(user.getToken(), userGetDTO.getToken());
    }
}
