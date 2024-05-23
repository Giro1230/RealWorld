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
@Table(name = "article")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Comment("게시물 제목")
  @Column
  private String tile;

  @Comment("게시글")
  @Column
  private String body;

  @Comment("생성날짜")
  @Column(nullable = false)
  private LocalDateTime creatAt;

  @Comment("수정날짜")
  @Column
  private LocalDateTime updateAt;
}
