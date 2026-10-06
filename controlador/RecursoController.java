package controlador;

import modelo.Recurso;

public class RecursoController extends PiezaController {

    private final Recurso recurso;

    public RecursoController(Recurso recurso) {
        super(recurso);
        this.recurso = recurso;
    }

    @Override
    public int[] ejecutarTurno(int[] coordenada) {
        if (estaAgotado()) {
            return new int[]{coordenada[0], coordenada[1]};
        }
        recurso.setUsos(recurso.getUsos() - 1);
        return calcularCoordenada(coordenada, recurso.getAdelantoCasillas());
    }

    public boolean estaAgotado() {
        return recurso.getUsos() <= 0;
    }
}
