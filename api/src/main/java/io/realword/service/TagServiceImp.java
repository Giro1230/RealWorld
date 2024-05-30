package io.realword.service;

import io.realword.controller.dto.res.ResTag;
import io.realword.service.inf.TagInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp implements TagInterface {
  @Override
  public List<ResTag> getAllTag() {
    return List.of();
  }
}
