package ejercicio07;

import java.io.*;
import java.util.*;

public class AgendaContactos {
    private static final String ARCHIVO = "agenda.txt";
    private static final int MAX_CONTACTOS = 20;
    
    private static TreeMap<String, String> agenda = new TreeMap<>();

    public static void main(String[] args) {
        cargarDatos();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- AGENDA DE CONTACTOS ---");
            System.out.println("1. Nuevo contacto");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Mostrar todos");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> añadirContacto(sc);
                case 2 -> buscarContacto(sc);
                case 3 -> mostrarTodo();
                case 4 -> guardarYSalir();
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void añadirContacto(Scanner sc) {
        if (agenda.size() >= MAX_CONTACTOS) {
            System.out.println("Error: La agenda está llena (máximo 20).");
            return;
        }

        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();

        if (agenda.containsKey(nombre)) {
            System.out.println("Error: Ese nombre ya existe en la agenda.");
        } else {
            System.out.print("Introduce teléfono: ");
            String tel = sc.nextLine();
            agenda.put(nombre, tel);
            System.out.println("Contacto añadido.");
        }
    }

    private static void buscarContacto(Scanner sc) {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine();
        if (agenda.containsKey(nombre)) {
            System.out.println("Teléfono: " + agenda.get(nombre));
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void mostrarTodo() {
        if (agenda.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            System.out.println("\nLISTADO ORDENADO:");
            
            agenda.forEach((nombre, tel) -> System.out.println("- " + nombre + ": " + tel));
        }
    }

    private static void cargarDatos() {
        File f = new File(ARCHIVO);
        if (f.exists()) {
            try (Scanner lector = new Scanner(f)) {
                while (lector.hasNextLine()) {
                    String[] partes = lector.nextLine().split(":");
                    if (partes.length == 2) {
                        agenda.put(partes[0], partes[1]);
                    }
                }
                System.out.println("Datos cargados correctamente.");
            } catch (IOException e) {
                System.out.println("No se pudo cargar la agenda.");
            }
        }
    }

    private static void guardarYSalir() {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (var entrada : agenda.entrySet()) {
                escritor.println(entrada.getKey() + ":" + entrada.getValue());
            }
            System.out.println("Agenda guardada. ¡Hasta pronto!");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos.");
        }
    }
}