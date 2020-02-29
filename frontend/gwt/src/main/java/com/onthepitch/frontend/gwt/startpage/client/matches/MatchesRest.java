package com.onthepitch.frontend.gwt.startpage.client.matches;

import com.onthepitch.shared.model.MatchesResult;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

public interface MatchesRest extends RestService {
    @POST
    @Path("/matches")
    void showPosts(MethodCallback<List<MatchesResult>> callback);
}
