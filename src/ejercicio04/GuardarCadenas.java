package ejercicio04;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GuardarCadenas {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String nombreArchivo = "cadenas_usuario.txt";

        System.out.println("--- Escritor de Archivos en Java ---");
        System.out.println("Introduce textos (escribe 'fin' para salir):");

        
        try (PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo))) {
            String entrada;
            boolean continuar = true;

            while (continuar) {
                System.out.print("> ");
                entrada = teclado.nextLine();

              
                if (entrada.equalsIgnoreCase("fin")) {
                    continuar = false;
                } else {
                    
                    salida.println(entrada);
                }
            }
            System.out.println("\nArchivo guardado con éxito como: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        } finally {
            teclado.close();
        }
    }
}