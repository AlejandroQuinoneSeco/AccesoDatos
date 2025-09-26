package app;

import service.FileService;

public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java -jar programa.jar <carpetaOrigen> <ficheroSalida>");
            return;
        }

        String rutaCarpeta = args[0];
        String rutaSalida = args[1];

        FileService service = new FileService();
        service.generarListado(rutaCarpeta, rutaSalida);
    }
}
