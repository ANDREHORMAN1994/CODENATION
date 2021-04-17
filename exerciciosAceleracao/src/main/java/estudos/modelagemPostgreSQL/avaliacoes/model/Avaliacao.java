package estudos.modelagemPostgreSQL.avaliacoes.model;

import com.sun.istack.NotNull;
import estudos.modelagemPostgreSQL.avaliacaoIdentity.AvaliacaoIdentity;

import javax.persistence.*;

@Entity
public class Avaliacao {

    @EmbeddedId
    private AvaliacaoIdentity avaliacaoIdentity;

    @NotNull
    private Byte nota;

    @NotNull
    private String avaliacao;
}
