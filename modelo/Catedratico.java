package modelo;

public class Catedratico extends Pieza {

    private int retrasoCasillas;
    private int[][] casillasAfectadas;
    private int casillas;

    public Catedratico(int id, String nombre, int[] coordenada, int estabilidad, int retrasoCasillas, int[][] casillasAfectadas, int casillas) {
        super(id, nombre, coordenada, estabilidad);
        this.retrasoCasillas = retrasoCasillas;
        this.casillasAfectadas = casillasAfectadas;
        this.casillas = casillas;
    }

    public int getRetrasoCasillas() {
        return retrasoCasillas;
    }

    public void setRetrasoCasillas(int retrasoCasillas) {
        this.retrasoCasillas = retrasoCasillas;
    }

    public int[][] getCasillasAfectadas() {
        return casillasAfectadas;
    }

    public void setCasillasAfectadas(int[][] casillasAfectadas) {
        this.casillasAfectadas = casillasAfectadas;
    }

    public int getCasillas() {
        return casillas;
    }

    public void setCasillas(int casillas) {
        this.casillas = casillas;
    }

    @Override
    public String toString() {
        String afectadas = "";
        for (int i = 0; i < casillasAfectadas.length; i++) {
            afectadas = afectadas + " fila " + casillasAfectadas[i][0] + " columna " + casillasAfectadas[i][1] + ";";
        }
        if (afectadas.equals("")) {
            afectadas = " ninguna";
        }
        return "Catedratico, " + super.toString() + ", retraso de casillas " + retrasoCasillas
                + ", casillas afectadas:" + afectadas;
    }
}
