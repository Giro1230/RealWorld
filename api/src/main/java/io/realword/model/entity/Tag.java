package io.realword.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String tagName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "article",nullable = false)
  private Article article;

  @Comment("생성날짜")
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Comment("수정날짜")
  @Column
  private LocalDateTime updatedAt;
}
