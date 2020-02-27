package com.onthepitch.frontend.gwt.startpage.client.post;

import com.onthepitch.shared.model.PostResult;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

public interface PostRest extends RestService {
    @POST
    @Path("/post/list")
    void showPosts(MethodCallback<PostResult> callback);
}