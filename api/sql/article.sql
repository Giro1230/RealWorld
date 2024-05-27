CREATE TABLE Article (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  body TEXT,
  user_id BIGINT NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME,
  FOREIGN KEY (user_id) REFERENCES user(id)
);
