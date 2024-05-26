package io.realword.service.inf;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CRUDInterface {
  Object save(Object data);
  Object getUserById(Long id);
  List<Object> getUserList();
  Object update(Object data);
  Boolean delete(Long id);
}
