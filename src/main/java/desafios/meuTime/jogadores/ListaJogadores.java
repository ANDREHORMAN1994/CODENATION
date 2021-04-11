package desafios.meuTime.jogadores;


import desafios.meuTime.desafio.exceptions.CapitaoNaoInformadoException;
import desafios.meuTime.desafio.exceptions.IdentificadorUtilizadoException;
import desafios.meuTime.desafio.exceptions.JogadorNaoEncontradoException;
import desafios.meuTime.desafio.exceptions.TimeNaoEncontradoException;
import desafios.meuTime.times.ListaTimes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListaJogadores {

    public List<Jogador> jogadores = new ArrayList<>();
    ListaTimes times = new ListaTimes();

    public boolean verificaJogadorExiste(Long id) {
        return jogadores.stream().anyMatch( jogador -> jogador.id.equals(id));
    }

    public void addJogador(Jogador jogador) {
        if (verificaJogadorExiste(jogador.getId())) throw new IdentificadorUtilizadoException();
        if (times.verificaTimeExiste(jogador.getIdTime())) throw new TimeNaoEncontradoException();
        jogadores.add(jogador);
    }

    public Jogador retornaJogador(Long id) {
        for ( Jogador jogador : jogadores ) {
            if (jogador.getId().equals(id)) return jogador;
        }
        throw new JogadorNaoEncontradoException();
    }

    public List<Jogador> retornaJogadoresTime(Long idTime) {
        List<Jogador> jogadoresTime = new ArrayList<>();
        for ( Jogador jogador : jogadores ) {
            if (jogador.getIdTime().equals(idTime)) jogadoresTime.add(jogador);
        }
        return jogadoresTime;
    }

    public void removerCapitao() {
        for ( Jogador jogador : jogadores ) {
            jogador.capitao = false;
        }
    }

    public void escolherCapitao(Long id) {
        removerCapitao();
        retornaJogador(id).setCapitao(true);
    }

    public Jogador retornaCapitaoTime(Long idTime) {
        if (times.verificaTimeExiste(idTime)) throw new TimeNaoEncontradoException();
        for ( Jogador jogador : jogadores ) {
            if (jogador.getIdTime().equals(idTime) && jogador.getCapitao()) return jogador;
        }
        throw new CapitaoNaoInformadoException();
    }

    public Long retornaMelhorJogador(List<Jogador> jogadores) {
        Integer primeiroValor = jogadores.get(0).getNivelHabilidade();
        Long idJogador = jogadores.get(0).getId();
        for (Jogador jogador : jogadores) {
            if (jogador.getNivelHabilidade() > primeiroValor) {
                primeiroValor = jogador.getNivelHabilidade();
                idJogador = jogador.getId();
            }
        }
        return idJogador;
    }

    public Long retornaJogadorMaisVelho(List<Jogador> jogadores) {
        LocalDate primeiroValor = jogadores.get(0).getDataNascimento();
        Long idJogador = jogadores.get(0).getId();
        for (Jogador jogador : jogadores) {
            if (jogador.getDataNascimento().isBefore(primeiroValor)) {
                primeiroValor = jogador.getDataNascimento();
                idJogador = jogador.getId();
            }
        }
        return idJogador;
    }

    public Long retornaJogadorMaiorSalario(List<Jogador> jogadores) {
        BigDecimal primeiroValor = jogadores.get(0).getSalario();
        Long idJogador = jogadores.get(0).getId();
        for (Jogador jogador : jogadores) {
            if (jogador.getSalario().compareTo(primeiroValor) > 0) {
                primeiroValor = jogador.getSalario();
                idJogador = jogador.getId();
            }
        }
        return idJogador;
    }
}
