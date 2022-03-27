package br.com.ifpb.gerenciadorendereco.repository;

import br.com.ifpb.gerenciadorendereco.models.Conta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ContaRepository extends CrudRepository<Conta, String> {
    @Query(value = "select * from conta where username = :name",nativeQuery = true)
    Conta Verify(@Param("name") String username);

    Conta findByUsername(String username);
}
