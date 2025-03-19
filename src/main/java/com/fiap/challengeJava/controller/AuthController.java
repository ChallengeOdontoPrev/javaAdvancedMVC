package com.fiap.challengeJava.controller;

import com.fiap.challengeJava.dto.UserDTO;
import com.fiap.challengeJava.dto.auth.LoginRequestDTO;
import com.fiap.challengeJava.dto.auth.LoginResponseDTO;
import com.fiap.challengeJava.dto.auth.RegisterRequestDTO;
import com.fiap.challengeJava.dto.auth.RegisterResponseDTO;
import com.fiap.challengeJava.enums.UserRole;
import com.fiap.challengeJava.service.AuthService;
import com.fiap.challengeJava.service.models.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute LoginRequestDTO data, Model model) {
        this.authService.login(data);
        model.addAttribute("message", "Login realizado com sucesso.");
        return new ModelAndView("servicos");
    }

    @PostMapping("/signup")
    public ModelAndView register(@Valid @ModelAttribute RegisterRequestDTO data, Model model) {
        RegisterResponseDTO account = this.authService.signup(data);
        model.addAttribute("message", data.getName() + ", sua conta foi cadastrada com sucesso.");
        return new ModelAndView("success");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findByRole(@RequestParam UserRole role) {
        List<UserDTO> users = userService.findByRole(role).stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    // Endpoint para solicitar a redefinição de senha
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        authService.forgotPassword(email);
        return ResponseEntity.ok("E-mail de redefinição de senha enviado.");
    }

    // Endpoint para redefinir a senha
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token,
                                                @RequestParam("newPassword") String newPassword,
                                                @RequestParam("confirmNewPassword") String confirmNewPassword) {
        authService.resetPassword(token, newPassword, confirmNewPassword);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }

}
