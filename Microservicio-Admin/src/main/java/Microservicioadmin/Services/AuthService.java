package Microservicioadmin.Services;

import Microservicioadmin.Security.Model.AuthResponse;
import Microservicioadmin.Security.Model.LoginRequest;

public interface AuthService {
    public AuthResponse login(LoginRequest loginRequest);
    public AuthResponse register(LoginRequest loginRequest);
}
