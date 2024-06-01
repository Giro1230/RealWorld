package io.realword.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Comment("게시물 제목")
  private String tile;

  @Comment("부제목")
  private String description;

  @Comment("게시글")
  @Column(columnDefinition = "MEDIUMTEXT")
  private String body;

  @Comment("글쓴이")
  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  @Comment("태그")
  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
  private List<Tag> tags = new ArrayList<>();

  @Comment("댓글")
  @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
  private List<io.realword.domain.Comment> comments = new ArrayList<>();

  @Comment("생성날짜")
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Comment("수정날짜")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
