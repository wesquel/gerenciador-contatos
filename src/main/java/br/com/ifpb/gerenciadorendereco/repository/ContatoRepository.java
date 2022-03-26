package br.com.ifpb.gerenciadorendereco.repository;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.models.Contato;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato, String> {
    Contato findById(long id);
    Iterable<Contato> findByConta(Conta conta);
}
