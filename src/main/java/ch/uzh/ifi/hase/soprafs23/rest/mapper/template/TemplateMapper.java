package ch.uzh.ifi.hase.soprafs23.rest.mapper.template;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ch.uzh.ifi.hase.soprafs23.entity.Template;
import ch.uzh.ifi.hase.soprafs23.rest.dto.template.TemplateGetDTO;

@Mapper()
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    TemplateGetDTO convertEntityToGetDTO(Template template);

}