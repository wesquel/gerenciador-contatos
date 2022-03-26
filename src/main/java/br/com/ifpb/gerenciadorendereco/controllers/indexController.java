package br.com.ifpb.gerenciadorendereco.controllers;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.models.Contato;
import br.com.ifpb.gerenciadorendereco.models.Endereco;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class indexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        Conta conta = new Conta();
        conta.exibirContatos();
        conta.setEmail("Bem vindo");
        modelAndView.setViewName("index");
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()){
            conta = (Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        modelAndView.addObject("dadosConta",conta);
        return modelAndView;
    }

}
