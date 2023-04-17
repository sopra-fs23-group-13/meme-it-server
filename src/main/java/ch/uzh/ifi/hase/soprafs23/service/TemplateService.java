package ch.uzh.ifi.hase.soprafs23.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.uzh.ifi.hase.soprafs23.entity.Template;

public class TemplateService {

    // TODO: implement imgflip API

    List<Template> getTemplates() {

        Template template = new Template();
        template.setImageUrl("https://i.pinimg.co");
        List<Template> templateList = new ArrayList<Template>(
                Arrays.asList(template));
        return templateList;
    }
}
