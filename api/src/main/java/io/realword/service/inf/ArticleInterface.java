package io.realword.service.inf;

import io.realword.controller.dto.res.ResArticle;

import java.util.List;

public interface ArticleInterface {
  List<ResArticle> getAllArticle();
  List<ResArticle> getArticlesByAuthor(String name);
  List<ResArticle> getArticlesByFavorited(String name);
  List<ResArticle> getArticlesByTag(String tag);
}
