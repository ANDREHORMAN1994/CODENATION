package meuTime;

import meuTime.desafio.exceptions.JogadorNaoEncontradoException;
import meuTime.desafio.exceptions.TimeNaoEncontradoException;
import meuTime.jogador.Jogador;
import meuTime.time.Time;
import meuTime.desafio.exceptions.IdentificadorUtilizadoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeuTime {

    public static List<Time> arrayTimes = new ArrayList<>();
    public static List<Jogador> arrayJogadores = new ArrayList<>();

    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws UnsupportedOperationException {
        if (arrayTimes.stream().allMatch(time -> time.id != id)) {
            Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
            arrayTimes.add(time);
        } else {
            throw new IdentificadorUtilizadoException("ID JÁ UTILIZADO ANTES");
        }
    }

    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) throws UnsupportedOperationException {
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.id == id)) {
            throw new IdentificadorUtilizadoException("ID JÁ UTILIZADO ANTES");
        } else if (arrayTimes.stream().allMatch(time -> time.id != idTime)) {
            throw new TimeNaoEncontradoException("TIME INFORMADO NÃO EXISTE");
        } else {
            Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
            arrayJogadores.add(jogador);
        }
    }

    public void definirCapitao(Long idJogador) {
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.capitao)) {
            arrayJogadores.forEach(jogador -> jogador.capitao = false);
        }
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.id == idJogador)) {
            arrayJogadores.stream().filter(jogador -> jogador.id == idJogador)
                    .forEach(expectJogador -> expectJogador.capitao = true);
        } else {
            throw new JogadorNaoEncontradoException("NÃO EXISTE ESSE JOGADOR");
        }
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        long idCapitao = 0;
        if (arrayJogadores.stream().anyMatch( jogador -> jogador.idTime == idTime )) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.capitao) {
                    idCapitao = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException("TIME INFORMADO NÃO EXISTE");
        }
        return idCapitao;
    }

    public String buscarNomeJogador(Long idJogador) {
        String nomeJogador = "";
        if (arrayJogadores.stream().anyMatch( jogador -> jogador.id == idJogador )) {
            for ( Jogador jogador : arrayJogadores) {
                if (jogador.id == idJogador) nomeJogador = jogador.nome;
            }
        } else {
            throw new JogadorNaoEncontradoException("NÃO EXISTE ESSE JOGADOR");
        }
        return nomeJogador;
    }

    public String buscarNomeTime(Long idTime) {
        String nomeTime = "";
        if (arrayTimes.stream().anyMatch( time -> time.id == idTime )) {
            for ( Time time : arrayTimes) {
                if (time.id == idTime) nomeTime = time.nome;
            }
        } else {
            throw new TimeNaoEncontradoException("TIME INFORMADO NÃO EXISTE");
        }
        return nomeTime;
    }

    public List<Long> buscarJogadoresDoTime(Long idTime) {
        List<Long> arrayJogadoresId = new ArrayList<>();
        if (arrayTimes.stream().anyMatch( time -> time.id == idTime )) {
            for ( Jogador jogador : arrayJogadores ) {
                if (jogador.idTime == idTime) {
                    arrayJogadoresId.add(jogador.id);
                }
            }
        } else {
            throw new TimeNaoEncontradoException("TIME INFORMADO NÃO EXISTE");
        }
        return arrayJogadoresId;
    }

    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Integer nivelHabilidadePrimeiroJogador = arrayJogadores.get(0).nivelHabilidade;
        Long idMelhorJogador = 0L;
        if (arrayTimes.stream().anyMatch( time -> time.id == idTime )) {
            for ( Jogador jogador : arrayJogadores ) {
                if (jogador.idTime == idTime && jogador.nivelHabilidade > nivelHabilidadePrimeiroJogador) {
                    nivelHabilidadePrimeiroJogador = jogador.nivelHabilidade;
                    idMelhorJogador = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException("TIME INFORMADO NÃO EXISTE");
        }
        return idMelhorJogador;
    }

    public Long buscarJogadorMaisVelho(Long idTime) {
        LocalDate idadeJogadorInicial = arrayJogadores.get(0).dataNascimento;
        Long idJogadorMaisVelho = 0L;
        if (arrayTimes.stream().anyMatch( time -> time.id == idTime )) {
            for ( Jogador jogador : arrayJogadores ) {
                if (jogador.idTime == idTime && jogador.dataNascimento.isAfter(idadeJogadorInicial)) {
                    idadeJogadorInicial = jogador.dataNascimento;
                    idJogadorMaisVelho = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException("TIME INFORMADO NÃO EXISTE");
        }
        return idJogadorMaisVelho;
    }

    public List<Long> buscarTimes() {
        List<Long> arrayTimesId = new ArrayList<>();
        if (arrayTimes.size() > 0) {
            for ( Time time : arrayTimes ) {
                arrayTimesId.add(time.id);
            }
        } else {
            return arrayTimesId;
        }
        return arrayTimesId;
    }

    public Long buscarJogadorMaiorSalario(Long idTime) {
        BigDecimal salarioJogadorInicial = arrayJogadores.get(0).salario;
        Long idJogadorMaiorSalario = 0L;
        if (arrayTimes.stream().anyMatch( time -> time.id == idTime )) {
            for ( Jogador jogador : arrayJogadores ) {
                if (jogador.idTime == idTime && jogador.salario.compareTo(salarioJogadorInicial) == 1) {
                    salarioJogadorInicial = jogador.salario;
                    idJogadorMaiorSalario = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException("TIME INFORMADO NÃO EXISTE");
        }
        return idJogadorMaiorSalario;
    }

    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        BigDecimal jogadorSalario = null;
        if (arrayJogadores.stream().anyMatch( jogador -> jogador.id == idJogador )) {
            for ( Jogador jogador : arrayJogadores ) {
                if (jogador.id == idJogador) {
                    jogadorSalario = jogador.salario;
                }
            }
        } else {
            throw new JogadorNaoEncontradoException("NÃO EXISTE ESSE JOGADOR");
        }
        return jogadorSalario;
    }

    public List<Long> buscarTopJogadores(Integer top) {
        Integer nivelHabilidadeInicial = arrayJogadores.get(0).nivelHabilidade;
        List<Long> arrayTopJogadores = new ArrayList<>();
        if (arrayJogadores.size() > 0) {
            for ( Jogador jogador : arrayJogadores ) {
                for ( Long idTopJogador : arrayTopJogadores ) {
                    if (jogador.nivelHabilidade > nivelHabilidadeInicial && jogador.id != idTopJogador) {
                        nivelHabilidadeInicial = jogador.nivelHabilidade;
                        arrayTopJogadores.add(jogador.id);
                    }
                }
            }
        } else {
            return arrayTopJogadores;
        }
        return arrayTopJogadores;
    }

}
