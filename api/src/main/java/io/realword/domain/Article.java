package io.realword.domain;


import io.realword.controller.dto.res.ResArticle;
import io.realword.controller.dto.res.ResComment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Comment("게시물 제목")
  @Column
  private String tile;

  @Column
  private String description;

  @Comment("게시글")
  @Column
  private String body;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
  private List<Tag> tags = new ArrayList<>();

  @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
  private List<io.realword.domain.Comment> comments = new ArrayList<>();

  @Comment("생성날짜")
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Comment("수정날짜")
  @Column
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public ResArticle toRes(){
    List<String> tags = this.tags.stream().map(Tag::getTagName).toList();
    List<ResComment> comments = this.comments.stream().map(io.realword.domain.Comment::toRes).toList();

    return ResArticle.builder()
      .id(this.id)
      .description(this.description)
      .tile(this.tile)
      .body(this.body)
      .userName(this.user.getUsername())
      .tags(tags)
      .comments(comments)
      .createdAt(this.createdAt)
      .updatedAt(this.updatedAt)
      .build();
  }
}
