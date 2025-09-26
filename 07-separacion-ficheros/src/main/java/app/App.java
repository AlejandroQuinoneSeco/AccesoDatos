package app;

import service.SplitService;

public class App {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: <ficheroEntrada> <ficheroDirectorios> <ficheroFicheros>");
            return;
        }

        String rutaEntrada = args[0];
        String salidaDirs = args[1];
        String salidaFiles = args[2];

        SplitService service = new SplitService();
        service.separar(rutaEntrada, salidaDirs, salidaFiles);
    }
}
