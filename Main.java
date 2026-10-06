import controlador.TableroController;
import vista.Vista;

public class Main {

    public static void main(String[] args) {
        TableroController controlador = new TableroController();
        controlador.cargaInicial();
        Vista vista = new Vista();

        int opcion = -1;
        while (opcion != 0) {
            vista.mostrarMenu();
            opcion = vista.leerNumero("Opcion: ");

            switch (opcion) {
                case 1:
                    vista.mostrarPiezas(controlador.getPiezas());
                    break;
                case 2:
                    int id = vista.leerNumero("Escriba el ID: ");
                    vista.mostrarPieza(controlador.buscarPieza(id));
                    break;
                case 3:
                    String nombre = vista.leerTexto("Escriba el nombre: ");
                    vista.mostrarPieza(controlador.buscarPieza(nombre));
                    break;
                case 4:
                    vista.mostrarPiezas(controlador.ordenarPorEstabilidad());
                    break;
                case 5:
                    vista.mostrarTablero(controlador.getTablero().getTablero(),
                            controlador.getPiezas(), controlador.getEstudiante());
                    break;
                case 6:
                    vista.mostrarMensaje(controlador.jugarTurno());
                    break;
                case 7:
                    while (!controlador.hayVictoria()) {
                        vista.mostrarMensaje(controlador.jugarTurno());
                    }
                    break;
                case 0:
                    vista.mostrarMensaje("Hasta luego");
                    break;
                default:
                    vista.mostrarMensaje("Opcion invalida");
            }
        }
    }
}
