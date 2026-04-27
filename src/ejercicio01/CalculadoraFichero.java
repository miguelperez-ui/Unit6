package ejercicio01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class CalculadoraFichero {

    public static void main(String[] args) {
       
        String rutaArchivo = "src/ejercicio01/NumerosReales.txt";
        File archivo = new File(rutaArchivo);

        double suma = 0;
        int contador = 0;

        try (Scanner lector = new Scanner(archivo)) {
           
            lector.useLocale(Locale.US);

            while (lector.hasNextDouble()) {
                double numero = lector.nextDouble();
                suma += numero;
                contador++;
            }

            if (contador > 0) {
                double media = suma / contador;
                System.out.println("--- Resultados ---");
                System.out.println("Suma total: " + suma);
                System.out.println("Media aritmética: " + media);
                System.out.println("Cantidad de números procesados: " + contador);
            } else {
                System.out.println("El archivo está vacío o no contiene números válidos.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo en " + archivo.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}