# Juego Semana de Parciales

Juego en Java que simula la supervivencia de un estudiante durante la semana de parciales. Se juega en un tablero y el objetivo es llegar a la meta.

## ¿Cómo funciona?

En el tablero hay 10 piezas que se cargan cuando se inicia el programa:

- **Estudiante:** avanza según lo que le salga en el dado y tiene puntos de estrés.
- **Recursos** te adelantan casillas, pero tienen usos limitados.
- **Catedráticos:** tienen un área de efecto y te retrasan casillas si caes en ella.

Todas las piezas tienen ID, nombre, posición y puntos de estabilidad. Lo que cambia es lo que hace cada una en su turno (`ejecutarTurno()`).

## Menú

```
1. Listar todas las piezas
2. Buscar pieza por ID
3. Buscar pieza por nombre
4. Ordenar piezas por estabilidad
5. Ver tablero
6. Jugar un turno
7. Jugar hasta llegar a la meta
0. Salir
```

Las opciones del 1 a 4 son las que pide el ejercicio. Las otras opciones del 5 a 7 son extras para jugar.

## Cómo está organizado (MVC)

| Carpeta | Qué hay | Para qué sirve |
|---|---|---|
| modelo | Pieza, Estudiante, Recurso, Catedratico, Tablero | Guardar los datos |
| vista | Vista | Mostrar el menú y leer lo que escribe el usuario |
| controlador | PiezaController y sus hijos, TableroController | Cargar, buscar, ordenar y jugar turnos |

Main.java es el programa principal: lee la opción, se la pide al controlador y muestra el resultado con la vista.

## Lo que se practica

- **Herencia:** Estudiante, Recurso y Catedratico heredan de Pieza, la clase padre. Los controladores heredan de PiezaController.
- **Polimorfismo:** una sola lista guarda piezas de distintos tipos y cada una responde según le corresponda.
- **Sobrecarga:** buscarPieza(int id) y buscarPieza(String nombre).
