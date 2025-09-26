package app;

import service.PaisesService;

public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java -jar programa.jar <rutaXML>");
            return;
        }

        String rutaXML = args[0];
        PaisesService service = new PaisesService();
        service.mostrarPaises(rutaXML);
    }
}
