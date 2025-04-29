package com.fiap.challengeJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @PostMapping("/success")
    public ModelAndView success(Model model) {
        return new ModelAndView("success");
    }

    @GetMapping("/success")
    public ModelAndView successRefresh(Model model) {
        return new ModelAndView("success");
    }

    @PostMapping("/error")
    public ModelAndView error(Model model) {
        return new ModelAndView("error");
    }

    @GetMapping("/error")
    public ModelAndView errorRefresh(Model model) {
        return new ModelAndView("error");
    }

    @PostMapping("/servicos")
    public ModelAndView servicos(Model model) {
        return new ModelAndView("servicos");
    }

    @GetMapping("/servicos")
    public ModelAndView servicosRefresh(Model model) {
        return new ModelAndView("servicos");
    }

    @PostMapping("/manageAddresses")
    public ModelAndView manageAddresses(Model model) {
        return new ModelAndView("manageAddresses");
    }

    @GetMapping("/manageAddresses")
    public ModelAndView manageAddressesRefresh(Model model) {
        return new ModelAndView("manageAddresses");
    }

    @PostMapping("/manageUser")
    public ModelAndView manageUser(Model model) {
        return new ModelAndView("manageUser");
    }

    @GetMapping("/manageUser")
    public ModelAndView manageUserRefresh(Model model) {
        return new ModelAndView("manageUser");
    }

    @PostMapping("/cadastro-endereco")
    public ModelAndView cadastroEndereco(Model model) {
        return new ModelAndView("cadastro-endereco");
    }

    @GetMapping("/cadastro-endereco")
    public ModelAndView cadastroEnderecoRefresh(Model model) {
        return new ModelAndView("cadastro-endereco");
    }

    @PostMapping("/home")
    public ModelAndView home(Model model) {
        return new ModelAndView("home");
    }

    @GetMapping("/home")
    public ModelAndView homeRefresh(Model model) {
        return new ModelAndView("home");
    }

    @GetMapping("/")
    public ModelAndView index(Model model) {
        return new ModelAndView("home");
    }

    @GetMapping("/cadastro-atendente")
    public ModelAndView cadastroAtendenteRefresh(Model model) {
        return new ModelAndView("cadastro-atendente");
    }

    @PostMapping("/cadastro-atendente")
    public ModelAndView cadastroAtendente(Model model) {
        return new ModelAndView("cadastro-atendente");
    }

    @GetMapping("/cadastro-dentista")
    public ModelAndView cadastroDentistaRefresh(Model model) {
        return new ModelAndView("cadastro-dentista");
    }

    @PostMapping("/cadastro-dentista")
    public ModelAndView cadastroDentista(Model model) {
        return new ModelAndView("cadastro-dentista");
    }

    @PostMapping("/login")
    public ModelAndView login(Model model) {
        return new ModelAndView("login");
    }

    @GetMapping("/login")
    public ModelAndView loginRefresh(Model model) {
        return new ModelAndView("login");
    }

}

