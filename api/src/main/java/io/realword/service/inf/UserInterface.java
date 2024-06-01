package io.realword.service.inf;

import io.realword.controller.dto.req.user.CurrentUserReq;
import io.realword.controller.dto.req.user.LoginUserReq;
import io.realword.controller.dto.req.user.RegisterUserReq;
import io.realword.controller.dto.req.user.UpdateUserReq;
import io.realword.controller.dto.res.user.CurrentUserRes;
import io.realword.controller.dto.res.user.LoginUserRes;
import io.realword.controller.dto.res.user.RegisterUserRes;
import io.realword.controller.dto.res.user.UpdatedUserRes;

import java.util.List;

public interface UserInterface {
  RegisterUserRes register(RegisterUserReq data);
  LoginUserRes login (LoginUserReq data);
  CurrentUserRes getCurrentUser(CurrentUserReq data);
  UpdatedUserRes update(String email, UpdateUserReq data);
}
