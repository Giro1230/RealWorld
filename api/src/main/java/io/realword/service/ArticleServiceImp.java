package io.realword.service;

import io.realword.controller.dto.req.article.CreatedArticleReq;
import io.realword.controller.dto.res.article.*;
import io.realword.domain.Article;
import io.realword.domain.Favorite;
import io.realword.domain.Tag;
import io.realword.domain.User;
import io.realword.repository.ArticleRepository;
import io.realword.repository.FavoriteRepository;
import io.realword.repository.UserRepository;
import io.realword.service.inf.ArticleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImp implements ArticleInterface {
  private final Logger logger;
  private final ArticleRepository articleRepository;
  private final FavoriteRepository favoriteRepository;
  private final UserRepository userRepository;

  @Autowired
  public ArticleServiceImp(
    ArticleRepository articleRepository,
    FavoriteRepository favoriteRepository,
    UserRepository userRepository
  )
  {
    this.logger = LoggerFactory.getLogger(ArticleServiceImp.class);
    this.articleRepository = articleRepository;
    this.favoriteRepository = favoriteRepository;
    this.userRepository = userRepository;
  }

  // Slug 만드는 함수
  private String converterSlug(String name){
    return name.toLowerCase(Locale.ROOT).replaceAll("\\s+", "-");
  }

  @Override
  public List<AllArticlesRes> getAllArticle() {
    try {
      List<Article> articles = articleRepository.findAll();

      return articles.stream()
        .map(article -> AllArticlesRes.builder()
          .title(article.getTile())
          .slug(article.getTile().toLowerCase(Locale.ROOT).replaceAll("\\s+", "-"))
          .description(article.getDescription())
          .body(article.getBody())
          .author(article.getUser().getUsername())
          .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
          .favorited(false)
          .favoritesCount(article.getFavorites().size())
          .createdAt(article.getCreatedAt())
          .updatedAt(article.getUpdatedAt())
          .build())
        .collect(Collectors.toList());

    } catch (Exception e) {
      logger.error("Failed to get article list", e);
      throw new RuntimeException("Failed to get article list", e);
    }
  }

  @Override
  public List<AllArticlesByAuthorRes> getArticlesByAuthor(String name) {
    try {
      return articleRepository.findArticlesByUserName(name)
        .stream()
        .map(article -> AllArticlesByAuthorRes.builder()
          .title(article.getTile())
          .slug(article.getTile().toLowerCase(Locale.ROOT).replaceAll("\\s+", "-"))
          .description(article.getDescription())
          .body(article.getBody())
          .author(article.getUser().getUsername())
          .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
          .favorited(false)
          .favoritesCount(article.getFavorites().size())
          .createdAt(article.getCreatedAt())
          .updatedAt(article.getUpdatedAt())
          .build())
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list by user name", e);
      throw new RuntimeException("Failed to get article list by user name", e);
    }
  }

  @Override
  public List<AllArticlesByFavoritedByUsernameRes> getArticlesByFavorited(String name) {
    try {
      List<Favorite> favorites = favoriteRepository.findByUserUsername(name);

      return articleRepository
        .findAllById(favorites.stream().map(favorite -> favorite.getUser().getId()).collect(Collectors.toList()))
        .stream()
        .map(article -> AllArticlesByFavoritedByUsernameRes.builder()
          .title(article.getTile())
          .slug(article.getTile().toLowerCase(Locale.ROOT).replaceAll("\\s+", "-"))
          .description(article.getDescription())
          .body(article.getBody())
          .author(article.getUser().getUsername())
          .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
          .favorited(true)
          .favoritesCount(article.getFavorites().size())
          .createdAt(article.getCreatedAt())
          .updatedAt(article.getUpdatedAt())
          .build())
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list by favorite user name", e);
      throw new RuntimeException("Failed to get article list by favorite user name", e);
    }
  }

  @Override
  public List<AllArticlesByAuthorByTagRes> getArticlesByTag(String tag) {
    try {
      return articleRepository.findArticlesByTag(tag)
        .stream()
        .map(article -> AllArticlesByAuthorByTagRes.builder()
          .title(article.getTile())
          .slug(converterSlug(article.getTile()))
          .description(article.getDescription())
          .body(article.getBody())
          .author(article.getUser().getUsername())
          .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
          .favorited(false)
          .favoritesCount(article.getFavorites().size())
          .createdAt(article.getCreatedAt())
          .updatedAt(article.getUpdatedAt())
          .build())
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list by tag name", e);
      throw new RuntimeException("Failed to get article list by tag name", e);
    }
  }

  @Override
  public CreatedArticleRes createdArticle(CreatedArticleReq data, String email) {
    try {
      List<Tag> tags = new ArrayList<>();
      for (String tagName : data.getTagList()) {

        Tag tag = Tag.builder()
          .tagName(tagName)
          .build();

        tags.add(tag);
      }

      Article article = Article.builder()
        .tile(data.getTitle())
        .slug(converterSlug(data.getTitle()))
        .description(data.getDescription())
        .body(data.getBody())
        .tags(tags)
        .build();

      article = articleRepository.save(article);

      return CreatedArticleRes.builder()
        .title(article.getTile())
        .slug(article.getSlug())
        .description(article.getDescription())
        .author(article.getUser().getUsername())
        .body(article.getBody())
        .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
        .favorited(false)
        .favoritesCount(article.getFavorites().size())
        .createdAt(article.getCreatedAt())
        .updatedAt(article.getUpdatedAt())
        .build();
    } catch (Exception e) {
      logger.error("Failed to create article", e);
      throw new RuntimeException("Failed to create article", e);
    }
  }

  @Override
  public List<FeedArticleRes> getFeed(String email) {
    try {
      User user = userRepository.findByEmail(email);

      return articleRepository.findArticlesByUserName(user.getUsername())
        .stream()
        .map(article -> FeedArticleRes.builder()
          .title(article.getTile())
          .slug(converterSlug(article.getTile()))
          .description(article.getDescription())
          .body(article.getBody())
          .author(article.getUser().getUsername())
          .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
          .favorited(false)
          .favoritesCount(article.getFavorites().size())
          .createdAt(article.getCreatedAt())
          .updatedAt(article.getUpdatedAt())
          .build())
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list by feed", e);
      throw new RuntimeException("Failed to get article list by feed", e);
    }
  }
}
