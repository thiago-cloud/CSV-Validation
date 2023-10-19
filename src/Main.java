import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println("Bem vindo ao Sistema de Validação de CSV!");
        Scanner entrada = new Scanner(System.in);

        System.out.println("\nInforme o caminho absoluto da pasta pendentes: ");
        String caminhoPastaPendente = entrada.nextLine();

        System.out.println("\nInforme o caminho absoluto da pasta válidos: ");
        String caminhoPastaValido = entrada.nextLine();

        System.out.println("\nInforme o caminho absoluto da pasta inválidos: ");
        String caminhoPastaInvalido = entrada.nextLine();


        System.out.println("Iniciando o processo de validação, detectando arquivos CSV na pasta informada");

        try {

            List<Path> arquivosCSV = Files.list(Path.of(caminhoPastaPendente))
                    .filter(arquivo -> arquivo.toString().endsWith(".csv")).collect(Collectors.toList());


            System.out.println("O sistema encontrou " + arquivosCSV.size() + " arquivos, iniciando validação");
            for (Path caminhoArquivo : arquivosCSV) {

                File arquivo = new File(caminhoArquivo.toUri());

                boolean resultado = funcaoValidadora(arquivo);

                if(resultado) {
                    Files.copy(caminhoArquivo
                            , Path.of(caminhoPastaValido + "\\" + caminhoArquivo.getFileName())
                            , StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Arquivo válido, copiado para a pasta de caminhos válidos");
                }

                else {
                    Files.copy(caminhoArquivo
                            , Path.of(caminhoPastaInvalido + "\\" + caminhoArquivo.getFileName())
                            , StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Arquivo inválido, copiado para a pasta de caminhos inválidos");
                }
            }

            System.out.println("Todos os arquivos foram validados!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("O erro acima ocorreu e por conta disso o programa foi terminado.");
        }


    }

    private static boolean funcaoValidadora(File arquivo) {
        System.out.println("Iniciando a validação do arquivo de nome: " + arquivo.getName());
        try  {
            FileReader pegaArquivo = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(pegaArquivo);
            String linha = leitor.readLine();
            String separador = ";";


            //Esse novo código vai pegando as linhas do CSV até que não haja mais nenhuma linha.
            while(linha != null) {
                String[] colunas = linha.split(separador);


                //Ele verifica primeiro Se a linha possui menos de 4 colunas e se a coluna com o nome do cliente está vazia.
                if (colunas.length == 4 && !colunas[1].isEmpty()) {
                    try {

                        //Após confirmar que o número de colunas está correto, o programa tentará transformar a coluna 0
                        //em um inteiro, a coluna 2 em uma data, e a coluna 3 em um Double
                        // (substituindo a vírgula pelo ponto, pois aqui no Brasil a gente usa vírgula).
                        Integer.parseInt(colunas[0]);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate.parse(colunas[2], formatter);
                        Double.parseDouble(colunas[3].replace(',','.'));

                    } catch (Exception e) {
                        // Se alguma dessas operações falharem, o código jogará um erro, o que indica que um dos campos
                        //é inválido, e marca o arquivo como invalidado.
                        System.out.println("Uma ou mais colunas do documento estão inválidas, favor verificar o CSV!");
                        return false;
                    }
                }

                // Entre as linhas do CSV existem linhas vazias, se esse for o caso, o else if irá verificar se as
                // colunas estão realmente erradas ou se o programa apenas capturou uma linha vazia entre uma linha e outra.
                else if(!linha.isEmpty()){
                    return false;
                }

                linha = leitor.readLine();

            }
        }

        catch (Exception e) {
            //Retorna falso caso haja erro de leitura no arquivo.
            System.out.println(e.getMessage());
            return false;
        }
        //Caso ele passe por todos os processos até que não haja nenhuma linha, a função retorna verdadeiro.
        return true;
    }
}