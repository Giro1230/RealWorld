package io.realword.service.inf;

import io.realword.controller.dto.req.ReqUser;
import io.realword.controller.dto.res.ResUser;

import java.util.List;

public interface UserInterface {
  ResUser register(ReqUser data);
  String login (ReqUser data);
  ResUser getUserById(Long id);
  List<ResUser> getUserList();
  ResUser getUserByEmail(String userEmail);
  ResUser update(ReqUser data);
  Boolean delete(Long userId);
}
