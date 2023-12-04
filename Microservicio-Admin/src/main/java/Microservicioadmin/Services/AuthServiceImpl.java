package Microservicioadmin.Services;

import Microservicioadmin.Entities.Role;
import Microservicioadmin.Entities.Usuario;
import Microservicioadmin.Repositories.RepositorioUsuario;
import Microservicioadmin.Security.Config.servicioJWT;
import Microservicioadmin.Security.Model.AuthResponse;
import Microservicioadmin.Security.Model.LoginRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final RepositorioUsuario repositorioUsuario;
    private final AuthenticationManager authenticationManager;
    private final servicioJWT jwtServ;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthResponse login(LoginRequest loginRequest) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        var user = repositorioUsuario.findByUsername(loginRequest.getUsername()).orElseThrow();
        var token = jwtServ.generateToken(user);

        AuthResponse aux = new AuthResponse(token);
        return aux;
    }

    @Override
    public AuthResponse register(LoginRequest loginRequest) {
        var user = Usuario.builder()
                .username(loginRequest.getUsername())
                .password(passwordEncoder.encode(loginRequest.getPassword()))
                .role(Role.USUARIO)
                .build();
        repositorioUsuario.save(user);
        var token = jwtServ.generateToken(user);
        return new AuthResponse(token);
    }
}
