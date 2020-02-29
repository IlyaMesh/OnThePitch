package com.onthepitch.frontend.gwt.startpage.client.login;

import com.onthepitch.shared.model.LoginRequest;
import com.onthepitch.shared.model.LoginResult;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public interface UserRest extends RestService {
@POST
@Path("/login")
void login(LoginRequest request, MethodCallback<LoginResult> methodCallback);
}
