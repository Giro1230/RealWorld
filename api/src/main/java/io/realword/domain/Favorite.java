package io.realword.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @org.hibernate.annotations.Comment("유저 정보")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @org.hibernate.annotations.Comment("게시물 정보")
  @ManyToOne(fetch = FetchType.LAZY)
  private Article article;

  @org.hibernate.annotations.Comment("생정 날짜")
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void onCreatedAt(){
    this.createdAt = LocalDateTime.now();
  }
}
