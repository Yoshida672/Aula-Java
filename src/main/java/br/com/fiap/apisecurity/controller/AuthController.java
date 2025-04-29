package br.com.fiap.apisecurity.controller;

import br.com.fiap.apisecurity.dto.AuthDTO;
import br.com.fiap.apisecurity.dto.RegisterDTO;
import br.com.fiap.apisecurity.dto.TokenResponseDTO;
import br.com.fiap.apisecurity.entity.User;
import br.com.fiap.apisecurity.repository.UserRepository;
import br.com.fiap.apisecurity.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService){
        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AuthDTO authDTO){
        //Gera um token para esse usuario e senha
        var userPwd = new UsernamePasswordAuthenticationToken(
                authDTO.login(),
                authDTO.password()
        );
        //Autentica o token
        var auth = this.authenticationManager.authenticate(userPwd);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new ResponseEntity<>(new TokenResponseDTO(token), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
    if(userRepository.findByLogin(registerDTO.login())!=null){
        return ResponseEntity.badRequest().build();
    }
    String passwordEncrypt = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(
                registerDTO.login(),
                passwordEncrypt,
                registerDTO.role()
        );
        userRepository.save(newUser);
        return ResponseEntity.ok().build();

    }

}
