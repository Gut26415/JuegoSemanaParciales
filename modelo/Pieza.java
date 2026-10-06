package modelo;

public class Pieza implements Comparable<Pieza> {

    private int id;
    private String nombre;
    private int[] coordenada;
    private int estabilidad;

    public Pieza(int id, String nombre, int[] coordenada, int estabilidad) {
        this.id = id;
        this.nombre = nombre;
        this.coordenada = new int[]{coordenada[0], coordenada[1]};
        this.estabilidad = estabilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[] getCoordenada() {
        return new int[]{coordenada[0], coordenada[1]};
    }

    public void setCoordenada(int[] coordenada) {
        this.coordenada = new int[]{coordenada[0], coordenada[1]};
    }

    public int getEstabilidad() {
        return estabilidad;
    }

    public void setEstabilidad(int estabilidad) {
        this.estabilidad = estabilidad;
    }

    @Override
    public int compareTo(Pieza otra) {
        if (estabilidad < otra.estabilidad) {
            return -1;
        }
        if (estabilidad > otra.estabilidad) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pieza) {
            Pieza otra = (Pieza) o;
            return id == otra.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ID " + id + ", nombre " + nombre
                + ", coordenada fila " + coordenada[0] + " columna " + coordenada[1]
                + ", estabilidad " + estabilidad;
    }
}
