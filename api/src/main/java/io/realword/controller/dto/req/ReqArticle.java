package io.realword.controller.dto.req;

import io.realword.domain.Tag;
import io.realword.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReqArticle {
  private Long id;
  private String tile;
  private String description;
  private String body;
  private User user;
  private List<Tag> tags = new ArrayList<>();
  private List<io.realword.domain.Comment> comments = new ArrayList<>();
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
