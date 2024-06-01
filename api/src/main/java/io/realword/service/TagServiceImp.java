package io.realword.service;

import io.realword.domain.Tag;
import io.realword.repository.TagRepository;
import io.realword.service.inf.TagInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public List<Tag> getAllTag() {
    try {
      return tagRepository.findAll();
    } catch (Exception e) {
      logger.error("Failed to get tags", e);
      throw new RuntimeException("Failed to get tags", e);
    }
  }
}
