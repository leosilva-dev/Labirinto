import java.util.Scanner;

public class PrincipalLabirinto {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String fileName = "";

        while (fileName.isEmpty()) {
            System.out.print("Informe o nome do arquivo que contém o labirinto: ");
            fileName = sc.nextLine();
        }

        sc.close();

        Labirinto labirinto = new Labirinto();

        labirinto.encontraSaida(labirinto.carregaLabirinto(fileName), 0, 0);

    }
}
