package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Core.Exceptions.BootCampException;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.LoginRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SignupRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(SignupRequest signUpRequest) throws BootCampException;
}
