package io.realword.repository;

import io.realword.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

  @Query("SELECT a FROM Article a WHERE a.user.username = :name")
  List<Article> findArticlesByUserName(@Param("name") String name);

  @Query("SELECT a FROM Article a JOIN a.tags t WHERE t.tagName = :tag")
  List<Article> findArticlesByTag(@Param("tag") String tag);
}
