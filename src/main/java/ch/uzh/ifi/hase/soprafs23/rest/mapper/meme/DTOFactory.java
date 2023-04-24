package ch.uzh.ifi.hase.soprafs23.rest.mapper.meme;

import ch.uzh.ifi.hase.soprafs23.entity.Meme;
import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemeGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemePostDTO;

import org.mapstruct.ObjectFactory;

public class DTOFactory {

    @ObjectFactory
    public Meme convertMemePostDTOtoEntity(MemePostDTO memePostDTO) {

        Meme meme = new Meme();

        // *template set when meme created
        meme.setTextBoxes(memePostDTO.getTextBoxes());

        return meme;
    }

    @ObjectFactory
    public MemeGetDTO convertEntityToMemeGetDTO(Meme meme) {

        MemeGetDTO memeGetDTO = new MemeGetDTO();

        memeGetDTO.setId(meme.getId());
        memeGetDTO.setImageUrl(meme.getTemplate().getImageUrl());
        memeGetDTO.setTextBoxes(meme.getTextBoxes());

        return memeGetDTO;
    }

}