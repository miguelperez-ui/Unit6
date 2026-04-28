package ejercicio08;

import java.io.*;
import java.util.Scanner;

public class EstacionMeteorologica {
    private static final String ARCHIVO = "temperaturas.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        
        inicializarArchivo();

        do {
            System.out.println("--- ESTACIÓN METEOROLÓGICA ---");
            System.out.println("1. Registrar nueva temperatura");
            System.out.println("2. Mostrar historial de registros");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> registrarTemperatura(sc);
                case 2 -> mostrarHistorial();
                case 3 -> System.out.println("Cerrando aplicación...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private static void inicializarArchivo() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            try (PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO))) {
                escritor.println("Fecha,Temperatura máxima,Temperatura mínima");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo.");
            }
        }
    }

    private static void registrarTemperatura(Scanner sc) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            System.out.print("Introduce la fecha (AAAA-MM-DD): ");
            String fecha = sc.nextLine();
            System.out.print("Temperatura máxima: ");
            int max = Integer.parseInt(sc.nextLine());
            System.out.print("Temperatura mínima: ");
            int min = Integer.parseInt(sc.nextLine());

            escritor.println(fecha + "," + max + "," + min);
            System.out.println("Registro guardado con éxito.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
        }
    }

    private static void mostrarHistorial() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            System.out.println("No hay registros disponibles.");
            return;
        }

       
        int maxAbsoluta = Integer.MIN_VALUE;
        int minAbsoluta = Integer.MAX_VALUE;
        boolean hayDatos = false;

        System.out.println("--- HISTORIAL DE REGISTROS ---");
        
        try (Scanner lector = new Scanner(f)) {
            // Saltar la cabecera
            if (lector.hasNextLine()) lector.nextLine();

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(",");

                if (partes.length == 3) {
                    String fecha = partes[0];
                    int tMax = Integer.parseInt(partes[1]);
                    int tMin = Integer.parseInt(partes[2]);

                    System.out.printf("Fecha: %s | Máx: %2dºC | Mín: %2dºC%n", fecha, tMax, tMin);

                    
                    if (tMax > maxAbsoluta) maxAbsoluta = tMax;
                    if (tMin < minAbsoluta) minAbsoluta = tMin;
                    hayDatos = true;
                }
            }

            if (hayDatos) {
                System.out.println("-------------------------------------------");
                System.out.println("RÉCORD HISTÓRICO:");
                System.out.println("Temperatura Máxima Absoluta: " + maxAbsoluta + "ºC");
                System.out.println("Temperatura Mínima Absoluta: " + minAbsoluta + "ºC");
            } else {
                System.out.println("El archivo está vacío.");
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el historial: " + e.getMessage());
        }
    }
}