package Microservicioadmin.Services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import Micoservicioadmin.Repositories.RepositorioUsuario;
import Microservicioadmin.Entities.Role;
import Microservicioadmin.Entities.Usuario;
import Microservicioadmin.Jwt.AuthResponse;
import Microservicioadmin.Jwt.LoginRequest;
import Microservicioadmin.Jwt.RegisterRequest;
import Microservicioadmin.Jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private RepositorioUsuario repository;
	private JwtService jwtService;

	public AuthResponse login(LoginRequest request) {
		return null;
	}
	
	public AuthResponse register(RegisterRequest request) {
		Usuario user= Usuario.builder().username(request.getUsername())
		.password(request.getPassword())
		.role(Role.USER)
		.build();
		
		repository.save(user);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}
}
