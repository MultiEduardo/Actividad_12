import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
// hay que convertir esto a un arcivo txt y que lo lea
public class AddressBook {
    static Scanner scanner = new Scanner(System.in);
    static int select = -1;
    static String Nombre;
    static int telefono;

    private HashMap<Integer,String> contacto = new HashMap<Integer, String>();

    public static void guardar(int telefono, String nombre, HashMap<Integer, String> contacto){
        if (contacto.containsKey(telefono)){
            System.out.println("No se puede introducir el contacto. El numero de telfono esta repetido\n");
        }else{
            contacto.put(telefono,  " " + nombre);
            System.out.println();
        }
    }

    public static void mostarContacto(HashMap<Integer,String> contacto){
        Integer clave;
        Iterator<Integer> Contacto = contacto.keySet().iterator();
        System.out.println("Contactos: ");
        while (Contacto.hasNext()){
            clave = Contacto.next();
            System.out.print("{ "+ clave + " }" +":" + "{" + contacto.get(clave) + " }");
            System.out.println("\n");
        }
    }

    public static void eliminarContacto(int telefono, HashMap<Integer,String> contacto){
        if (contacto.containsKey(telefono)){
            contacto.remove(telefono);
            System.out.println("Se ha eliminado con exito el usuario.\n");
        }else{
            System.out.println("No existe el contacto.\n");
        }
    }

    public static void main(String[] args){
        AddressBook addressBook = new AddressBook();

        //Mientras la opción elegida sea 0, se termina el programa
        while(select != 0){
            //Try catch para evitar que el programa termine si hay un error
            try {
                System.out.println("Bienvenido a AddressBook!\n"+"Elige un opción:\n1.- Agregar Contacto" +
                        "\n2.- Mostar Contactos\n" +
                        "3.- Eliminar contacto\n" +
                        "0.- Salir");
                select = Integer.parseInt(scanner.nextLine());
                switch (select){
                    case 1:
                        System.out.println("Dame el nombre:");
                        Nombre = scanner.nextLine();
                        System.out.println("Dame el telefono:");
                        telefono = scanner.nextInt();
                        guardar(telefono, Nombre, addressBook.contacto);
                        break;
                    case 2:
                        mostarContacto(addressBook.contacto);
                        break;
                    case 3:
                        System.out.print("introduzca el numero de telefono: ");
                        telefono = scanner.nextInt();
                        eliminarContacto(telefono, addressBook.contacto);
                        break;
                    case 0:
                        System.out.println("Gracias por su visita");
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }

            }catch (Exception e){
                System.out.println("Error");
            }
        }
    }
}
