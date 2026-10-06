package controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.Catedratico;
import modelo.Estudiante;
import modelo.Pieza;
import modelo.Recurso;
import modelo.Tablero;

public class TableroController {

    private final Tablero tablero;
    private final List<PiezaController> piezas = new ArrayList<>();
    private EstudianteController jugador;

    public TableroController() {
        tablero = new Tablero(new int[PiezaController.TAMANO][PiezaController.TAMANO], false);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Estudiante getEstudiante() {
        return jugador.getEstudiante();
    }

    public void cargaInicial() {
        jugador = new EstudianteController(new Estudiante(1, "Jonathan", new int[]{0, 0}, 20, 80));
        colocarPieza(jugador);

        colocarPieza(new RecursoController(new Recurso(2, "Cafetera", new int[]{0, 3}, 100, 3, 3)));
        colocarPieza(new RecursoController(new Recurso(3, "Enchufe", new int[]{1, 4}, 100, 2, 2)));
        colocarPieza(new RecursoController(new Recurso(4, "Impresora", new int[]{3, 2}, 100, 4, 2)));
        colocarPieza(new RecursoController(new Recurso(5, "Mesa de trabajo", new int[]{4, 5}, 100, 3, 3)));
        colocarPieza(new RecursoController(new Recurso(6, "Maquina de cafe", new int[]{5, 4}, 100, 2, 2)));

        colocarPieza(new CatedraticoController(
                new Catedratico(7, "Ing. Perez", new int[]{2, 2}, 100, 3, new int[0][0], 2), false));
        colocarPieza(new CatedraticoController(
                new Catedratico(8, "Lic. Gomez", new int[]{3, 4}, 100, 2, new int[0][0], 2), true));
        colocarPieza(new CatedraticoController(
                new Catedratico(9, "Dr. Lopez", new int[]{5, 1}, 100, 4, new int[0][0], 3), false));
        colocarPieza(new CatedraticoController(
                new Catedratico(10, "Dra. Morales", new int[]{5, 5}, 100, 2, new int[0][0], 1), true));
    }

    public void colocarPieza(PiezaController pc) {
        int[] c = pc.getPieza().getCoordenada();
        if (pc.getId() <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor a 0");
        }
        if (buscarController(pc.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una pieza con el id " + pc.getId());
        }
        if (!PiezaController.dentroDelTablero(c[0], c[1])) {
            throw new IllegalArgumentException("Coordenada fuera del tablero");
        }
        if (tablero.getTablero()[c[0]][c[1]] != 0) {
            throw new IllegalArgumentException("La casilla ya esta ocupada");
        }
        pc.marcarEn(tablero.getTablero());
        piezas.add(pc);
    }

    public List<Pieza> getPiezas() {
        List<Pieza> lista = new ArrayList<>();
        for (PiezaController pc : piezas) {
            lista.add(pc.getPieza());
        }
        return lista;
    }

    public Pieza buscarPieza(int id) {
        PiezaController pc = buscarController(id);
        if (pc == null) {
            return null;
        }
        return pc.getPieza();
    }

    public Pieza buscarPieza(String nombre) {
        for (PiezaController pc : piezas) {
            if (pc.getPieza().getNombre().equalsIgnoreCase(nombre.trim())) {
                return pc.getPieza();
            }
        }
        return null;
    }

    public List<Pieza> ordenarPorEstabilidad() {
        List<Pieza> lista = getPiezas();
        Collections.sort(lista);
        return lista;
    }

    private PiezaController buscarController(int id) {
        for (PiezaController pc : piezas) {
            if (pc.getId() == id) {
                return pc;
            }
        }
        return null;
    }

    public String jugarTurno() {
        if (tablero.isVictoria()) {
            return "La partida ya termino";
        }

        Estudiante e = jugador.getEstudiante();
        int[] destino = jugador.ejecutarTurno(e.getCoordenada());
        int casilla = PiezaController.coordenadaAIndice(destino);
        String evento = e.getNombre() + " saco " + jugador.getUltimoDado() + " y cayo en la casilla " + casilla + ".";

        int id = tablero.getTablero()[destino[0]][destino[1]];
        if (id != 0) {
            PiezaController efecto = buscarController(id);
            if (efecto != null) {
                destino = efecto.ejecutarTurno(destino);
                int nueva = PiezaController.coordenadaAIndice(destino);
                String nombrePieza = efecto.getPieza().getNombre();
                if (nueva > casilla) {
                    evento = evento + " " + nombrePieza + " adelanta a " + e.getNombre() + " hasta la casilla " + nueva + ".";
                } else if (nueva < casilla) {
                    evento = evento + " " + nombrePieza + " retrasa a " + e.getNombre() + " hasta la casilla " + nueva + ".";
                } else {
                    evento = evento + " " + nombrePieza + " ya no tiene usos.";
                }
            }
        }

        jugador.moverA(destino);
        evento = evento + " Estres actual " + e.getPuntoEstres() + ".";

        if (jugador.haLlegadoAMeta()) {
            tablero.setVictoria(true);
            evento = evento + " " + e.getNombre() + " llego a la meta.";
        }
        return evento;
    }

    public boolean hayVictoria() {
        return tablero.isVictoria();
    }
}
