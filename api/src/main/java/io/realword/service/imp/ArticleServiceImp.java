package io.realword.service.imp;

import io.realword.service.inf.CRUDInterface;
import io.realword.service.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements CRUDInterface {
  private final Logger logger;
  private final ArticleRepository articleRepository;

  @Autowired
  public ArticleServiceImp(ArticleRepository articleRepository) {
    this.logger = LoggerFactory.getLogger(ArticleServiceImp.class);
    this.articleRepository = articleRepository;
  }

  @Override
  public Object save(Object data) {
    return null;
  }

  @Override
  public Object getUserById(Long id) {
    return null;
  }

  @Override
  public List<Object> getUserList() {
    return List.of();
  }

  @Override
  public Object update(Object data) {
    return null;
  }

  @Override
  public Boolean delete(Long id) {
    return null;
  }
}
