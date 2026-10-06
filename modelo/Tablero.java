package modelo;

public class Tablero {

    private int[][] tablero;
    private boolean victoria;

    public Tablero(int[][] tablero, boolean victoria) {
        this.tablero = tablero;
        this.victoria = victoria;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public boolean isVictoria() {
        return victoria;
    }

    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }

    @Override
    public String toString() {
        return "Tablero de " + tablero.length + " por " + tablero[0].length + ", victoria " + victoria;
    }
}
