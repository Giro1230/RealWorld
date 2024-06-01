//package io.realword.service;
//
//import io.realword.domain.Article;
//import io.realword.repository.ArticleRepository;
//import io.realword.service.inf.ArticleInterface;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ArticleServiceImp implements ArticleInterface {
//  private final Logger logger;
//  private final ArticleRepository articleRepository;
//
//  @Autowired
//  public ArticleServiceImp(ArticleRepository articleRepository) {
//    this.logger = LoggerFactory.getLogger(ArticleServiceImp.class);
//    this.articleRepository = articleRepository;
//  }
//
//
//  @Override
//  public List<ResArticle> getAllArticle() {
//    try {
//      return articleRepository.findAll()
//        .stream()
//        .map(Article::toRes)
//        .collect(Collectors.toList());
//    } catch (Exception e) {
//      logger.error("Failed to get article list", e);
//      throw new RuntimeException("Failed to get article list", e);
//    }
//  }
//
//  @Override
//  public List<ResArticle> getArticlesByAuthor(String name) {
//    try {
//      return articleRepository.findArticlesByUserName(name)
//        .stream()
//        .map(Article::toRes)
//        .collect(Collectors.toList());
//    } catch (Exception e) {
//      logger.error("Failed to get article list by user name", e);
//      throw new RuntimeException("Failed to get article list by user name", e);
//    }
//  }
//
//  @Override
//  public List<ResArticle> getArticlesByFavorited(String name) {
//    return List.of();
//  }
//
//  @Override
//  public List<ResArticle> getArticlesByTag(String tag) {
//    return List.of();
//  }
//
//  @Override
//  public Article createdArticle(Article a) {
//    try {
//      return articleRepository.save(a);
//    } catch (Exception e) {
//      logger.error("Failed to create article", e);
//      throw new RuntimeException("Failed to create article", e);
//    }
//  }
//}
