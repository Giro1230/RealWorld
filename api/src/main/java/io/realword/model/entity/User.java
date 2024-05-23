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
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Comment("유저 이름")
  @Column(nullable = false)
  private String username;

  @Comment("유저 이메일")
  @Column(nullable = false, unique = true)
  private String email;

  @Comment("비밀번호")
  @Column(nullable = false)
  private String password;

  @Comment("생성날짜")
  @Column
  private String bio;

  @Comment("생성날짜")
  @Column
  private String image;

  @Comment("생성날짜")
  @Column(nullable = false)
  private LocalDateTime creatAt;

  @Comment("수정날짜")
  @Column
  private LocalDateTime updateAt;
}
