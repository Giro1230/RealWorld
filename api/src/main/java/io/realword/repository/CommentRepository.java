package io.realword.repository;

import io.realword.domain.Article;
import io.realword.domain.Comment;
import io.realword.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByArticle(Article article);

  List<Comment> findByArticleAndUser(Article article, User user);
}
