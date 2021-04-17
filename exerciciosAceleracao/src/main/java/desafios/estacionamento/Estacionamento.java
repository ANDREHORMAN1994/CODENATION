package desafios.estacionamento;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    public List<Carro> listaCarros = new ArrayList<>();
    String message;

    public void estacionar(Carro carro) {
        if (semMotorista(carro)) throw new EstacionamentoException(message);
        if (validarIdade(carro)) throw new EstacionamentoException(message);
        if (validarPontos(carro)) throw new EstacionamentoException(message);
        if (semHabilitacao(carro)) throw new EstacionamentoException(message);
        if (semNome(carro)) throw new EstacionamentoException(message);
        atualizaListaCarros(carro);
    }

    public int carrosEstacionados() {
        return listaCarros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return listaCarros.contains(carro);
    }

    public Boolean limiteVagas() {
        message = "Limite de vagas é 10";
        int VAGAS = 10;
        return carrosEstacionados() >= VAGAS;
    }

    public Boolean semHabilitacao(Carro carro) {
        message = "Motorista sem Habilitação";
        return carro.getMotorista().getHabilitacao().isEmpty();
    }

    public Boolean semNome(Carro carro) {
        message = "Motorista sem Nome";
        return carro.getMotorista().getNome().isEmpty();
    }

    public Boolean validarIdade(Carro carro) {
        message = "Motorista idade Negativa ou Menor de 18 anos";
        int MAIOR_IDADE = 18;
        return carro.getMotorista().getIdade() < MAIOR_IDADE;
    }

    public Boolean validarPontos(Carro carro) {
        message = "Motorista pontuação superior a vinte pontos";
        int LIMITE_PONTOS = 20;
        return carro.getMotorista().getPontos() > LIMITE_PONTOS;
    }

    public Boolean semMotorista(Carro carro) {
        message = "Carro sem motorista";
        return carro.getMotorista() == null;
    }

    public void chegouCarro(Carro carro) {
        int SENIOR = 55;
        for (Carro c : listaCarros) {
            if (c.getMotorista().getIdade() < SENIOR) {
                listaCarros.remove(c);
                listaCarros.add(carro);
                return;
            }
        }
        throw new EstacionamentoException("Não há vagas Disponíveis");
    }

    public void atualizaListaCarros(Carro carroNovo) {
        if (limiteVagas()) {
            chegouCarro(carroNovo);
        } else {
            listaCarros.add(carroNovo);
        }
    }

    public static void main(String[] args) {

        Motorista motorista = Motorista.builder().withNome("Ada").withIdade(20)
                .withPontos(3)
                .withHabilitacao("1231")
                .build();
        Carro carro = Carro.builder().withCor(Cor.BRANCO).
                withPlaca("123")
                .withMotorista(motorista)
                .build();

        Estacionamento estacionamento = new Estacionamento();
        estacionamento.estacionar(carro);

        System.out.println(estacionamento.carroEstacionado(carro));
        System.out.println(estacionamento.carrosEstacionados());
    }
}
