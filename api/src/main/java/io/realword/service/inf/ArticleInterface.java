package io.realword.service.inf;

import io.realword.controller.dto.res.ResArticle;

import java.util.List;

public interface ArticleInterface {
  List<ResArticle> getAllArticle();
  List<ResArticle> getArticleByUser();
  List<ResArticle> getArticleByFavorite();
  List<ResArticle> getArticleByTag();
}
