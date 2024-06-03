package io.realword.service;

import io.realword.controller.dto.res.tag.AllTagRes;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagServiceImpTest {

  private final TagServiceImp tagService;
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpTest.class);

  @Autowired
  TagServiceImpTest(TagServiceImp tagService) {
    this.tagService = tagService;
  }

  @Test
  void getAllTag() {
    // given

    // when
    List<AllTagRes> list = tagService.getAllTag();

    // then
    logger.info("getAllTag Test = {}", list);
  }
}
