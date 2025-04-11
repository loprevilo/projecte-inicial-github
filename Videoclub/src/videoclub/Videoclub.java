/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package videoclub;

import Cliente.Cliente;
import Pelicula.Pelicula;
import Alquiler.Alquiler;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author polol
 */
public class Videoclub {
    // Sirve para poder utilizar el escáner en todas las clases sin tener la necesidad de declararlo diferentes veces
    public static Scanner cin = new Scanner(System.in);  
   
    public static void main(String[] args) {
        
        // Este bloque sirve para declarar las ArrayList de cada clase y los objetos encargados de llamar los menús de cada clase
        ArrayList<Cliente> clientesArr = new ArrayList();
        Cliente gestorCliente = new Cliente();
        ArrayList<Pelicula> peliculasArr = new ArrayList();
        Pelicula gestorPelicula = new Pelicula();
        ArrayList<Alquiler> alquileresArr = new ArrayList();
        Alquiler gestorAlquiler = new Alquiler();
        int opcion = 0;

        do {    
            System.out.println("               ---------- ¿Dónde quieres acceder? ----------");
            System.out.println("      || 1. Cliente || 2. Película || 3. Alquiler || 4. Salir ||");
            opcion = Videoclub.cin.nextInt();
            // Menú principal
            switch(opcion) {
                
                case 1 -> {
                    gestorCliente.menuCliente(clientesArr);
                    break;
                }
                case 2 -> {
                    gestorPelicula.menuPelicula(peliculasArr);
                    break;
                }
                case 3 -> {
                    gestorAlquiler.menuAlquiler(alquileresArr, peliculasArr);
                    break;
                }
                case 4 -> {
                    System.out.println("Saliendo");
                    break;
                }
                default -> {
                    System.out.println("Opción seleccionada no correcta, selecciona una opción válida");
                }
            }
        } while(opcion != 4);
    }
}
