package br.com.ifpb.gerenciadorendereco.controllers;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class cadastroController {

    @Autowired
    private ContaRepository contaRepository;

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(){
        return "cadastro";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastro(Conta conta){
        conta.setPassword(new BCryptPasswordEncoder().encode(conta.getPassword()));
        contaRepository.save(conta);
        return "/cadastroConcluido";
    }

}
