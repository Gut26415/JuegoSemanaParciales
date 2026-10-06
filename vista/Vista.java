package vista;

import controlador.PiezaController;
import java.util.List;
import java.util.Scanner;
import modelo.Estudiante;
import modelo.Pieza;
import modelo.Recurso;

public class Vista {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println();
        System.out.println("SEMANA DE PARCIALES");
        System.out.println("1. Listar todas las piezas");
        System.out.println("2. Buscar pieza por ID");
        System.out.println("3. Buscar pieza por nombre");
        System.out.println("4. Ordenar piezas por estabilidad");
        System.out.println("5. Ver tablero");
        System.out.println("6. Jugar un turno");
        System.out.println("7. Jugar hasta llegar a la meta");
        System.out.println("0. Salir");
    }

    public int leerNumero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarPiezas(List<Pieza> piezas) {
        System.out.println("Piezas en el campus: " + piezas.size());
        for (Pieza p : piezas) {
            System.out.println(p);
        }
    }

    public void mostrarPieza(Pieza pieza) {
        if (pieza == null) {
            System.out.println("No se encontro la pieza");
        } else {
            System.out.println(pieza);
        }
    }

    public void mostrarTablero(int[][] matriz, List<Pieza> piezas, Estudiante estudiante) {
        System.out.println("Tablero. La casilla 0 esta abajo a la izquierda y la meta 48 arriba a la derecha");
        for (int fila = PiezaController.TAMANO - 1; fila >= 0; fila--) {
            String linea = "";
            for (int col = 0; col < PiezaController.TAMANO; col++) {
                int indice = PiezaController.coordenadaAIndice(new int[]{fila, col});
                String celda = "" + indice;

                int id = matriz[fila][col];
                if (id != 0) {
                    celda = celda + marca(id, piezas);
                }

                int[] c = estudiante.getCoordenada();
                if (c[0] == fila && c[1] == col) {
                    celda = celda + "E";
                }

                while (celda.length() < 6) {
                    celda = celda + " ";
                }
                linea = linea + celda;
            }
            System.out.println(linea);
        }
        System.out.println("E estudiante, R recurso, C catedratico o su area de efecto");
        System.out.println(estudiante.getNombre() + " esta en la casilla "
                + PiezaController.coordenadaAIndice(estudiante.getCoordenada())
                + " con estres " + estudiante.getPuntoEstres());
    }

    private String marca(int id, List<Pieza> piezas) {
        for (Pieza p : piezas) {
            if (p.getId() == id) {
                if (p instanceof Recurso) {
                    return "R";
                }
                return "C";
            }
        }
        return "";
    }
}
