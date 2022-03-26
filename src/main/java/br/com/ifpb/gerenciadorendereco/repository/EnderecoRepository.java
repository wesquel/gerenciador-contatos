package br.com.ifpb.gerenciadorendereco.repository;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import br.com.ifpb.gerenciadorendereco.models.Contato;
import br.com.ifpb.gerenciadorendereco.models.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
    Endereco findById(Long id);
    Iterable<Endereco> findByContato(Contato contato);
}
