package io.realword.model.dto;

import io.realword.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements DTOFunc {
  private Long id;
  private String username;
  private String email;
  private String password;
  private String bio;
  private String image;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Override
  public User InsertDataConverter() {
    if(this.id == null) {
      this.id = 0L;
    }
    return new User(this.id, this.username, this.email, this.password, this.bio, this.image, this.createdAt, null);
  }

  @Override
  public User UpdateDataConverter() {
    return new User(this.id, this.username, this.email, this.password, this.bio, this.image, this.createdAt, this.updatedAt);
  }

  @Override
  public void InsertTime() {

  }
}
