package br.com.ifpb.gerenciadorendereco.controllers;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class loginController {

    @Autowired
    ContaRepository contaRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSucces(Conta conta){
        Conta account = this.contaRepository.Verify(conta.getUsername(), conta.getPassword());
        System.out.println("sdasdasd");
        if (account != null){
            System.out.println("é nulo");
            return "/";
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "login";
    }

}
