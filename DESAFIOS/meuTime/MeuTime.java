package meuTime;


import meuTime.desafio.exceptions.TimeNaoEncontradoException;
import meuTime.jogadores.Jogador;
import meuTime.jogadores.ListaJogadores;
import meuTime.times.ListaTimes;
import meuTime.times.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class MeuTime implements MeuTimeInterface {

    ListaTimes times = new ListaTimes();
    ListaJogadores jogadores = new ListaJogadores();

    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        times.addTime(time);
    }

    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        if (!times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        jogadores.addJogador(jogador);
    }

    public void definirCapitao(Long idJogador) {
        jogadores.escolherCapitao(idJogador);
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        if (!times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        return jogadores.retornaCapitaoTime(idTime).getId();
    }

    public String buscarNomeJogador(Long idJogador) {
        return jogadores.retornaJogador(idJogador).getNome();
    }

    public String buscarNomeTime(Long idTime) {
        if (!times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        return times.retornaTime(idTime).getNome();
    }

    public List<Long> buscarJogadoresDoTime(Long idTime) {
        if (!times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        return jogadores.retornaJogadoresTime(idTime)
                .stream().map(Jogador::getId)
                .collect(Collectors.toList());
    }

    public Long buscarMelhorJogadorDoTime(Long idTime) {
        if (!times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        return jogadores.retornaMelhorJogador(jogadores.retornaJogadoresTime(idTime));
    }

    public Long buscarJogadorMaisVelho(Long idTime) {
        if (!times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        return jogadores.retornaJogadorMaisVelho(jogadores.retornaJogadoresTime(idTime));
    }

    public List<Long> buscarTimes() {
        List<Long> vazio = new ArrayList<>();
        if (times.times.isEmpty()) return vazio;
        return times.times.stream()
                .map(Time::getId)
                .collect(Collectors.toList());
    }

    public Long buscarJogadorMaiorSalario(Long idTime) {
        if (!times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        return jogadores.retornaJogadorMaiorSalario(jogadores.retornaJogadoresTime(idTime));
    }

    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return jogadores.retornaJogador(idJogador).getSalario();
    }

    public List<Long> buscarTopJogadores(Integer top) {
        List<Long> vazio = new ArrayList<>();
        if (jogadores.jogadores.isEmpty()) return vazio;

        Comparator<Jogador> comparaPorHabilidade = Comparator
                .comparing(Jogador::getNivelHabilidade).reversed()
                .thenComparing(Jogador::getId);

        return jogadores.jogadores
                .stream().sorted(comparaPorHabilidade)
                .map(Jogador::getId)
                .limit(top)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        MeuTime metodos = new MeuTime();
        System.out.println(metodos.times.times);
        System.out.println(metodos.jogadores.jogadores);

        metodos.incluirTime(1l, "Time1", LocalDate.now(), "branco", "branco");
        metodos.incluirJogador(2l, 1l, "Jogador1", LocalDate.now().minus(25, ChronoUnit.YEARS), 1, BigDecimal.TEN);
        metodos.incluirJogador(3l, 1l, "Jogador2", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, BigDecimal.TEN);

        System.out.println(metodos.times.times.stream().map(Time::getNome).collect(Collectors.toList()));
        System.out.println(metodos.jogadores.jogadores.stream().map(Jogador::getNome).collect(Collectors.toList()));
    }
}
