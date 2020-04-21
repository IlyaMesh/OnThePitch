/*
 * Copyright (c)
 */

package com.onthepitch.backend.service;

import com.onthepitch.shared.model.request.SignupRequest;
import com.onthepitch.shared.model.response.JwtResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    JwtResponse authUser(Authentication authentication);

    void regisrateUser(SignupRequest signUpRequest);
}
