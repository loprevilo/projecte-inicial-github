/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Alquiler;

import java.time.LocalDate;
import java.util.ArrayList;
import Pelicula.Pelicula;
import videoclub.Videoclub;
import java.time.format.DateTimeParseException;

/**
 *
 * @author polol
 */

//LES COSES QUE JA S'HAGIN EXPLICAT EN LES DUES CLASSES ANTERIORS NO ESTARAN COMENTADES EN AQUESTA
public class Alquiler {
    private LocalDate data_lloguer;
    private LocalDate data_retorn;
    private int IDclient;
    private int IDlloguer;
    private int IDPelicula;
    private static int IDLloguer_sequel = 0; 

    // Getters y Setters
    public LocalDate getData_lloguer() {
        return data_lloguer;
    }

    public void setData_lloguer(LocalDate data_lloguer) {
        this.data_lloguer = data_lloguer;
    }

    public LocalDate getData_retorn() {
        return data_retorn;
    }

    public void setData_retorn(LocalDate data_retorn) {
        this.data_retorn = data_retorn;
    }

    public int getIDclient() {
        return IDclient;
    }

    public void setIDclient(int IDclient) {
        this.IDclient = IDclient;
    }

    public int getIDlloguer() {
        return IDlloguer;
    }

    public int getIDPelicula() {
        return IDPelicula;
    }

    public void setIDPelicula(int IDPelicula) {
        this.IDPelicula = IDPelicula;
    }

    // Constructor
    public Alquiler(LocalDate data_lloguer, LocalDate data_retorn, int IDclient, int IDPelicula){
        this.data_lloguer = data_lloguer;
        this.data_retorn = data_retorn;
        this.IDclient = IDclient;
        this.IDPelicula = IDPelicula;
        this.IDlloguer = IDLloguer_sequel++;  
    }
    public Alquiler(){}

    public void registrarLloguer(ArrayList<Alquiler> lloguersArr, ArrayList<Pelicula> PeliculesArr){

        System.out.print("Introdueix l'ID del cliente: ");
        int IDclient = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer

        //Es comprova si hi han pelicules disponibles 
        boolean disponibles = false;
        
            for (Pelicula p : PeliculesArr) {
                //Es comprova si la pelicula està disponible
                if (p.getEstat()) { 
                    disponibles = true;
                break; 
                }
            }
        if (!disponibles){
            System.out.println("No hi ha pelicules disponibles per llogar en aquest moment.");
            return; 
        }
        
        //Es mostren totes les pelicules disponibles amb el seu respectiu ID
        System.out.println("Llista de pelicules disponibles:");
        
        for (Pelicula p : PeliculesArr){
           //Amb l'if controlem que només es mostrin les pel·licules disponibles.
            if (p.getEstat()){ 
                System.out.println("ID: " + p.getIDPelicula() + " || Titol: " + p.getTitol());
            }
        }
        
        System.out.print("Introduiex l'ID de la pleicula que vols llogar: ");
            
            int idPelicula = Videoclub.cin.nextInt();
            Videoclub.cin.nextLine(); //Buffer
    
        Pelicula peliculaSeleccionada = null;
        for (Pelicula p : PeliculesArr) {
            if (p.getIDPelicula() == idPelicula){
                //Comprova si la pelicula està llogada o no
                if (!p.getEstat()) { 
                    System.out.println("Error: La pelicula amb l'ID " + idPelicula + " ja esta llogada.");
                    return; 
                } else{
                    peliculaSeleccionada = p;
                    break; 
                }
            }
        }

        if (peliculaSeleccionada == null) {
            System.out.println("Error: No s'ha trobat cap pelicula amb l'ID introduit.");
            return; 
        }

        System.out.println("Data registre (YYYY-MM-DD): ");
        String fecha;           

        while (true){
        fecha = Videoclub.cin.nextLine();
            try{
                data_lloguer = LocalDate.parse(fecha);
                break;
            }catch (DateTimeParseException e){
                System.out.println("Format incorrecte. Utilitza el format (YYYY-MM-DD). Torna a intentar-ho ");
            }
    }
        System.out.println("Data retorn (YYYY-MM-DD): ");
        String fecha2;           
    
        while (true){
        fecha2 = Videoclub.cin.nextLine();
            try{
                data_retorn = LocalDate.parse(fecha2);
                break;
            }catch (DateTimeParseException e){
                System.out.println("Format incorrecte. Utilitza el format (YYYY-MM-DD). Torna a intentar-ho ");
            }
        }   
        
        Alquiler alquiler = new Alquiler(data_lloguer, data_retorn, IDclient, idPelicula);
        lloguersArr.add(alquiler);
        
            for (Pelicula p : PeliculesArr){
                if (p.getIDPelicula() == idPelicula){
                    //Es canvia l'estat de la pelicula a no disponible
                    p.setEstat(false); 
                    System.out.println("La pelicula '" + p.getTitol() + "' ha sigut llogada.");
                    
                break;
                }
            }
        System.out.println("Alquiler registrat amb exit ID assignat: " + alquiler.getIDlloguer());
    }
    
    public void eliminarLloguer(ArrayList<Alquiler> lloguersArr, ArrayList<Pelicula> PeliculesArr) {
        System.out.print("Introdueix l'ID del cliente per veure els seus alquileres: ");
        int clientId = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer
        
        boolean lloguerEliminat = false;

        System.out.println("Alquileres del cliente amb l'ID: " + clientId);
        
        for (Alquiler l : lloguersArr) {
            if (l.getIDclient() == clientId) {
                System.out.println("ID del alquiler: " + l.getIDlloguer() + " || Pelicula ID: " + l.getIDPelicula() + " || Data alquiler: " + l.getData_lloguer() + " || Data retorn: " + l.getData_retorn());
            }
        }

        System.out.print("Introdueix l'ID del alquiler que vols eliminar: ");
        int idEliminar = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer

        for (int i = 0; i < lloguersArr.size(); i++) {
            Alquiler l = lloguersArr.get(i);
            if (l.getIDlloguer() == idEliminar && l.getIDclient() == clientId) {
                
                //Després d'eliminar el alquiler es marca la pelicula com a disponible
                for (Pelicula p : PeliculesArr) {
                    if (p.getIDPelicula() == l.getIDPelicula()) {
                        p.setEstat(true); 
                        System.out.println("La pelicula '" + p.getTitol() + "' ha sigut tornada y està disponible altre cop.");
                        break;
                    }
                }
            lloguersArr.remove(i);
            lloguerEliminat = true;
            System.out.println("El alquiler amb l'ID " + idEliminar + " ha sigut eliminat");
            break;
            }
        }

        if (!lloguerEliminat) {
            System.out.println("No s'ha trobat cap alquiler amb l'ID o el cliente especificat.");
        }
    }
    
    public void tornarPelicula(ArrayList<Alquiler> lloguersArr, ArrayList<Pelicula> PeliculesArr) {
        
        System.out.print("Introdueix l'ID del cliente per veure elks seus alquileres: ");
        int clientId = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer
        
        boolean lloguerTrobat= false;

        System.out.println("Alquileres del cliente amb l'ID: " + clientId);
        for (Alquiler l : lloguersArr) {
            if (l.getIDclient() == clientId) {
                System.out.println("ID del alquiler: " + l.getIDlloguer() + " || Pelicula ID: " + l.getIDPelicula() + " || Data alquiler: " + l.getData_lloguer() + " || Data retorn: " + l.getData_retorn());
            }
        }

        System.out.print("Introdueix l'ID del alquiler que vols tornar: ");
        int idDevolver = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer

        for (Alquiler l : lloguersArr) {
            if (l.getIDlloguer() == idDevolver && l.getIDclient() == clientId) {
                //Tornar la pelicula, al tornar-la es marca com a disponible altre cop.
                for (Pelicula p : PeliculesArr) {
                    if (p.getIDPelicula() == l.getIDPelicula()) {
                        p.setEstat(true);
                        System.out.println("La pelicula '" + p.getTitol() + "' ha sigut retornada.");
                        break;
                    }
                }
                lloguersArr.remove(l);
                lloguerTrobat = true;
                break;
            }
        }

        if (!lloguerTrobat) {
            System.out.println("No s'ha trobat cap alquiler amb l'ID o el cliente especificat.");
        }
    }
    
    public void modificarLloguer(ArrayList<Alquiler> lloguersArr, ArrayList<Pelicula> PeliculesArr) {
        System.out.print("Introdueix l'ID del cliente per veure els seus alquileres: ");
        int clientId = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); 

        boolean lloguerTrobat = false;
    
        
        System.out.println("Alquileres del cliente amb ID: " + clientId);
        for (Alquiler l : lloguersArr) {
            if (l.getIDclient() == clientId) {
                // Mostrar cada alquiler
                System.out.println("ID del alquiler: " + l.getIDlloguer());
            }
        }

        System.out.print("Introdueix l'ID del alquiler a modificar: ");
        int idModificar = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer

        for (Alquiler l : lloguersArr) {
            //Es comprova que l'ID de cliente i de alquiler coincideixin amb IDs existents.
            if (l.getIDlloguer() == idModificar && l.getIDclient() == clientId) {
                lloguerTrobat = true;

                // Menú de opcions per a modificar
                int opcio;
                do {
                    System.out.println("\n                                           Escull que  vols modificar:");
                    System.out.println("          || 1. Modificar ID del cliente || 2. Modificar Data de retorn || 3. Modificar ID de la pelicula || 4. Enrrere ||");
                    opcio = Videoclub.cin.nextInt();
                    Videoclub.cin.nextLine(); //Buffer 

                    switch (opcio) {
                        case 1:
                            
                            System.out.print("Introdueix un nou ID per al cliente: ");
                            int nouIDclient = Videoclub.cin.nextInt();
                            l.setIDclient(nouIDclient);
                            System.out.println("ID del cliente modificat a " + nouIDclient);
                            break;

                        case 2:
                            
                            System.out.print("Introdueix la nova data de retorn (YYYY-MM-DD): ");
                            String fecha = Videoclub.cin.nextLine();
                            LocalDate novaFechaRetorno = LocalDate.parse(fecha);
                            l.setData_retorn(novaFechaRetorno);
                            System.out.println("Data de retorn modificada a " + novaFechaRetorno);
                            break;

                        case 3:
                           
                            System.out.println("Llista de pelicules disponibles:");
                            for (Pelicula p : PeliculesArr) {
                                //Serveix per mostrar només les pelicules disponibles
                                if (p.getEstat()){ 
                                    System.out.println("ID: " + p.getIDPelicula() + " || Títol: " + p.getTitol());
                                }
                            }

                            System.out.print("Introdueix el nou ID de la pelicula: ");
                            int nuevoIDPelicula = Videoclub.cin.nextInt();
                            Videoclub.cin.nextLine(); //Buffer

                            // Serveix per comprovar si la pelicula existeix
                            boolean peliculaValida = false;
                            for (Pelicula p : PeliculesArr) {
                                if (p.getIDPelicula() == nuevoIDPelicula && p.getEstat()) {
                                    peliculaValida = true;
                                    break;
                                }
                            }

                            if (!peliculaValida) {
                                System.out.println("La pelicula no esta disponible per al canvi.");
                            } else {
                                l.setIDPelicula(nuevoIDPelicula);
                                System.out.println("ID de pelicula modificat a " + nuevoIDPelicula);
                            }
                            break;

                        case 4:
                            
                            System.out.println("Modificacio cancelada.");
                            break;

                        default:
                            System.out.println("Opcio no valida.");
                    }

                } while (opcio != 4);
                break;
            }
        }
    }

    public void mostrarPeliculesLlogades(ArrayList<Alquiler> lloguersArr, ArrayList<Pelicula> PeliculesArr) {
        
        System.out.print("Introdueix l'ID del cliente: ");
        int IDclient = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer

        //Es comrpova si l'ID del cliente es vàlid
        boolean clientExistent = false;
        for (Alquiler alquiler : lloguersArr) {
            
            if (alquiler.getIDclient() == IDclient) {
                clientExistent = true;
                break;
            }
        }

        if (!clientExistent) {
            System.out.println("L'ID " + IDclient + " no es valid.");
            return;
        }

        //Es mostren les pelicules llogades pel cliente 
        boolean tePelicules = false;
        System.out.println("Pleicules llogades per el cliente amb l'ID " + IDclient + ":");
        
        for (Alquiler l : lloguersArr) {
            //Es busquen pelicules associades al ID cliente solicitat
            if (l.getIDclient() == IDclient) {
             
                for (Pelicula pelicula : PeliculesArr) {
                    if (pelicula.getIDPelicula() == l.getIDPelicula()) {
                        
                        System.out.println(pelicula.getTitol());
                        tePelicules = true;
                        break;
                    }
                }
            }
        }
        if (!tePelicules) {
            System.out.println("El cliente amb l'ID " + IDclient + " no te pelicules llogades.");
        }
    }

    public void visualitzarPeliculesDisponibles(ArrayList<Pelicula> PeliculesArr) {
        System.out.println("-------- Peliculas disponibles  --------");

        //Serveix per comprovar si hi han pelicules disponibles
        boolean Disponibles = false;

        for (Pelicula p : PeliculesArr) {
            //Es comprova si la pelicula esà disponible
            if (p.getEstat()){ 
                System.out.println("ID: " + p.getIDPelicula() + " || Titol: " + p.getTitol());
                Disponibles = true;
            }
        }

        if (!Disponibles) {
            System.out.println("No hi ha pelicules disponibles en aquest moment.");
        }
    }
    public void visualitzarPeliculesNoRetornades(ArrayList<Alquiler> lloguersArr, ArrayList<Pelicula> PeliculesArr) {
        System.out.println("-------- Pelicules no retornades --------");

        boolean noRetornades = false;
        
        for (Alquiler l : lloguersArr) {
            for (Pelicula p : PeliculesArr) {
                //Serveix per saber si la pelicula solicitada està llogada (no retornada)
                if (p.getIDPelicula() == l.getIDPelicula() && !p.getEstat()) { 
                    
                    System.out.println("ID: " + p.getIDPelicula() + " || Titol: " + p.getTitol());
                    noRetornades = true;
                    
                    break;  
                }
            }
        }

        if (!noRetornades) {
            System.out.println("No hi ha pelicules no retornades en aquest moment.");
        }
    }
    public void menuLloguer(ArrayList<Alquiler> lloguersArr, ArrayList<Pelicula> PeliculesArr) {
        int opcio = 0;
        do {
            System.out.println("                                                                      -----------Selecciona una opcio-----------\n");
            System.out.println("|| 1. Registrar alquiler || 2. Eliminar alquiler || 3. Tornar pelicula || 4. Modificar Alquiler || 5. Mostrar Pelicules LLogades || 6. Visualitzar pelicules disponibles || 7. Visualitzar pelicules no retornades  || 8. Sortir || ");
            opcio = Videoclub.cin.nextInt();
            switch (opcio) {
                case 1 -> {
                    registrarLloguer(lloguersArr, PeliculesArr);
                    break;
                }
                case 2 -> {
                    eliminarLloguer(lloguersArr, PeliculesArr);
                    break;
                }
                case 3 -> {
                    tornarPelicula(lloguersArr, PeliculesArr);
                    break;
                }
                case 4 -> {
                    modificarLloguer(lloguersArr, PeliculesArr);
                }
                case 5 ->{
                    mostrarPeliculesLlogades(lloguersArr, PeliculesArr);
                    return;
                }
                case 6 ->{
                    visualitzarPeliculesDisponibles(PeliculesArr);
                    return;
                }
                case 7 ->{
                    visualitzarPeliculesNoRetornades(lloguersArr, PeliculesArr);
                    return;
                }
                case 8 ->{
                    System.out.println("Tornant enrrere");
                    return;
                }
                default -> {
                    System.out.println("La opcio seleccionada no existeix, selecciona una valida.");
                }
            }
        } while (opcio != 8);
    }
}




           
            

            
