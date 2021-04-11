package desafios.meuTime.times;


import desafios.meuTime.desafio.exceptions.IdentificadorUtilizadoException;
import desafios.meuTime.desafio.exceptions.TimeNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class ListaTimes {

    public List<Time> times = new ArrayList<>();

    public Boolean verificaTimeExiste(Long idTime) {
        return times.stream().anyMatch( time -> time.id.equals(idTime) );
    }

    public void addTime(Time time) {
        if (verificaTimeExiste(time.getId())) throw new IdentificadorUtilizadoException();
        times.add(time);
    }

    public Time retornaTime(Long idTime) {
        for ( Time time : times ) {
            if (time.id.equals(idTime)) return time;
        }
        throw new TimeNaoEncontradoException();
    }

}
