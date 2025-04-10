/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;
import java.time.LocalDate;
import java.util.ArrayList;
import videoclub.Videoclub;

public class Client {
    private String nom;
    private String cognom;
    private String email;
    private static int IDclient_sequel = 0; 
    private int IDclient;
    private LocalDate data_registre; 


    //Constructor amb paràmetres per assignar les dades del client.
public Client(String nom, String cognom, String email) {
    this.nom = nom;
    this.cognom = cognom;
    this.email = email;
    this.IDclient = IDclient_sequel++; // Asignación del ID y luego incremento
    this.data_registre = LocalDate.now();
    }


    //Constructor sense paràmetres
public Client() {
    this.nom = "";
    this.cognom = "";
    this.email = "";
    this.IDclient = IDclient_sequel++; // Asigna un nuevo ID
    this.data_registre = LocalDate.now();
    }

    //Getter i Setter
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIDclient() {
        return IDclient;
    }

    public void setIDclient(int IDclient) {
        this.IDclient = IDclient;
    }

    public LocalDate getData_registre() {
        return data_registre;
    }

    public void setData_registre(LocalDate data_registre) {
        this.data_registre = data_registre;
    }

    //Aquest mètode serveix per registrar un nou client
    public void registrarClient(ArrayList<Client> ClientsArr){
        
        //Cridem a l'escaner per natejar el Buffer (aquesta comanda es repetirà 
        //a lo llarg de totes les classes, per tant, per evitar el mateix comentari
        //a partir d'ara només posarà: //Buffer
        Videoclub.cin.nextLine(); 

        System.out.println("Nom client: ");
        String nomC = Videoclub.cin.nextLine();

        System.out.println("Cognom client: ");
        String cognomC = Videoclub.cin.nextLine();

        System.out.println("Email client: ");
        String emailC = Videoclub.cin.nextLine();

        //Creem el nou client amb el constructor
        Client Client1 = new Client(nomC, cognomC, emailC);
        System.out.println("Data de registre de client: " + Client1.getData_registre());
        System.out.println("ID assignat: " + Client1.getIDclient());

        //Agreguem el client a l'ArrayList de client
        ClientsArr.add(Client1);
    }

//Aquest mètode serveix per eliminar el client
public void eliminarClient(ArrayList<Client> ClientsArr){ 
    
    //Es demana l'ID del client que es vol eliminar
    System.out.println("Introdueix l'ID del client que vols eliminar: ");
    int idEliminar = Videoclub.cin.nextInt();
    Videoclub.cin.nextLine(); //Buffer

    //Comprovardor per a la continuitat del mètode
    //(Comprova si s'ha eliminat el client o no
    boolean clientEliminat = false;

    
    for (int i = 0; i < ClientsArr.size(); i++){
        //Es comprova si l'ID assignat per part de l'usuari es correspont amb el d'un client.
        if (ClientsArr.get(i).getIDclient() == idEliminar){
            //S'elimina al client amb l'ID coincident
            ClientsArr.remove(i);
            //Es confirma l'eliminació del client per poder continuar.
            clientEliminat = true;
            
            System.out.println("El client amb ID " + idEliminar + " s'ha eliminat.");
            break;
        }
    }
    //Si no s'ha arribat a verificar la eliminació, es mostra missatge d'error
    if (!clientEliminat) {
        System.out.println("No s'ha trobat cap client amb la ID introduida.");
    }
}
//Aquest mètode serveix per modificar un client
public void modificarClient(ArrayList<Client> ClientsArr) {
    //Es demana a l'usuari l'ID del client a modificar
    System.out.println("Introdueix l'ID del client que vols modificar: ");
    int idModificar = Videoclub.cin.nextInt();
    Videoclub.cin.nextLine(); //Buffer

    for (Client c : ClientsArr) {
        //Es comprova que l'ID intorduït coincideix amb un ID existent
        if (c.getIDclient() == idModificar) {
            int opcio = 0;
            //El seguent do while serveix per poder tornar enrrere
            do {
                System.out.println("-----------Quina dada vols modificar del client " + c.getNom() + "? -----------\n");
                System.out.println("       || 1. Nom || 2. Cognom || 3. Email || 4. Tirar enrere ||\n");
                opcio = Videoclub.cin.nextInt();
                Videoclub.cin.nextLine(); //Buffer

                switch (opcio) {
                    case 1 -> {
                        System.out.print("Introdueix el nou nom (L'actual es: " + c.getNom() + "): ");
                        String nouNom = Videoclub.cin.nextLine();
                        c.setNom(nouNom);
                        
                        System.out.println("El nou nom es: " + c.getNom());
                    }
                    case 2 -> {
                        System.out.print("Introdueix el nou cognom (L'actual es: " + c.getCognom() + "): ");
                        String nouCognom = Videoclub.cin.nextLine();
                        c.setCognom(nouCognom);
                        
                        System.out.println("El nou cognom es: " + c.getCognom());
                    }
                    case 3 -> {
                        System.out.print("Introdueix el nou email (L'actual es: " + c.getEmail() + "): ");
                        String nouEmail = Videoclub.cin.nextLine();
                        c.setEmail(nouEmail);
                        
                        System.out.println("El nou email es: " + c.getEmail());
                    }
                    case 4 ->{
                        System.out.println("Tornant al menu anterior");
                    }
                    default -> System.out.println("L'opcio introduida no es correcta, escull una valida.");
                }
            } while (opcio != 4);
        }
        else{
            System.out.println("No s'ha trobat cap client amb la ID introduida."); 
        }
    }  
}
//Aquest mètode serveix per mostrar els clients registrats.
public void mostrarClients(ArrayList<Client> ClientsArr) {
    //es comprova si hi han clients, en cas de que no hi hagin, es surt del mètode
    if (ClientsArr.size() == 0) {
        System.out.println("No hi ha clients registrats actualment.");
        return; 
    }
    //Es mostra un llistat dels clients i la seva informació
    System.out.println("Llista de clients registrats:");
    for (Client c : ClientsArr) {
        System.out.println("----------------------------------");
        System.out.println("ID Client: " + c.getIDclient()+"\n");
        System.out.println("Nom: " + c.getNom()+"\n");
        System.out.println("Cognom: " + c.getCognom()+"\n");
        System.out.println("Email: " + c.getEmail()+"\n");
        System.out.println("Data de registre: " + c.getData_registre()+"\n");
    }
    System.out.println("----------------------------------");
}
//Aquest mètode serveix per utlilitzar els diferents mètodes de Client (amb el format d'un menú).
public void menuClient(ArrayList<Client> ClientsArr){
    int opcio = 0;
    do {
        System.out.println("                                -----------Selecciona una opcio----------- \n");
        System.out.println("      || 1. Registrar client || 2. Eliminar client || 3. Modificar client || 4. Mostrar client || 5. Enrrere ||     ");

    opcio = Videoclub.cin.nextInt();
    switch(opcio){
        case 1 ->{
            registrarClient(ClientsArr);
            break;
        }
        case 2 ->{
            eliminarClient(ClientsArr);
            break;
        }
         case 3 ->{
            modificarClient(ClientsArr);
            break;
        }
          case 4 ->{
            mostrarClients(ClientsArr);
            break;
        }
          case 5 ->{
            System.out.println("Tornant al menu anterior");
            break;
        }
          
         default ->{
             System.out.println("La opcio seleccionada no es correcte, selecciona una valida.");
         }
    }
    }while (opcio != 5);
}




}









