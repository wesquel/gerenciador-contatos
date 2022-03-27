package br.com.ifpb.gerenciadorendereco.controllers;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.repository.ContaRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.*;
import java.util.Set;

@Controller
public class cadastroController {

    @Autowired
    private ContaRepository contaRepository;

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public Object cadastro() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cadastro");
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return modelAndView;
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public Object cadastro(@Valid Conta conta, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(bindingResult.getErrorCount());
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return modelAndView;
        }
        conta.setPassword(new BCryptPasswordEncoder().encode(conta.getPassword()));
        contaRepository.save(conta);
        return modelAndView;
    }

}
