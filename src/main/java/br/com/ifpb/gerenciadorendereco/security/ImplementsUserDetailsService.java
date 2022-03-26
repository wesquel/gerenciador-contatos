package br.com.ifpb.gerenciadorendereco.security;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Conta conta = contaRepository.findByUsername(username);
        if(conta == null){
            throw new UsernameNotFoundException("Conta não encontrada!");
        }
        return conta;
    }
}
