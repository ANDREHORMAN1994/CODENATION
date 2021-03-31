package meuTime.time;

import java.time.LocalDate;

public class Time {

    public Long id;
    public String nome;
    public LocalDate dataCriacao;
    public String corUniformePrincipal;
    public String corUniformeSecundário;

    public Time(long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundário) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundário = corUniformeSecundário;
    }
}
