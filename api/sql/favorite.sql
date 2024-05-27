CREATE TABLE Favorite (
  user_id INT,
  article_id INT,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (userId, articleId),
  FOREIGN KEY (userId) REFERENCES User(id),
  FOREIGN KEY (articleId) REFERENCES Article(id)
);
