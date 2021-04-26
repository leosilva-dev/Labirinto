import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Labirinto {

    Labirinto() {
    }

    public char[][] carregaLabirinto(String filename) {
        int linesQuantity = 0;
        int columnsQuantity = 0;
        char[][] labirintoMatriz = new char[linesQuantity][columnsQuantity];

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);

            linesQuantity = Integer.parseInt(br.readLine().trim());
            columnsQuantity = Integer.parseInt(br.readLine().trim());

            String line = "";

            labirintoMatriz = new char[linesQuantity][columnsQuantity];
            while (line != null) {

                for (int r = 0; r < linesQuantity; r++) {
                    line = br.readLine();
                    for (int c = 0; c < columnsQuantity; c++) {

                        labirintoMatriz[r][c] = line.charAt(c);

                    }
                }
                line = br.readLine();

            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"" + filename + "\" não existe.");
        } catch (IOException e) {
            System.out.println("Erro na leitura de " + filename + ".");
        }

        return labirintoMatriz;
    }

    public void encontraSaida(char[][] labirinto, int row, int col) {

        imprimeLabirinto(labirinto);

        if (labirinto[row][col] == 'D') {
            escreveSaida(true);
        }

        // direita
        if (col < labirinto[row].length - 1) {
            if (canMove(labirinto, row, col + 1)) {
                setVisited(labirinto, row, col);
                encontraSaida(labirinto, row, col + 1);
            }
        }

        // esquerda
        if (col >= 1) {
            if (canMove(labirinto, row, col - 1)) {
                setVisited(labirinto, row, col);
                encontraSaida(labirinto, row, col - 1);
            }
        }

        // cima
        if (row >= 1) {
            if (canMove(labirinto, row - 1, col)) {
                setVisited(labirinto, row, col);
                encontraSaida(labirinto, row - 1, col);
            }
        }

        // // baixo
        if (row < labirinto.length - 1) {
            if (canMove(labirinto, row + 1, col)) {
                setVisited(labirinto, row, col);
                encontraSaida(labirinto, row + 1, col);
            }
        }

    }

    private boolean canMove(char[][] labirinto, int row, int col) {
        if (labirinto[row][col] == 'X') {
            return false;
        } else if (labirinto[row][col] == ' ') {
            return true;
        } else if (labirinto[row][col] == '_') {
            return false;
        } else if (labirinto[row][col] == 'D') {
            return true;
        }

        return false;
    }

    private char[][] setVisited(char[][] labirinto, int row, int col) {
        labirinto[row][col] = '_';
        return labirinto;
    }

    private void imprimeLabirinto(char[][] labirinto) {
        System.out.println();
        System.out.println("-----------------------------");
        for (int r = 0; r < 8; r++) {
            System.out.println();
            for (int c = 0; c < 20; c++) {
                System.out.print(labirinto[r][c]);
            }
        }
    }

    private void escreveSaida(boolean temSaida) {
        try {
            File fileToWrite = new File("saidaLabirinto.txt");
            FileWriter fr = new FileWriter(fileToWrite);
            PrintWriter out = new PrintWriter(fr);

            if (temSaida) {
                out.println("Existe um caminho para o labirinto");
            } else {
                out.println("Não existe um caminho para o labirinto");
            }

            out.close();

        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo.");
        }

    }
}
