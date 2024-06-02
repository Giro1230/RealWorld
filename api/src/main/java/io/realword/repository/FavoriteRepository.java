package io.realword.repository;

import io.realword.domain.Article;
import io.realword.domain.Favorite;
import io.realword.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
  List<Favorite> findByUserUsername(String username);

  @Query("SELECT f.article.id FROM Favorite f WHERE f.user = :user")
  List<Long> findFavoritedArticleIdsByUser(@Param("user") User user);

  List<Article> findByFavoritesContaining(User user);

  @Query("SELECT f FROM Favorite f WHERE f.user = :user AND f.article = :article")
  Favorite findFavoriteArticleIdsByUserAndArticle(@Param("user") User user, @Param("article") Article article);
}
