package ejercicio06;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OrdenarArchivo {
    public static void main(String[] args) {
    	
        String archivoEntrada = "src/ejercicio06/numeros.txt";
        String archivoSalida = "ordenados.txt";
        List<Integer> listaNumeros = new ArrayList<>();

       
        try (Scanner lector = new Scanner(new File(archivoEntrada))) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (!linea.isEmpty()) {
                    try {
                        listaNumeros.add(Integer.parseInt(linea));
                    } catch (NumberFormatException e) {
                        System.out.println("Saltando línea no numérica: " + linea);
                    }
                }
            }
            System.out.println("Lectura completada. Números cargados: " + listaNumeros.size());

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return; 
        }

        
        Collections.sort(listaNumeros);

        
        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivoSalida))) {
            for (Integer num : listaNumeros) {
                escritor.println(num);
            }
            System.out.println("Archivo '" + archivoSalida + "' generado con éxito.");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de salida: " + e.getMessage());
        }
    }
}