/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package videoclub;

import Client.Client;
import Pelicula.Pelicula;
import Lloguer.Lloguer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author polol
 */
public class Videoclub {
//serveix per poder utilitzar l'escaner en totes les classes sense tenir la necesitat de declarar-lo diferents vegades
public static Scanner cin = new Scanner(System.in);  
   
public static void main(String[] args) {
        
        //Aquest bloc serveix per declarar les ArrayList de cada classe i els objectes encarregats de cridar els menus de cada classe
         ArrayList<Client> ClientsArr = new ArrayList();
         Client gestorClient = new Client();
         ArrayList<Pelicula> PeliculesArr = new ArrayList();
         Pelicula gestorPelicula = new Pelicula();
         ArrayList<Lloguer> LloguersArr = new ArrayList();
         Lloguer gestorLloguer = new Lloguer();
         int opcio = 0;

    do{    
        System.out.println("               ---------- On vols accedir? ----------");
        System.out.println("      || 1. Client || 2. Pelicula  || 3. Lloguer || 4. Sortir ||");
        opcio = Videoclub.cin.nextInt();
        //Menú principal
        switch(opcio){
            
            case 1 ->{
                gestorClient.menuClient(ClientsArr);
                break;
            }
            case 2 ->{
                gestorPelicula.menuPelicula(PeliculesArr);
                break;
            }
            case 3 ->{
                gestorLloguer.menuLloguer(LloguersArr, PeliculesArr);
                break;
            }
            case 4 ->{
                System.out.println("Sortint");
                break;
            }
            default ->{
                System.out.println("Opcio seleccionada no correcta, selecciona una opcio valida");
            }
        }
    }while(opcio != 4);
}
}

