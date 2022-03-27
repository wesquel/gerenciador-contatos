package br.com.ifpb.gerenciadorendereco.controllers;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.models.Contato;
import br.com.ifpb.gerenciadorendereco.models.Endereco;
import br.com.ifpb.gerenciadorendereco.repository.ContaRepository;
import br.com.ifpb.gerenciadorendereco.repository.ContatoRepository;
import br.com.ifpb.gerenciadorendereco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/registro")
public class registroController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    //Pagina de Visualizaçao de Contatos
    @RequestMapping(value = "/contatos", method = RequestMethod.GET)
    public ModelAndView listaContatos(){
        Conta logado = (Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Conta conta = contaRepository.findByUsername(logado.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contatos");
        Iterable<Contato> contatos = contatoRepository.findByConta(conta);
        modelAndView.addObject("dadosConta", conta);
        modelAndView.addObject("contatos", contatos);
        return modelAndView;
    }

    //Pagina de Cadastro de contatos (GET)
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView formulario(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("formContato");
        Contato contato = new Contato();
        Endereco endereco = new Endereco();
        modelAndView.addObject("dadosContato",contato);
        modelAndView.addObject("enderecoContato",endereco);
        return modelAndView;
    }
    //Pagina de Cadastro de contatos METODO (POST)
    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String salvar(@Valid Contato contato, Endereco endereco,BindingResult result){
        System.out.println(result.getErrorCount());
        if (result.hasErrors()){
            System.out.println("tem error");;
        }
        contato.adicionarEndereco(endereco);
        contato.setConta((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        endereco.setContato(contato);
        contatoRepository.save(contato);
        enderecoRepository.save(endereco);
        return "redirect:/registro/contatos";
    }

    //Pagina de deletar contato
    @RequestMapping("/contatos/deletar/{id}")
    public String deletarContato(@PathVariable("id") long id){
        Contato contato = contatoRepository.findById(id);
        contatoRepository.delete(contato);
        return "redirect:/registro/contatos";
    }

    //Pagina de Cadastro de ENDEREÇO (GET)
    @RequestMapping(value = "/cadastro/endereco/{id}", method = RequestMethod.GET)
    public ModelAndView formularioEnderecos(@PathVariable("id") long id){
        Contato contato = contatoRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("formEndereco");
        Endereco endereco = new Endereco();
        modelAndView.addObject("dadosContato",contato);
        modelAndView.addObject("enderecoContato",endereco);
        return modelAndView;
    }

    //Pagina de Cadastro de ENDEREÇO (POST)
    @RequestMapping(value = "/cadastro/endereco/{id}", method = RequestMethod.POST)
    public String formularioEnderecosPOST(Endereco endereco ,@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Contato contato = contatoRepository.findById(id);
        modelAndView.setViewName("formEndereco");
        endereco.setContato(contato);
        enderecoRepository.save(endereco);
        return "redirect:/registro/contatos/{id}";
    }

    //Pagina de deletar endereco
    @RequestMapping("/contatos/endereco/deletar/{id}")
    public String deletarEndereco(@PathVariable("id") long id){
        Endereco endereco = enderecoRepository.findById(id);
        System.out.println(endereco.getCep());
        enderecoRepository.delete(endereco);
        return "redirect:/registro/contatos/"+endereco.getContato().getId();
    }

    //Pagina de informação do contato
    @RequestMapping("/contatos/{id}")
    public ModelAndView enderecos(@PathVariable("id") long id){
        Contato contato = contatoRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("enderecos");
        modelAndView.addObject("dadosContato", contato);
        Iterable<Endereco> enderecos = enderecoRepository.findByContato(contato);
        modelAndView.addObject("enderecos", enderecos);
        return modelAndView;
    }

    //Pagina com Form de edição de contato
    @RequestMapping("/contatos/editar/{id}")
    public ModelAndView editarContato(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("formEditContato");
        Contato contato = contatoRepository.findById(id);
        modelAndView.addObject("dadosContato",contato);
        return modelAndView;
    }

    // Editar Contatos
    @RequestMapping(value = "/contatos/editar/{id}", method = RequestMethod.POST)
    public String salvarEdicaoContato(@PathVariable("id") long id, Contato contato){
        Contato atual = contatoRepository.findById(id);
        atual.setNome(contato.getNome());
        atual.setCPF(contato.getCPF());
        atual.setRG(contato.getRG());
        contatoRepository.save(atual);
        return "redirect:/registro/contatos/";
    }

    //Pagina com Form de edição de endereco
    @RequestMapping("/contatos/endereco/editar/{id}")
    public ModelAndView editarEndereco(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("formEditEndereco");
        Endereco endereco = enderecoRepository.findById(id);
        modelAndView.addObject("dadosEndereco",endereco);
        return modelAndView;
    }

    //Pagina de editar endereço
    @RequestMapping(value = "/contatos/endereco/editar/{id}", method = RequestMethod.POST)
    public String editarEndereco(@PathVariable("id") long id, Endereco endereco){
        Endereco atual = enderecoRepository.findById(id);
        atual.setRua(endereco.getRua());
        atual.setCep(endereco.getCep());
        atual.setCidade(endereco.getCidade());
        atual.setComplemento(endereco.getComplemento());
        atual.setBairro(endereco.getBairro());
        atual.setEstado(endereco.getEstado());
        atual.setNumero(endereco.getNumero());
        enderecoRepository.save(atual);
        return "redirect:/registro/contatos/"+atual.getContato().getId();
    }

}
