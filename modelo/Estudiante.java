package modelo;

public class Estudiante extends Pieza {

    private int puntoEstres;

    public Estudiante(int id, String nombre, int[] coordenada, int estabilidad, int puntoEstres) {
        super(id, nombre, coordenada, estabilidad);
        this.puntoEstres = puntoEstres;
    }

    public int getPuntoEstres() {
        return puntoEstres;
    }

    public void setPuntoEstres(int puntoEstres) {
        this.puntoEstres = puntoEstres;
    }

    @Override
    public String toString() {
        return "Estudiante, " + super.toString() + ", puntos de estres " + puntoEstres;
    }
}
