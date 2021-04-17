package estudos.modelagemPostgreSQL.categoria.model;

import com.sun.istack.NotNull;
import estudos.modelagemPostgreSQL.livro.model.Livro;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String nomeCategoria;

    @ManyToMany
    private List<Livro> livrosRelacionados;

}
