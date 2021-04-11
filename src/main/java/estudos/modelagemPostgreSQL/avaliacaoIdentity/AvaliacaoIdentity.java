package estudos.modelagemPostgreSQL.avaliacaoIdentity;

import com.sun.istack.NotNull;
import estudos.modelagemPostgreSQL.leitor.model.Leitor;
import estudos.modelagemPostgreSQL.livro.model.Livro;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AvaliacaoIdentity implements Serializable {

    @ManyToOne
    @NotNull
    private Leitor idLeitor;

    @ManyToOne
    @NotNull
    private Livro idLivro;

}
