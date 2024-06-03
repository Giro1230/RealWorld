package io.realword.controller;

import io.realword.controller.dto.res.tag.AllTagRes;
import io.realword.service.TagServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

  private Logger logger;
  private TagServiceImp tagService;

  @Autowired
  public TagController(TagServiceImp tagService) {
    this.logger = LoggerFactory.getLogger(TagController.class);
    this.tagService = tagService;
  }

  @GetMapping
  public ResponseEntity<List<AllTagRes>> getAllTag () {
    try {
      List<AllTagRes> returnData = tagService.getAllTag();
      logger.info("Get '/tags' Responses Data = {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Fail to find tags", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
