CREATE TABLE tag
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  tagName VARCHAR(13) NOT NULL,
  article_id BIGINT NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME,
  CONSTRAINT tag_fk FOREIGN KEY (article_id) REFERENCES article(id)
);
