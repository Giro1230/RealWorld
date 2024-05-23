package io.realword.model.dto;

import io.realword.model.entity.Article;
import io.realword.model.entity.Tag;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO implements DTOFunc{
  private Long id;
  private String tagName;
  private Article article;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Override
  public Tag InsertDataConverter(){
    if (this.id == null) {
      this.id = 0L;
    }

    return new Tag(this.id, this.tagName, this.article, this.createdAt, null);
  }

  @Override
  public Tag UpdateDataConverter(){
    return new Tag(this.id, this.tagName, this.article, this.createdAt, this.updatedAt);
  }

  @Override
  public void InsertTime() {

  }
}
