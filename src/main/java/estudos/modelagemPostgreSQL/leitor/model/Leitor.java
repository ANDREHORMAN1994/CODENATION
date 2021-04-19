package estudos.modelagemPostgreSQL.leitor.model;

import com.sun.istack.NotNull;
import estudos.modelagemPostgreSQL.avaliacoes.model.Avaliacao;

import javax.persistence.*;
import java.util.List;

@Entity
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String nomeLeitor;

    @OneToMany
    private List<Avaliacao> avaliacoesdoLeitor;

}
