package com.fiap.challengeJava.controller;

import com.fiap.challengeJava.dto.UserDTO;
import com.fiap.challengeJava.dto.auth.LoginRequestDTO;
import com.fiap.challengeJava.dto.auth.RegisterRequestDTO;
import com.fiap.challengeJava.dto.auth.RegisterResponseDTO;
import com.fiap.challengeJava.enums.UserRole;
import com.fiap.challengeJava.service.AuthService;
import com.fiap.challengeJava.service.models.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/role")
    public ResponseEntity<List<UserDTO>> findByRole(@RequestParam UserRole role) {
        List<UserDTO> users = userService.findByRole(role).stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        // Obtém todos os usuários do serviço
        List<UserDTO> users = userService.findAll();

        // Obtém o e-mail do usuário autenticado no contexto do Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String authenticatedUserEmail = ((UserDetails) authentication.getPrincipal()).getUsername(); // E-mail do usuário logado

            // Filtra a lista de usuários para remover o usuário autenticado
            users = users.stream()
                    .filter(user -> !user.getEmail().equals(authenticatedUserEmail)) // Compara o e-mail do usuário logado com os e-mails da lista
                    .collect(Collectors.toList());
        }

        // Retorna a lista filtrada
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

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestParam String newName) {
        userService.update(id, newName);
        return ResponseEntity.ok("Usuário atualizado com sucesso.");
    }
}
