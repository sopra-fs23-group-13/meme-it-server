package ch.uzh.ifi.hase.soprafs23.rest.mapper.rating;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ch.uzh.ifi.hase.soprafs23.entity.Rating;
import ch.uzh.ifi.hase.soprafs23.rest.dto.rating.RatingGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.rating.RatingPostDTO;

@Mapper()
public interface RatingMapper {
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    Rating convertRatingPostDTOtoEntity(RatingPostDTO ratingPostDTO);

    RatingGetDTO convertEntityToRatingGetDTO(Rating rating);
}
