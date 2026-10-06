package controlador;

import java.util.ArrayList;
import modelo.Catedratico;

public class CatedraticoController extends PiezaController {

    private final Catedratico catedratico;
    private final boolean vertical;

    public CatedraticoController(Catedratico catedratico) {
        this(catedratico, false);
    }

    public CatedraticoController(Catedratico catedratico, boolean vertical) {
        super(catedratico);
        if (catedratico.getCasillas() < 1 || catedratico.getCasillas() > 3) {
            throw new IllegalArgumentException("Las casillas afectadas deben ser de 1 a 3");
        }
        this.catedratico = catedratico;
        this.vertical = vertical;
        inicializarArea();
    }

    public boolean isVertical() {
        return vertical;
    }

    public void inicializarArea() {
        int[][] area = calcularRangoCasillasAfectadas(catedratico.getCasillas(), catedratico.getCoordenada());
        catedratico.setCasillasAfectadas(area);
    }

    public int[][] calcularRangoCasillasAfectadas(int casillas, int[] coordenada) {
        int[] desplazamientos = {1, -1, 2};
        ArrayList<int[]> lista = new ArrayList<>();

        for (int i = 0; i < casillas; i++) {
            int fila = coordenada[0];
            int col = coordenada[1];
            if (vertical) {
                fila = fila + desplazamientos[i];
            } else {
                col = col + desplazamientos[i];
            }

            if (dentroDelTablero(fila, col)) {
                int indice = coordenadaAIndice(new int[]{fila, col});
                if (indice != 0 && indice != META) {
                    lista.add(new int[]{fila, col});
                }
            }
        }

        int[][] resultado = new int[lista.size()][2];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i);
        }
        return resultado;
    }

    @Override
    public int[] ejecutarTurno(int[] coordenada) {
        return calcularCoordenada(coordenada, -catedratico.getRetrasoCasillas());
    }

    @Override
    public void marcarEn(int[][] matriz) {
        super.marcarEn(matriz);
        int[][] area = catedratico.getCasillasAfectadas();
        for (int i = 0; i < area.length; i++) {
            if (matriz[area[i][0]][area[i][1]] == 0) {
                matriz[area[i][0]][area[i][1]] = catedratico.getId();
            }
        }
    }
}
