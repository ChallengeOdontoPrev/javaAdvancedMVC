package com.fiap.challengeJava.service;

import com.fiap.challengeJava.domain.User;
import com.fiap.challengeJava.dto.EmailDTO;
import com.fiap.challengeJava.dto.UserDTO;
import com.fiap.challengeJava.dto.auth.LoginRequestDTO;
import com.fiap.challengeJava.dto.auth.LoginResponseDTO;
import com.fiap.challengeJava.dto.auth.RegisterRequestDTO;
import com.fiap.challengeJava.dto.auth.RegisterResponseDTO;
import com.fiap.challengeJava.infra.security.TokenService;
import com.fiap.challengeJava.service.exceptions.InvalidCredentialsException;
import com.fiap.challengeJava.service.exceptions.UserAlreadyExistsException;
import com.fiap.challengeJava.service.models.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.fiap.challengeJava.enums.UserRole.ATENDENTE;
import static com.fiap.challengeJava.enums.UserRole.DENTISTA;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MessageSenderService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userService.findByEmail(username);
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO auth) {
        User user = (User) this.loadUserByUsername(auth.getEmail());
        if (passwordEncoder.matches(auth.getPassword(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return new LoginResponseDTO(user.getEmail(), token);
        } else {
            throw new InvalidCredentialsException("Senha incorreta!!");
        }
    }

    @Transactional
    public RegisterResponseDTO signup(RegisterRequestDTO auth) {
        this.userService.loadUserByUsername(auth.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Conta já existente com este email.");
                });

        String encryptedPassword = passwordEncoder.encode(auth.getPassword());

        UserDTO user = switch (auth.getRole()) {
            case DENTISTA -> {
                if (auth.getCro() == null || auth.getCro().isBlank()) {
                    throw new InvalidCredentialsException("O CRO é obrigatório para o papel de Dentista.");
                }
                yield new UserDTO(auth.getName(), auth.getEmail(), auth.getRg(), auth.getBirthDate(), encryptedPassword, auth.getRole(), auth.getCro(), auth.getClinicId());
            }
            case ATENDENTE -> {
                if (auth.getCro() != null) {
                    throw new InvalidCredentialsException("A atendente não pode ter CRO.");
                }
                yield new UserDTO(auth.getName(), auth.getEmail(), auth.getRg(), auth.getBirthDate(), encryptedPassword, auth.getRole(), auth.getClinicId());
            }
            default -> throw new InvalidCredentialsException("Papel de usuário inválido.");
        };

        user = this.userService.insert(user);
        return new RegisterResponseDTO(user.getEmail(), user.getName());
    }

    public void publishMessageToForgotPassword(String email) {
        User user = userService.findByEmail(email);
        emailService.processAndSendMessage(new EmailDTO(user.getEmail()));
    }
}
