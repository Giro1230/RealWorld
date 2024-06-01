package io.realword.service;

import io.realword.controller.dto.res.tag.AllTagRes;
import io.realword.domain.Tag;
import io.realword.repository.TagRepository;
import io.realword.service.inf.TagInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImp implements TagInterface {
  private final Logger logger;
  private final TagRepository tagRepository;

  @Autowired
  public TagServiceImp(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
    this.logger = LoggerFactory.getLogger(TagServiceImp.class);
  }

  @Override
  public List<AllTagRes> getAllTag() {
    try {
      // findAllByTag
      List<Tag> tags = tagRepository.findAll();

      // return Array
      List<AllTagRes> allTagResList = new ArrayList<>();

      // Tag -> AllTagRes
      for (Tag tag : tags) {
        AllTagRes allTagRes = new AllTagRes();
        allTagRes.setTagName(tag.getTagName());

        // push allTagResList
        allTagResList.add(allTagRes);
      }

      return allTagResList;
    } catch (Exception e) {
      logger.error("Failed to get tags", e);
      throw new RuntimeException("Failed to get tags", e);
    }
  }
}
