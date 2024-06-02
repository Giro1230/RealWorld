CREATE TABLE follow(
  follower_id INT NOT NULL,
  followee_id INT NOT NULL,
  created_at  DATETIME NOT NULL,
  PRIMARY KEY (follower_id, followee_id),
  FOREIGN KEY (follower_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (followee_id) REFERENCES users (id) ON DELETE CASCADE
);
