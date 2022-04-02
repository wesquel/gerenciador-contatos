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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.*;
import java.sql.SQLIntegrityConstraintViolationException;
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
    public Object cadastro(@Valid Conta conta, BindingResult bindingResult, RedirectAttributes atributos) {
        ModelAndView modelAndView = new ModelAndView();
        if(contaRepository.findByUsername(conta.getUsername()) != null){
            bindingResult.rejectValue("username", "error.user", "Username já existe.");
        }
        else if (contaRepository.findByEmail(conta.getEmail()) != null){
            bindingResult.rejectValue("email", "error.user", "Email já existe.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return modelAndView;
        }
        conta.setPassword(new BCryptPasswordEncoder().encode(conta.getPassword()));
        contaRepository.save(conta);
        modelAndView.addObject("validMessage", "Conta cadastrada com sucesso!");
        return modelAndView;
    }

}
