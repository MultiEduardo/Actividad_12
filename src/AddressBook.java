import java.io.*;
import java.util.*;

// hay que convertir esto a un arcivo txt y que lo lea
public class AddressBook {
    static Scanner scanner = new Scanner(System.in);
    static int select = -1;
    static String Nombre;
    static String telefono;


    static HashMap<String,String> contacto;
    public AddressBook() throws IOException {
        contacto = new HashMap<String, String>();
        guardar(telefono, Nombre, contacto);
        mostarContacto(contacto);
    }

    public static void guardar(String telefono, String nombre,  HashMap<String, String> contacto) throws IOException {
        //Verifica si el telefono se repite o no en dado caso que no se repita hara el push
        if (contacto.containsKey(telefono)){
            System.out.println("No se puede introducir el contato. El numero de telfono esta repetido\n");
        }else{
            contacto.put(telefono,  " " + nombre);
            System.out.println();
        }
        //crea el archivo si no existe, en dado caso de existir solo pondra los datos que no tengan el
        //telefono repetido
        File Contactos =new File("Contactos.txt");
        FileOutputStream fos=new FileOutputStream(Contactos);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(contacto);
        oos.flush();
        oos.close();
        fos.close();


    }

    public static void mostarContacto(HashMap<String, String> contacto)  {
        try{
            //busca todos los datos del txt y los muestra
            File Contactos = new File("Contactos.txt");
            FileInputStream fis = new FileInputStream(Contactos);
            ObjectInputStream ois = new ObjectInputStream(fis);

            contacto = (HashMap<String, String>) ois.readObject();

            ois.close();
            fis.close();
            for(Map.Entry<String,String> m :contacto.entrySet()){
                System.out.println(m.getKey()+" : "+m.getValue());
            }
        }catch (Exception e){

        }

    }

    public static void eliminarContacto(String telefono, HashMap<String, String> contacto) throws IOException {
        //Verifica que el numero exista y en dado caso de existir elimina el numero
        if (contacto.containsKey(telefono)){
            contacto.remove(telefono);
            System.out.println("Se ha eliminado con exito el usuario.\n");
        }else{
            System.out.println("No existe el contacto.\n");
        }
        //se hace una actualizacion al archivo txt
        File Contactos =new File("Contactos.txt");
        FileOutputStream fos=new FileOutputStream(Contactos);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(contacto);
        oos.flush();
        oos.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException {
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
                        telefono = scanner.nextLine();
                        guardar(telefono,Nombre, addressBook.contacto);
                        break;
                    case 2:
                        mostarContacto(addressBook.contacto);
                        break;
                    case 3:
                        System.out.print("introduzca el numero de telefono: ");
                        telefono = scanner.nextLine();
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
