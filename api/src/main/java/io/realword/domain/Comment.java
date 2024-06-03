package io.realword.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @org.hibernate.annotations.Comment("내용")
  private String body;

  @org.hibernate.annotations.Comment("생성날짜")
  private LocalDateTime createdAt;

  @org.hibernate.annotations.Comment("수정날짜")
  private LocalDateTime updatedAt;

  @org.hibernate.annotations.Comment("게시글")
  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @org.hibernate.annotations.Comment("글쓴이")
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
