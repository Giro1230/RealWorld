package io.realword.service.inf;

import java.util.List;

public interface ArticleInterface {
  List<ArticleVO> getAllArticle();
  List<ArticleVO> getArticlesByAuthor(ArticleVO name);
  List<ArticleVO> getArticlesByFavorited(ArticleVO name);
  List<ArticleVO> getArticlesByTag(ArticleVO tag);
  ArticleVO createdArticle(ArticleVO a);
}
