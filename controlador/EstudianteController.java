package controlador;

import java.util.Random;
import modelo.Estudiante;

public class EstudianteController extends PiezaController {

    private final Estudiante estudiante;
    private final Random random = new Random();
    private int ultimoDado;

    public EstudianteController(Estudiante estudiante) {
        super(estudiante);
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public int tirarDado() {
        ultimoDado = random.nextInt(6) + 1;
        return ultimoDado;
    }

    public int getUltimoDado() {
        return ultimoDado;
    }

    @Override
    public int[] ejecutarTurno(int[] coordenada) {
        int pasos = tirarDado();
        return calcularCoordenada(coordenada, pasos);
    }

    public int[] ejecutarTurno() {
        return ejecutarTurno(estudiante.getCoordenada());
    }

    public void moverA(int[] nuevaCoordenada) {
        int antes = coordenadaAIndice(estudiante.getCoordenada());
        int despues = coordenadaAIndice(nuevaCoordenada);
        int estres = estudiante.getPuntoEstres() - (despues - antes);

        if (despues == META) {
            estres = 0;
        }
        if (estres < 0) {
            estres = 0;
        }
        if (estres > 100) {
            estres = 100;
        }

        estudiante.setCoordenada(nuevaCoordenada);
        estudiante.setPuntoEstres(estres);
        estudiante.setEstabilidad(100 - estres);
    }

    public boolean haLlegadoAMeta() {
        return coordenadaAIndice(estudiante.getCoordenada()) == META;
    }

    @Override
    public void marcarEn(int[][] matriz) {
    }
}
