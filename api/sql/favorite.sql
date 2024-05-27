CREATE TABLE Favorite (
  user_id BIGINT,
  article_id BIGINT,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (user_id, article_id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (article_id) REFERENCES article(id)
);
