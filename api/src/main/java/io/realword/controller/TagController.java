package io.realword.controller;

import io.realword.controller.dto.res.ResTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {

  private Logger logger;

  public TagController() {
    this.logger = LoggerFactory.getLogger(TagController.class);
  }

  @GetMapping
  public ResponseEntity<List<ResTag>> getAllTag () {
    try {
      return ResponseEntity.ok(List.of());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
