package io.realword.service.inf;

import io.realword.controller.dto.req.article.CreatedArticleReq;
import io.realword.controller.dto.res.article.*;

import java.util.List;

public interface ArticleInterface {
  List<AllArticlesRes> getAllArticle();
  List<AllArticlesByAuthorRes> getArticlesByAuthor(String name);
  List<AllArticlesByFavoritedByUsernameRes> getArticlesByFavorited(String name);
  List<AllArticlesByAuthorByTagRes> getArticlesByTag(String tag);


  CreatedArticleRes createdArticle(CreatedArticleReq data, String email);
  List<FeedArticleRes> getFeed(String email);
}
