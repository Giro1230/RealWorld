package io.realword.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
public class TagEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String tagName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "article",nullable = false)
  private ArticleEntity article;
}
