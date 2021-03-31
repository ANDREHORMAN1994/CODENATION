package meuTime;

import meuTime.desafio.exceptions.CapitaoNaoInformadoException;
import meuTime.desafio.exceptions.JogadorNaoEncontradoException;
import meuTime.desafio.exceptions.TimeNaoEncontradoException;
import meuTime.jogador.Jogador;
import meuTime.time.Time;
import meuTime.desafio.exceptions.IdentificadorUtilizadoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class MeuTime {

    public static List<Time> arrayTimes = new ArrayList<>();
    public static List<Jogador> arrayJogadores = new ArrayList<>();

    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (arrayTimes.stream().noneMatch( time -> time.id.equals(id) )) {
            Time novoTime = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
            arrayTimes.add(novoTime);
        } else {
            throw new IdentificadorUtilizadoException();
        }
    }

    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.id.equals(id))) {
            throw new IdentificadorUtilizadoException();
        } else if (arrayTimes.stream().noneMatch(time -> time.id.equals(idTime))) {
            throw new TimeNaoEncontradoException();
        } else {
            Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
            arrayJogadores.add(jogador);
        }
    }

    public void definirCapitao(Long idJogador) {
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.capitao)) {
            arrayJogadores.forEach(jogador -> jogador.capitao = false);
        }
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.id.equals(idJogador))) {
            arrayJogadores.stream().filter(jogador -> jogador.id.equals(idJogador))
                    .forEach(expectJogador -> expectJogador.capitao = true);
        } else {
            throw new JogadorNaoEncontradoException();
        }
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        long idCapitao = 0;
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.idTime.equals(idTime))) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.capitao) {
                    idCapitao = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException();
        }
        return idCapitao;
    }

    public String buscarNomeJogador(Long idJogador) {
        String nomeJogador = "";
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.id.equals(idJogador))) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.id.equals(idJogador)) nomeJogador = jogador.nome;
            }
        } else {
            throw new JogadorNaoEncontradoException();
        }
        return nomeJogador;
    }

    public String buscarNomeTime(Long idTime) {
        String nomeTime = "";
        if (arrayTimes.stream().anyMatch(time -> time.id.equals(idTime))) {
            for (Time time : arrayTimes) {
                if (time.id.equals(idTime)) nomeTime = time.nome;
            }
        } else {
            throw new TimeNaoEncontradoException();
        }
        return nomeTime;
    }

    public List<Long> buscarJogadoresDoTime(Long idTime) {
        List<Long> arrayJogadoresId = new ArrayList<>();
        if (arrayTimes.stream().anyMatch(time -> time.id.equals(idTime))) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.idTime.equals(idTime)) {
                    arrayJogadoresId.add(jogador.id);
                }
            }
        } else {
            throw new TimeNaoEncontradoException();
        }
        return arrayJogadoresId;
    }

    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Integer nivelHabilidadePrimeiroJogador = arrayJogadores.get(0).nivelHabilidade;
        Long idMelhorJogador = 0L;
        if (arrayTimes.stream().anyMatch(time -> time.id.equals(idTime))) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.idTime.equals(idTime) && jogador.nivelHabilidade >= nivelHabilidadePrimeiroJogador) {
                    nivelHabilidadePrimeiroJogador = jogador.nivelHabilidade;
                    idMelhorJogador = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException();
        }
        return idMelhorJogador;
    }

    public Long buscarJogadorMaisVelho(Long idTime) {
        LocalDate idadeJogadorInicial = arrayJogadores.get(0).dataNascimento;
        Long idJogadorMaisVelho = 0L;
        if (arrayTimes.stream().anyMatch(time -> time.id.equals(idTime))) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.idTime.equals(idTime) && jogador.dataNascimento.isBefore(idadeJogadorInicial)) {
                    idadeJogadorInicial = jogador.dataNascimento;
                    idJogadorMaisVelho = jogador.id;
                } else if (jogador.id.equals(arrayJogadores.get(0).id)) {
                    idadeJogadorInicial = jogador.dataNascimento;
                    idJogadorMaisVelho = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException();
        }
        return idJogadorMaisVelho;
    }

    public List<Long> buscarTimes() {
        List<Long> arrayTimesId = new ArrayList<>();
        if (!arrayTimes.isEmpty()) {
            for (Time time : arrayTimes) {
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
        if (arrayTimes.stream().anyMatch(time -> time.id.equals(idTime))) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.idTime.equals(idTime) && jogador.salario.compareTo(salarioJogadorInicial) > 0) {
                    salarioJogadorInicial = jogador.salario;
                    idJogadorMaiorSalario = jogador.id;
                }
            }
        } else {
            throw new TimeNaoEncontradoException();
        }
        return idJogadorMaiorSalario;
    }

    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        BigDecimal jogadorSalario = null;
        if (arrayJogadores.stream().anyMatch(jogador -> jogador.id.equals(idJogador))) {
            for (Jogador jogador : arrayJogadores) {
                if (jogador.id.equals(idJogador)) {
                    jogadorSalario = jogador.salario;
                }
            }
        } else {
            throw new JogadorNaoEncontradoException();
        }
        return jogadorSalario;
    }

    public List<Long> buscarTopJogadores(Integer top) {
        List<BigDecimal> arrayTopSalarios = new ArrayList<>();
        List<Long> arrayTopIds = new ArrayList<>();
        List<Long> result = new ArrayList<>();
        if (!arrayJogadores.isEmpty()) {
            for ( Jogador jogador : arrayJogadores ) {
                arrayTopSalarios.add(jogador.salario);
            }
            Collections.sort(arrayTopSalarios, Collections.reverseOrder());
            for ( BigDecimal bigDecimal : arrayTopSalarios ) {
                for ( Jogador jogador : arrayJogadores ) {
                    if (bigDecimal.equals(jogador.salario)) {
                        arrayTopIds.add(jogador.id);
                    }
                }
            }
        } else {
            return result;
        }
        for (int i = 0; i < top; i++) {
            result.add(arrayTopIds.get(i));
        }
        return result;
    }

    public static void main(String[] args) {
        MeuTime metodos = new MeuTime();
        metodos.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        System.out.println(metodos.buscarTimes());
        metodos.incluirJogador(2L, 1L, "ANDRE", LocalDate.now(), 10, new BigDecimal(2.0));
        metodos.incluirJogador(3L, 2L, "ANDRE", LocalDate.now(), 10, new BigDecimal(2.0));
//        System.out.println(metodos);
    }

}
