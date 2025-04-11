/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pelicula;

import java.util.ArrayList;
import videoclub.Videoclub;

/**
 *
 * @author polol
 */

public class Pelicula {
    private String titulo;
    private String genero;
    private int anyoEstreno;
    private String Descripcion;
    private static int IDPelicula_sequel = 0; // Comptador d'ID
    private int IDPelicula;
    private Boolean estado; // Llogades o no

    // Getters y Setters
    public String getTitol() {
        return titulo;
    }

    public void setTitol(String titulo) {
        this.titulo = titulo;
    }

    public String getGenere() {
        return genero;
    }

    public void setGenere(String genero) {
        this.genero = genero;
    }

    public int getAnyEstrena() {
        return anyoEstreno;
    }

    public void setAnyEstrena(int anyoEstreno) {
        this.anyoEstreno = anyoEstreno;
    }

    public String getDescripcio() {
        return Descripcion;
    }

    public void setDescripcio(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIDPelicula() {
        return IDPelicula;
    }

    public Boolean getEstat() {
        return estado;
    }

    public void setEstat(Boolean estado) {
        this.estado = estado;
    }

    
    // Constructor amb paràmetres
    public Pelicula(String titulo, String genero, int anyoEstreno, String Descripcion) {
        this.titulo = titulo;
        this.genero = genero;
        this.anyoEstreno = anyoEstreno;
        this.Descripcion = Descripcion;
        //Serveix per incrementar l'ID automaticament a l'hora de crear
        this.IDPelicula = IDPelicula_sequel++; 
        //Serveix per indicar que les pel·licules estaràn disponibles per defecte
        this.estado = true; 
    }

    // Constructor sense paràmetres (serveix per cridar al menú al main).
    public Pelicula() {
    }

    //Aquest mètode serveix per registrar una pel·licula.
    public void registrarPelicula(ArrayList<Pelicula> PeliculesArr) {
        Videoclub.cin.nextLine(); //Buffer
        
        System.out.print("Titulo de la pelicula: ");
        String titulo = Videoclub.cin.nextLine();
        
        System.out.print("Genero de la pelicula: ");
        String genero = Videoclub.cin.nextLine();
        
        System.out.print("Any d'estrena: ");
        int anyoEstreno = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer
        
        System.out.print("Descripcion: ");
        String descripcion = Videoclub.cin.nextLine();
        //Afegim la pel·licula registrada al ArrayList.
        Pelicula novaPelicula = new Pelicula(titulo, genero, anyoEstreno, descripcion);
        PeliculesArr.add(novaPelicula);
        
        System.out.println("Pelicula registrada amb ID: " + novaPelicula.getIDPelicula());
    }

    //Aquest mètode serveix per eliminar una pel·licula registrada.
    public void eliminarPelicula(ArrayList<Pelicula> PeliculesArr){
        //Es demana l'ID de la pel·licula a eliminar
        System.out.print("Introdueix l'ID de la pelicula que vols eliminar: ");
        int idEliminar = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer
        //Serveix per controlar si la peli s'elimina.
        boolean peliculaEliminada = false;
        
        for (int i = 0; i < PeliculesArr.size(); i++){
            //Es comprova si l'ID introduit concideix amb un existent
            if (PeliculesArr.get(i).getIDPelicula() == idEliminar){
                //si s'ha trovat una pelicula conicident s'elimina la pelicula i es passa el Boolean a treu per verificar la eliminació
                PeliculesArr.remove(i);
                peliculaEliminada = true;
                System.out.println("La pelicula amb ID " + idEliminar + " ha estado eliminada.");
                break;
            }
        }
        //Si no es verifica la eliminació es mostra missatge d'error.
        if (!peliculaEliminada) {
            System.out.println("No s'ha trobat cap pelicula amb l'ID introduida.");
        }
    }
    
    //Aquest mètode serveix per modificar una peli registrada
    public void modificarPelicula(ArrayList<Pelicula> PeliculesArr){
        
        System.out.print("Introdueix l'ID de la pelicula que vols modificar: ");
        int idModificar = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); //Buffer
        
        for (Pelicula p : PeliculesArr) {
            //Es comprova si l'id introduit coincideix amb un existent
            if (p.getIDPelicula() == idModificar) {
                int opcion = 0;
                //El seguent do while serveix per poder tornar enrrere
                do {
                    System.out.println("       Quina dada vols modificar de la pelicula '" + p.getTitol() + "'?\n");
                    System.out.println("|| 1. Titulo || 2. Genero || 3. Any Estrena || 4. Descripcion || 5. Tirar enrere ||\n");
                    opcion = Videoclub.cin.nextInt();
                    Videoclub.cin.nextLine(); //Buffer
                    
                    switch (opcion) {
                        case 1 -> {
                            System.out.print("Introdueix el nou titulo (L'actual es: " + p.getTitol() + "): ");
                            String nouTitol = Videoclub.cin.nextLine();
                            p.setTitol(nouTitol);
                            System.out.println("El nou titulo es: " + p.getTitol());
                        }
                        case 2 -> {
                            System.out.print("Introdueix el nou genero (L'actual es: " + p.getGenere() + "): ");
                            String nouGenere = Videoclub.cin.nextLine();
                            p.setGenere(nouGenere);
                            System.out.println("El nou genero es: " + p.getGenere());
                        }
                        case 3 -> {
                            System.out.print("Introdueix el nou any d'estrena (L'actual és: " + p.getAnyEstrena() + "): ");
                            int nouAny = Videoclub.cin.nextInt();
                            Videoclub.cin.nextLine();
                            p.setAnyEstrena(nouAny);
                            System.out.println("El nou any d'estrena és: " + p.getAnyEstrena());
                        }
                        case 4 -> {
                            System.out.print("Introdueix la nova descripcion (L'actual és: " + p.getDescripcio() + "): ");
                            String novaDescripcio = Videoclub.cin.nextLine();
                            p.setDescripcio(novaDescripcio);
                            System.out.println("La nova descripcion és: " + p.getDescripcio());
                        }
                        case 5 -> {
                            System.out.println("Tornant al menú anterior...");
                        }
                        default -> {
                            System.out.println("L'opcion introduïda no és correcta, escull una vàlida.");
                        }
                    }
                } while (opcion != 5);
                return;
            }
        }
        System.out.println("No s'ha trobat cap pel·lícula amb l'ID introduïda.");
    }

    //Aquest mètode serveix per utlilitzar els diferents mètodes de Client (amb el format d'un menú).
    public void menuPelicula(ArrayList<Pelicula> PeliculesArr) {
        int opcion = 0;
        do {
            System.out.println("                            -----------Selecciona una opcion----------- \n");
            System.out.println("      || 1. Registrar pelicula || 2. Eliminar pelicula || 3. Modificar pelicula || 4. Enrrere ||      ");
            opcion = Videoclub.cin.nextInt();
            switch (opcion) {
                case 1 -> {
                    registrarPelicula(PeliculesArr);
                    break;
                }
                case 2 -> {
                    eliminarPelicula(PeliculesArr);
                    break;
                }
                case 3 -> {
                    modificarPelicula(PeliculesArr);
                    break;
                }
                case 4 -> {
                    System.out.println("Tornant al menu anterior");
                    break;
                }
                default -> {
                    System.out.println("La opcion seleccionada no es correcte, selecciona una valida.");
                }
            }
        } while (opcion != 4);
    }
}
