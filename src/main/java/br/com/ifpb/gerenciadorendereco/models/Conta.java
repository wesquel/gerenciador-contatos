package br.com.ifpb.gerenciadorendereco.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NamedQuery(name = "selecionarTodas", query = "select c from Conta c")
@Entity
@Data
public class Conta implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_conta")
    private Long id;

    @NotBlank(message = "Enter a username ")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Enter a email ")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Enter a password ")
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "conta", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contato> contatos = new ArrayList<Contato>();

    public void exibirContatos(){
        for (Contato contato: contatos){
            System.out.println(contato);
        }
    }

    public Conta() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
