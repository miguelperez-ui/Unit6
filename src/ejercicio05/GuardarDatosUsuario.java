package ejercicio05;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GuardarDatosUsuario {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String nombreArchivo = "datos.txt";

      
        System.out.print("Introduce tu nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Introduce tu edad: ");
        String edad = teclado.nextLine();

      
        try (PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            
            salida.println("Nombre: " + nombre + " | Edad: " + edad);
            
            System.out.println("\nDatos guardados correctamente en " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            teclado.close();
        }
    }
}
