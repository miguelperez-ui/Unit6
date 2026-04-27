package ejercicio03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Locale;

public class GestionAlumnos {

    public static void main(String[] args) {
    	String rutaArchivo = "src/ejercicio03/Alumnos.txt";
        File fichero = new File(rutaArchivo);
        int sumaEdades = 0;
        double sumaEstaturas = 0;
        int totalAlumnos = 0;

        System.out.println("--- Listado de Alumnos ---");

        try (Scanner lector = new Scanner(fichero)) {
      
            lector.useLocale(Locale.US);

            while (lector.hasNext()) {
                
                String nombre = lector.next();
                String edadString = lector.next();
                String estaturaString = lector.next();

                
                int edad = Integer.parseInt(edadString);
                double estatura = Double.parseDouble(estaturaString);

              
                System.out.println("- " + nombre);
                sumaEdades += edad;
                sumaEstaturas += estatura;
                totalAlumnos++;
            }

           
            if (totalAlumnos > 0) {
                double mediaEdad = (double) sumaEdades / totalAlumnos;
                double mediaEstatura = sumaEstaturas / totalAlumnos;

                System.out.println("--- Estadísticas Finales ---");
                System.out.printf("Media de edad: %.2f años%n", mediaEdad);
                System.out.printf("Media de estatura: %.2f metros%n", mediaEstatura);
            } else {
                System.out.println("No hay datos suficientes para calcular medias.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encuentra el archivo Alumnos.txt");
        } catch (NumberFormatException e) {
            System.err.println("Error: El formato numérico en el archivo es incorrecto.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}