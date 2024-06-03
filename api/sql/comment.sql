CREATE TABLE comment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  body TEXT,
  user_id BIGINT NOT NULL,
  article_id BIGINT NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (article_id) REFERENCES article(id)
);
