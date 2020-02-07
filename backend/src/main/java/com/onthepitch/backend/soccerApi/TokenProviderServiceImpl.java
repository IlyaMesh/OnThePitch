package com.onthepitch.backend.soccerApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenProviderServiceImpl  implements TokenProviderService{
    private static final String TOKEN = "243513ff93844f83bebe1c682be1fb47";

    @Override
    public String getToken() {
        return TOKEN;
    }

    @Override
    public boolean hasToken() {
        String token = getToken();
        return StringUtils.isNotEmpty(token);
    }
}
