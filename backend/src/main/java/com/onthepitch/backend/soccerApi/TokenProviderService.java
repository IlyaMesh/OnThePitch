package com.onthepitch.backend.soccerApi;

public interface TokenProviderService {
    String getToken();

    boolean hasToken();
}
