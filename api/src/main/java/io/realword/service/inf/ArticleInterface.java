package io.realword.service.inf;

import io.realword.controller.dto.req.article.CreatedArticleReq;
import io.realword.controller.dto.req.article.UpdatedArticleReq;
import io.realword.controller.dto.res.article.*;

import java.util.List;

public interface ArticleInterface {
  List<AllArticlesRes> getAllArticle();
  List<AllArticlesByAuthorRes> getArticlesByAuthor(String name);
  List<AllArticlesByFavoritedByUsernameRes> getArticlesByFavorited(String name);
  List<AllArticlesByAuthorByTagRes> getArticlesByTag(String tag);


  CreatedArticleRes createdArticle(CreatedArticleReq data, String email);
  List<FeedArticleRes> getFeed(String email);
  List<AllArticlesRes> getAllArticlesWithFavoriteStatus(String email);
  List<AllArticlesByAuthorRes> getAllArticlesByUser(String email);
  List<AllArticlesByAuthorRes> getArticlesByAuthorWithFavoriteStatus(String email, String username);
  ArticleBySlugRes getArticleBySlug(String email, String slug);
  List<AllArticlesByAuthorByTagRes> getArticlesByTagWithFavoriteStatus(String email, String tag);
  UpdatedArticleRes updatedArticle(String email, String slug, UpdatedArticleReq updatedArticle);
  Boolean deleteArticle(String email, String slug);

  CreatedFavoriteRes createdFavorite(String email, String slug);
  List<ArticlesFavoritedbyUsernameRes> getArticleByUserWidthFavorite (String email, String username);
  Boolean deletedFavorite(String email, String slug);
}
