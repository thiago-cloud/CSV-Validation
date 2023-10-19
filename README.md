# csv-validation
Um software criado em Java para validação de arquivos CSV é uma ferramenta valiosa para empresas e profissionais que trabalham com dados armazenados em formato CSV (Comma-Separated Values). Essa aplicação permite verificar e garantir a integridade e precisão dos dados contidos em arquivos CSV, facilitando a análise e o uso desses dados.

##Validação de campos

Agora, o leitor de CSV também irá ler as linhas de todos os arquivos para verificar
a validez dos campos. Ele validará se o campo de preço é válido, se o nome do cliente
foi informado, se a data é válida e se o preço é válido. Junto do projeto, irão mais
alguns arquivos .CSV que exemplificam quais e como as funcionalidades de validação funcionam.
Abaixo está um exemplo de como o código se comporta:

```
"C:\Program Files\Java\jdk-19\bin\java.exe" -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:57408,suspend=y,server=n -javaagent:C:\Users\pejo5\AppData\Local\JetBrains\IdeaIC2022.2\captureAgent\debugger-agent.jar -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\pejo5\Documents\csv-validation\out\production\validador-de-csv;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2.3\lib\idea_rt.jar" Main
Connected to the target VM, address: '127.0.0.1:57408', transport: 'socket'
Bem vindo ao Sistema de Validação de CSV!

Informe o caminho absoluto da pasta pendentes: 
C:\Users\pejo5\Documents\csv-validation\src\pastaPendente

Informe o caminho absoluto da pasta válidos: 
C:\Users\pejo5\Documents\csv-validation\src\pastaValido

Informe o caminho absoluto da pasta inválidos: 
C:\Users\pejo5\Documents\csv-validation\src\pastaInvalido
Iniciando o processo de validação, detectando arquivos CSV na pasta informada
O sistema encontrou 7 arquivos, iniciando validação
Iniciando a validação do arquivo de nome: arquivo.csv
Arquivo válido, copiado para a pasta de caminhos válidos
Iniciando a validação do arquivo de nome: arquivoInvalido.csv
Arquivo inválido, copiado para a pasta de caminhos inválidos
Iniciando a validação do arquivo de nome: arquivoInvalidoDataInvalida.csv
Uma ou mais colunas do documento estão inválidas, favor verificar o CSV!
Arquivo inválido, copiado para a pasta de caminhos inválidos
Iniciando a validação do arquivo de nome: arquivoInvalidoPrecoInvalido.csv
Uma ou mais colunas do documento estão inválidas, favor verificar o CSV!
Arquivo inválido, copiado para a pasta de caminhos inválidos
Iniciando a validação do arquivo de nome: arquivoInvalidoSemNomeLinha3.csv
Arquivo inválido, copiado para a pasta de caminhos inválidos
Iniciando a validação do arquivo de nome: arquivoInvalidoSemUmCampo.csv
Arquivo inválido, copiado para a pasta de caminhos inválidos
Iniciando a validação do arquivo de nome: arquivoValido.csv
Arquivo válido, copiado para a pasta de caminhos válidos
Todos os arquivos foram validados!
```