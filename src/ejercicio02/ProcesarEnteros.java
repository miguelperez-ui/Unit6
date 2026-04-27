package ejercicio02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcesarEnteros {

    public static void main(String[] args) {
    	String rutaArchivo = "src/ejercicio02/Enteros.txt";
        File fichero = new File(rutaArchivo);
        
        int suma = 0;
        int contador = 0;

      
        try (Scanner lector = new Scanner(fichero)) {
            
            
            while (lector.hasNextInt()) {
                int numero = lector.nextInt();
                suma += numero;
                contador++;
            }

           
            if (contador > 0) {
                double media = (double) suma / contador;
                
                System.out.println("--- Resumen de Datos ---");
                System.out.println("Suma total: " + suma);
                System.out.printf("Media aritmética: %.2f%n", media);
                System.out.println("Total de números leídos: " + contador);
            } else {
                System.out.println("El archivo no contenía números enteros válidos.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se pudo encontrar el archivo 'Enteros.txt'.");
            System.err.println("Asegúrate de que esté en la raíz del proyecto.");
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}