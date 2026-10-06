package modelo;

public class Recurso extends Pieza {

    private int adelantoCasillas;
    private int usos;

    public Recurso(int id, String nombre, int[] coordenada, int estabilidad, int adelantoCasillas, int usos) {
        super(id, nombre, coordenada, estabilidad);
        this.adelantoCasillas = adelantoCasillas;
        this.usos = usos;
    }

    public int getAdelantoCasillas() {
        return adelantoCasillas;
    }

    public void setAdelantoCasillas(int adelantoCasillas) {
        this.adelantoCasillas = adelantoCasillas;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }

    @Override
    public String toString() {
        return "Recurso, " + super.toString() + ", adelanto de casillas " + adelantoCasillas
                + ", usos " + usos;
    }
}
