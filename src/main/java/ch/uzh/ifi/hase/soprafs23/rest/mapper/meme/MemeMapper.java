package ch.uzh.ifi.hase.soprafs23.rest.mapper.meme;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ch.uzh.ifi.hase.soprafs23.entity.Meme;
import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemeGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemePostDTO;

@Mapper()
public interface MemeMapper {
    MemeMapper INSTANCE = Mappers.getMapper(MemeMapper.class);

    @Mapping(source = "textBoxes", target = "textBoxes")
    @Mapping(source = "fontSize", target = "fontSize")
    @Mapping(source = "color", target = "color")
    Meme convertMemePostDTOtoEntity(MemePostDTO memePostDTO);

    @Mapping(source = "textBoxes", target = "textBoxes")
    @Mapping(source = "template.imageUrl", target = "imageUrl")
    @Mapping(source = "id", target = "id")
    MemeGetDTO convertEntityToGetDTO(Meme meme);
}
