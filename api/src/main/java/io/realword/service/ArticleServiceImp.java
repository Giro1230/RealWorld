package io.realword.service;

import io.realword.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp   {
  private final Logger logger;
  private final ArticleRepository articleRepository;

  @Autowired
  public ArticleServiceImp(ArticleRepository articleRepository) {
    this.logger = LoggerFactory.getLogger(ArticleServiceImp.class);
    this.articleRepository = articleRepository;
  }


}
