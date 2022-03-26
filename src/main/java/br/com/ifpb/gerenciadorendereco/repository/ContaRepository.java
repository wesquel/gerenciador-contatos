package br.com.ifpb.gerenciadorendereco.repository;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ContaRepository extends CrudRepository<Conta, String> {
    @Query(value="select * from conta where username =: username and password =: password", nativeQuery = true)
    public Conta Verify(String username, String password);
    Conta findByUsername(String username);
}
