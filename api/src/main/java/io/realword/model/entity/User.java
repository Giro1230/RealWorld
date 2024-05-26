package io.realword.model.entity;

import io.realword.model.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
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

  @Column
  private String bio;

  @Column
  private String image;

  @Comment("생성날짜")
  @Column(name = "", nullable = false)
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

  public UserDTO toDTO() {
    return UserDTO.builder()
      .id(this.id)
      .username(this.username)
      .email(this.email)
      .password(this.password)
      .bio(this.bio)
      .image(this.image)
      .createdAt(this.createdAt)
      .updatedAt(this.updatedAt)
      .build();
  }

  @Override
  public String toString() {
    return "User{id=" + id +
      ", username='" + username + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", bio='" + bio + '\'' +
      ", image='" + image + '\'' +
      ", createdAt=" + createdAt +
      ", updatedAt=" + updatedAt +
      '}';
  }

}
