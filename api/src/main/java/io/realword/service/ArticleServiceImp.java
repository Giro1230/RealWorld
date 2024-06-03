package io.realword.service;

import io.realword.controller.dto.req.article.CreatedArticleReq;
import io.realword.controller.dto.req.article.CreatedCommentReq;
import io.realword.controller.dto.req.article.UpdatedArticleReq;
import io.realword.controller.dto.res.article.*;
import io.realword.domain.*;
import io.realword.repository.ArticleRepository;
import io.realword.repository.CommentRepository;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImp implements ArticleInterface {
  private final Logger logger;
  private final ArticleRepository articleRepository;
  private final FavoriteRepository favoriteRepository;
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;

  @Autowired
  public ArticleServiceImp(
    ArticleRepository articleRepository,
    FavoriteRepository favoriteRepository,
    UserRepository userRepository,
    CommentRepository commentRepository
  )
  {
    this.logger = LoggerFactory.getLogger(ArticleServiceImp.class);
    this.articleRepository = articleRepository;
    this.favoriteRepository = favoriteRepository;
    this.userRepository = userRepository;
    this.commentRepository = commentRepository;
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

  @Override
  public List<AllArticlesRes> getAllArticlesWithFavoriteStatus(String email) {
    try {
      User user = userRepository.findByEmail(email);
      List<Long> favorites = favoriteRepository.findFavoritedArticleIdsByUser(user);
      List<Article> articles = articleRepository.findAll();

      return articles.stream()
        .map(article -> {
          boolean isFavorited = favorites.contains(article.getId());

          return AllArticlesRes.builder()
            .title(article.getTile())
            .slug(article.getSlug())
            .description(article.getDescription())
            .body(article.getBody())
            .author(article.getUser().getUsername())
            .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
            .favorited(isFavorited)
            .favoritesCount(article.getFavorites().size())
            .createdAt(article.getCreatedAt())
            .updatedAt(article.getUpdatedAt())
            .build();
        })
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list width favorite", e);
      throw new RuntimeException("Failed to get article list width favorite", e);
    }
  }

  @Override
  public List<AllArticlesByAuthorRes> getAllArticlesByUser(String email) {
    try {
      User user = userRepository.findByEmail(email);
      List<Long> favorites = favoriteRepository.findFavoritedArticleIdsByUser(user);
      List<Article> articles = articleRepository.findAll();

      return articles.stream()
        .map(article -> {
          boolean isFavorited = favorites.contains(article.getId());

          return AllArticlesByAuthorRes.builder()
            .title(article.getTile())
            .slug(article.getSlug())
            .description(article.getDescription())
            .body(article.getBody())
            .author(article.getUser().getUsername())
            .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
            .favorited(isFavorited)
            .favoritesCount(article.getFavorites().size())
            .createdAt(article.getCreatedAt())
            .updatedAt(article.getUpdatedAt())
            .build();
        })
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list by user", e);
      throw new RuntimeException("Failed to get article list by user", e);
    }
  }

  @Override
  public List<AllArticlesByAuthorRes> getArticlesByAuthorWithFavoriteStatus(String email, String username) {
    try {
      User user = userRepository.findByEmail(email);
      List<Long> favorites = favoriteRepository.findFavoritedArticleIdsByUser(user);
      List<Article> articles = articleRepository.findArticlesByUserName(username);

      return articles.stream()
        .map(article -> {
          boolean isFavorited = favorites.contains(article.getId());

          return AllArticlesByAuthorRes.builder()
            .title(article.getTile())
            .slug(article.getSlug())
            .description(article.getDescription())
            .body(article.getBody())
            .author(article.getUser().getUsername())
            .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
            .favorited(isFavorited)
            .favoritesCount(article.getFavorites().size())
            .createdAt(article.getCreatedAt())
            .updatedAt(article.getUpdatedAt())
            .build();
        })
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list by author width favorite", e);
      throw new RuntimeException("Failed to get article list by author width favorite", e);
    }
  }

  @Override
  public ArticleBySlugRes getArticleBySlug(String email, String slug) {
    try {
      User user = userRepository.findByEmail(email);
      List<Long> favorites = favoriteRepository.findFavoritedArticleIdsByUser(user);
      Article article = articleRepository.findBySlug(slug);


      boolean isFavorited = favorites.contains(article.getId());

      return ArticleBySlugRes.builder()
        .title(article.getTile())
        .slug(article.getSlug())
        .description(article.getDescription())
        .body(article.getBody())
        .author(article.getUser().getUsername())
        .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
        .favorited(isFavorited)
        .favoritesCount(article.getFavorites().size())
        .createdAt(article.getCreatedAt())
        .updatedAt(article.getUpdatedAt())
        .build();
    } catch (Exception e) {
      logger.error("Failed to get article by slug", e);
      throw new RuntimeException("Failed to get article by slug", e);
    }
  }

  @Override
  public List<AllArticlesByAuthorByTagRes> getArticlesByTagWithFavoriteStatus(String email, String tag) {
    try {
      User user = userRepository.findByEmail(email);
      List<Long> favorites = favoriteRepository.findFavoritedArticleIdsByUser(user);
      List<Article> articles = articleRepository.findArticlesByTag(tag);

      return articles.stream()
        .map(article -> {
          boolean isFavorited = favorites.contains(article.getId());

          return AllArticlesByAuthorByTagRes.builder()
            .title(article.getTile())
            .slug(article.getSlug())
            .description(article.getDescription())
            .body(article.getBody())
            .author(article.getUser().getUsername())
            .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
            .favorited(isFavorited)
            .favoritesCount(article.getFavorites().size())
            .createdAt(article.getCreatedAt())
            .updatedAt(article.getUpdatedAt())
            .build();
        })
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article list by tag width favorite", e);
      throw new RuntimeException("Failed to get article list by tag width favorite", e);
    }
  }

  @Override
  public UpdatedArticleRes updatedArticle(String email, String slug, UpdatedArticleReq updatedArticle) {
    try {
      User user = userRepository.findByEmail(email);
      Article article = articleRepository.findBySlugAndUser(slug, user);
      article.update(updatedArticle.getBody());

      List<Long> favorites = favoriteRepository.findFavoritedArticleIdsByUser(user);

      boolean isFavorited = favorites.contains(article.getId());

      return UpdatedArticleRes.builder()
        .title(article.getTile())
        .slug(article.getSlug())
        .description(article.getDescription())
        .body(article.getBody())
        .author(article.getUser().getUsername())
        .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
        .favorited(isFavorited)
        .favoritesCount(article.getFavorites().size())
        .createdAt(article.getCreatedAt())
        .updatedAt(article.getUpdatedAt())
        .build();
    } catch (Exception e) {
      logger.error("Failed to updated article", e);
      throw new RuntimeException("Failed to updated article", e);
    }
  }

  @Override
  public Boolean deleteArticle(String email, String slug){
    try {
      User user = userRepository.findByEmail(email);
      Article article = articleRepository.findBySlugAndUser(slug, user);

      articleRepository.delete(article);

      return true;
    } catch (Exception e) {
      logger.error("Failed to deleted article", e);
      return false;
    }
  }

  @Override
  public CreatedFavoriteRes createdFavorite(String email, String slug){
    try {
      User user = userRepository.findByEmail(email);
      Article article = articleRepository.findBySlugAndUser(slug, user);
      Favorite favorite = Favorite.builder()
        .user(user)
        .article(article)
        .build();

      favoriteRepository.save(favorite);

      return CreatedFavoriteRes.builder()
        .title(article.getTile())
        .slug(article.getSlug())
        .description(article.getDescription())
        .body(article.getBody())
        .author(article.getUser().getUsername())
        .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
        .favorited(true)
        .favoritesCount(article.getFavorites().size())
        .createdAt(article.getCreatedAt())
        .updatedAt(article.getUpdatedAt())
        .build();
    } catch (Exception e) {
      logger.error("Failed to created favorite", e);
      throw new RuntimeException("Failed to created favorite", e);
    }
  }

  @Override
  public List<ArticlesFavoritedbyUsernameRes> getArticleByUserWidthFavorite (String email, String username) {
    try {
      User user = userRepository.findByEmail(email);
      User author = userRepository.findByUsername(username);

      List<Article> articles = favoriteRepository.findArticlesByUser(author);
      List<Long> favorites = favoriteRepository.findFavoritedArticleIdsByUser(user);

      return articles.stream()
        .map(article -> {
          boolean isFavorited = favorites.contains(article.getId());

          return ArticlesFavoritedbyUsernameRes.builder()
            .title(article.getTile())
            .slug(article.getSlug())
            .description(article.getDescription())
            .body(article.getBody())
            .author(article.getUser().getUsername())
            .tagList(article.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()))
            .favorited(isFavorited)
            .favoritesCount(article.getFavorites().size())
            .createdAt(article.getCreatedAt())
            .updatedAt(article.getUpdatedAt())
            .build();
        })
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get article by user width favorite", e);
      throw new RuntimeException("Failed to get article by user width favorite", e);
    }
  }

  @Override
  public Boolean deletedFavorite(String email, String slug){
    try {
      User user = userRepository.findByEmail(email);
      Article article = articleRepository.findBySlug(slug);

      Favorite favorite = favoriteRepository.findFavoriteArticleIdsByUserAndArticle(user, article);

      favoriteRepository.delete(favorite);

      return true;
    } catch (Exception e) {
      logger.error("Failed to deleted favorite", e);
      return false;
    }
  }

  @Override
  public CreatedCommentRes createdComment(String email, String slug, CreatedCommentReq data){
    try {
      User user = userRepository.findByEmail(email);
      Article article = articleRepository.findBySlug(slug);

      Comment comment = commentRepository.save(Comment.builder()
                                                .user(user)
                                                .article(article)
                                                .body(data.getBody())
                                                .build());

      return CreatedCommentRes.builder()
        .id(comment.getId())
        .body(comment.getBody())
        .author(comment.getUser().getUsername())
        .createdAt(comment.getCreatedAt())
        .updatedAt(comment.getUpdatedAt())
        .build();
    } catch (Exception e) {
      logger.error("Failed to created comment", e);
      throw new RuntimeException("Failed to created comment", e);
    }
  }

  @Override
  public List<GetCommentRes> getCommentByArticle(String email, String slug){
    try {
      Article article = articleRepository.findBySlug(slug);
      User user = userRepository.findByEmail(email);

      List<Comment> comments;

      if (email == null) {
        comments = commentRepository.findByArticle(article);
      } else {
        comments = commentRepository.findByArticleAndUser(article, user);
      }

      return comments.stream()
        .map(comment -> GetCommentRes.builder()
          .id(comment.getId())
          .body(comment.getBody())
          .createdAt(comment.getCreatedAt())
          .updatedAt(comment.getUpdatedAt())
          .author(comment.getUser().getUsername())
          .build())
        .collect(Collectors.toList());
    } catch (Exception e){
      logger.error("Failed to get comments", e);
      throw new RuntimeException("Failed to get comments", e);
    }
  }

  @Override
  public Boolean deletedComment(String email, String slug, Long id) {
    try {
      User user = userRepository.findByEmail(email);
      Article article = articleRepository.findBySlug(slug);
      Comment comment = commentRepository.getReferenceById(id);

      if(Objects.equals(comment.getUser(), user) && Objects.equals(comment.getArticle(), article)) {
        commentRepository.delete(comment);
      }

      return true;
    } catch (Exception e) {
      logger.error("Failed to deleted comments", e);
      return false;
    }
  }
}
