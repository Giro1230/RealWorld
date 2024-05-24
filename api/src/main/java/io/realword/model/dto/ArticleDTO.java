package io.realword.model.dto;

import io.realword.model.entity.Article;
import io.realword.model.entity.Tag;
import io.realword.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO implements DTOFunc{
  private Long id;
  private String title;
  private String body;
  private User user;
  private List<Tag> tag = new ArrayList<>();
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Override
  public Article insertDataConverter () {
    if (this.id == null) {
      this.id = 0L;
    }
    insertTime();
    return new Article (this.id, this.title, this.body, this.user, this.tag, this.createdAt, null);
  }

  @Override
  public Article updateDataConverter () {
    insertTime();
    return new Article(this.id, this.title, this.body, this.user, this.tag, this.createdAt, this.updatedAt);
  }

  @Override
  public void insertTime() {
    if(this.createdAt == null) {
      this.createdAt = LocalDateTime.now();
    } else if(this.updatedAt == null) {
      this.updatedAt = LocalDateTime.now();
    }
  }
}
