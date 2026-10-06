package controlador;

import modelo.Pieza;

public abstract class PiezaController {

    public static final int TAMANO = 7;
    public static final int META = 48;

    private final Pieza pieza;

    protected PiezaController(Pieza pieza) {
        this.pieza = pieza;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public int getId() {
        return pieza.getId();
    }

    public abstract int[] ejecutarTurno(int[] coordenada);

    public void marcarEn(int[][] matriz) {
        int[] c = pieza.getCoordenada();
        matriz[c[0]][c[1]] = pieza.getId();
    }

    public int[] calcularCoordenada(int[] coordenada, int pasos) {
        int fila = coordenada[0];
        int col = coordenada[1];

        if (pasos > 0) {
            for (int i = 0; i < pasos; i++) {
                if (fila % 2 == 0) {
                    if (col < TAMANO - 1) {
                        col++;
                    } else if (fila < TAMANO - 1) {
                        fila++;
                    }
                } else {
                    if (col > 0) {
                        col--;
                    } else {
                        fila++;
                    }
                }
            }
        } else {
            for (int i = 0; i < -pasos; i++) {
                if (fila % 2 == 0) {
                    if (col > 0) {
                        col--;
                    } else if (fila > 0) {
                        fila--;
                    }
                } else {
                    if (col < TAMANO - 1) {
                        col++;
                    } else {
                        fila--;
                    }
                }
            }
        }
        return new int[]{fila, col};
    }

    public int[] calcularCoordenada(int indice) {
        int[] inicio = {0, 0};
        return calcularCoordenada(inicio, indice);
    }

    public static int coordenadaAIndice(int[] coordenada) {
        int fila = coordenada[0];
        int col = coordenada[1];
        if (fila % 2 == 0) {
            return fila * TAMANO + col;
        }
        return fila * TAMANO + (TAMANO - 1 - col);
    }

    public static boolean dentroDelTablero(int fila, int col) {
        return fila >= 0 && fila < TAMANO && col >= 0 && col < TAMANO;
    }
}
