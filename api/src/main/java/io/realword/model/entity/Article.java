package io.realword.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
  private List<Tag> tags = new ArrayList<>();

  @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
  private List<io.realword.model.entity.Comment> comments = new ArrayList<>();

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
}
