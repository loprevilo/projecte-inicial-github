package Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import videoclub.Videoclub;

public class Cliente {
    private String nombre;
    private String apellido;
    private String email;
    private static int secuenciaIDCliente = 0;
    private int idCliente;
    private LocalDate fechaRegistro;

    // Constructor con parámetros
    public Cliente(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.idCliente = secuenciaIDCliente++;
        this.fechaRegistro = LocalDate.now();
    }

    // Constructor sin parámetros
    public Cliente() {
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.idCliente = secuenciaIDCliente++;
        this.fechaRegistro = LocalDate.now();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Método para registrar un nuevo cliente
    public void registrarCliente(ArrayList<Cliente> listaClientes) {
        Videoclub.cin.nextLine(); // Buffer

        System.out.println("Nombre del cliente: ");
        String nombreC = Videoclub.cin.nextLine();

        System.out.println("Apellido del cliente: ");
        String apellidoC = Videoclub.cin.nextLine();

        System.out.println("Email del cliente: ");
        String emailC = Videoclub.cin.nextLine();

        Cliente nuevoCliente = new Cliente(nombreC, apellidoC, emailC);
        System.out.println("Fecha de registro del cliente: " + nuevoCliente.getFechaRegistro());
        System.out.println("ID asignado: " + nuevoCliente.getIdCliente());

        listaClientes.add(nuevoCliente);
    }

    // Método para eliminar un cliente
    public void eliminarCliente(ArrayList<Cliente> listaClientes) {
        System.out.println("Introduce el ID del cliente que quieres eliminar: ");
        int idEliminar = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); // Buffer

        boolean clienteEliminado = false;

        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getIdCliente() == idEliminar) {
                listaClientes.remove(i);
                clienteEliminado = true;
                System.out.println("El cliente con ID " + idEliminar + " ha sido eliminado.");
                break;
            }
        }

        if (!clienteEliminado) {
            System.out.println("No se encontró ningún cliente con el ID introducido.");
        }
    }

    // Método para modificar un cliente
    public void modificarCliente(ArrayList<Cliente> listaClientes) {
        System.out.println("Introduce el ID del cliente que quieres modificar: ");
        int idModificar = Videoclub.cin.nextInt();
        Videoclub.cin.nextLine(); // Buffer

        for (Cliente c : listaClientes) {
            if (c.getIdCliente() == idModificar) {
                int opcion = 0;
                do {
                    System.out.println("-----------¿Qué dato quieres modificar del cliente " + c.getNombre() + "? -----------\n");
                    System.out.println("       || 1. Nombre || 2. Apellido || 3. Email || 4. Volver atrás ||\n");
                    opcion = Videoclub.cin.nextInt();
                    Videoclub.cin.nextLine(); // Buffer

                    switch (opcion) {
                        case 1 -> {
                            System.out.print("Introduce el nuevo nombre (El actual es: " + c.getNombre() + "): ");
                            String nuevoNombre = Videoclub.cin.nextLine();
                            c.setNombre(nuevoNombre);
                            System.out.println("El nuevo nombre es: " + c.getNombre());
                        }
                        case 2 -> {
                            System.out.print("Introduce el nuevo apellido (El actual es: " + c.getApellido() + "): ");
                            String nuevoApellido = Videoclub.cin.nextLine();
                            c.setApellido(nuevoApellido);
                            System.out.println("El nuevo apellido es: " + c.getApellido());
                        }
                        case 3 -> {
                            System.out.print("Introduce el nuevo email (El actual es: " + c.getEmail() + "): ");
                            String nuevoEmail = Videoclub.cin.nextLine();
                            c.setEmail(nuevoEmail);
                            System.out.println("El nuevo email es: " + c.getEmail());
                        }
                        case 4 -> {
                            System.out.println("Volviendo al menú anterior.");
                        }
                        default -> {
                            System.out.println("La opción introducida no es válida. Por favor, selecciona una opción válida.");
                        }
                    }
                } while (opcion != 4);
                return;
            }
        }
        System.out.println("No se encontró ningún cliente con el ID introducido.");
    }

    // Método para mostrar los clientes registrados
    public void mostrarClientes(ArrayList<Cliente> listaClientes) {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados actualmente.");
            return;
        }

        System.out.println("Lista de clientes registrados:");
        for (Cliente c : listaClientes) {
            System.out.println("----------------------------------");
            System.out.println("ID Cliente: " + c.getIdCliente());
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Apellido: " + c.getApellido());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Fecha de registro: " + c.getFechaRegistro());
        }
        System.out.println("----------------------------------");
    }

    // Menú principal para gestionar clientes
    public void menuCliente(ArrayList<Cliente> listaClientes) {
        int opcion = 0;
        do {
            System.out.println("-----------Selecciona una opción-----------");
            System.out.println("|| 1. Registrar cliente || 2. Eliminar cliente || 3. Modificar cliente || 4. Mostrar clientes || 5. Volver atrás ||");

            opcion = Videoclub.cin.nextInt();

            switch (opcion) {
                case 1 -> registrarCliente(listaClientes);
                case 2 -> eliminarCliente(listaClientes);
                case 3 -> modificarCliente(listaClientes);
                case 4 -> mostrarClientes(listaClientes);
                case 5 -> System.out.println("Volviendo al menú anterior.");
                default -> System.out.println("La opción seleccionada no es válida, por favor selecciona una opción correcta.");
            }
        } while (opcion != 5);
    }
}








