## POO_TBG07 - FLAPPY BIRD

O objetivo deste projeto é desenvolver uma versão do jogo Flappy Bird utilizando a linguagem de programação Java. O grupo é composto por Bernardo Magalhães - A038819 e Dario Rodrigues - A038042.
Desde o planeamento até a fase de implementação, estaremos empenhados em criar uma aplicação interativa, que permita aos jogadores controlar um pássaro, desviando-se dos obstáculos, com o objetivo de acumular o máximo de pontos possivel. Estes pontos são obtidos cada vez que é ultrapassado um obstáculo com sucesso.

### IMPLEMENTED FEATURES

1. Menu Inicial
Desenvolvemos um menu inicial intuitivo onde permite iniciar o jogo quando o jogador pretender.
     
    ![image](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/prints_jogo/menu_inicial.png)

2. Controlo do Pássaro
Implementamos um sistema de controlo do pássaro através de cliques no rato, proporcionando aos jogadores a capacidade de controlar a altitude do pássaro para evitar colisões com obstáculos.

3. Gerar Obstáculos
Criamos um sistema dinâmico para gerar obstáculos aleatórios ao longo do percurso do pássaro.


4. Sistema de Pontuação
Implementamos um sistema de pontuação que regista a pontuação do jogador à medida que avança no jogo. No final do jogo, é exibido a pontuação final e o recorde atual de pontuação

6. Gráficos Simples
Utilizamos uma biblioteca text-based chamada Lanterna para criar elementos gráficos básicos que representam o cenário do jogo.

    ![image](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/prints_jogo/jogo.png)


7. Sistema de Recompensa com Sementes
Adicionamos uma funcionalidade extra onde o jogador pode ganhar pontos adicionais ao apanhar sementes durante o jogo.

8. Menu Game-Over
Desenvolvemos um menu de final de jogo, intuitivo onde permite ao utilizador reiniciar o jogo, consultar o score obtido e sair do jogo.

   ![image](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/prints_jogo/game_over.png)

### PLANNED FEATURES

- Menu Inicial: Desenvolvimento de um menu inicial que permitirá aos jogadores iniciar o jogo, sair do jogo e ver o record de pontuação.
- Controlo do Pássaro : Implementação do controlo do pássaro com cliques no rato para voar e evitar colisões com obstáculos.
- Gerar Obstáculos: Desenvolvimento de um sistema para gerar obstáculos aleatórios ao longo do percurso do pássaro.
- Sistema de Pontuação: Criação de um mecanismo de pontuação para acompanhar e exibir a pontuação do jogador à medida que avança no jogo e guardar o melhor resultado para exibir no ecrã inicial.
- Gráficos Simples: Desenvolvimento de elementos gráficos básicos para representar o cenário e o pássaro.

  ## Mockups
  ![image](https://github.com/DarioRodrigues17/tbg07/assets/133675148/0d941a14-ade9-475d-ae1f-963421bdacce)
  
  ![image](https://github.com/DarioRodrigues17/tbg07/assets/133675148/51fea070-fe70-48de-8bcf-57fe99209499)
  
  ![image](https://github.com/DarioRodrigues17/tbg07/assets/133675148/6a9e08b8-846d-4eb5-bfdd-502b3e389e9c)

  ## UML (Intermédio)
  ![image](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/UML.png?raw=true)

  ## UML (Final)
  [UML_Final.png](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/UML_Final.png)
  ![image](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/UML_Final.png)


### DESIGN

- **Problem in Context**
  - Iniciamos o projeto com a intenção de utilizar a biblioteca text-based Lanterna para criar a interface gráfica do nosso jogo. No entanto, enfrentamos um desafio significativo: a falta de documentação e exemplos de projetos equivalentes desenvolvidos com lanterna. Assim, após várias tentativas, e para não ficarmos parados, decidimos avançar com a criação do projeto em Swing, para termos algo palpável e a funcionar, e a dai começar a adaptar para Lanterna. Isto acabou por nos trazer mais algumas dificuldades durante a realização do projeto mas, como a implementação com Swing tinha corrido relativamente bem, estávamos motivados a ultrapassar esses dificuldades e adaptar o código já desenvolvido com Swing para responder às especificações e necessidade do projeto, integrando a biblioteca Lanterna conforme necessário.

- **The Pattern** 
  - O design pattern Singleton foi escolhido para garantir a utilização de uma única instância globalmente acessível da classe FlappyBird.
  O Singleton integra-se bem ao contexto de design, fornecendo um ponto de acesso único à instância FlappyBird. Isso evita a criação de múltiplas instâncias e oferece um ponto de entrada global para uma melhor gestão e eficiência do jogo.
  
- **Implementation**
  - O Padrão Singleton é implementado na classe FlappyBird com um construtor privado (FlappyBird()) e um método estático para aceder à instância singleton (getInstance()). A instância única é armazenada numa variável estática (instance):
       
          public class FlappyBird {
          private static FlappyBird instance = null;

          private FlappyBird() {
          ...lógica do construtor
          }

          public static FlappyBird getInstance() {
          if (instance == null) {
          instance = new FlappyBird();
          }
          return instance;
          }
          
          ...
          }
[FlappyBird.java](https://github.com/DarioRodrigues17/tbg07/blob/main/src/main/java/com/flappybirdg07/Game/FlappyBird.java)  

- **Consequences**
  _- Vantagens:_
    - Instância Única: O Padrão Singleton assegura que exista apenas uma instância da classe FlappyBird em toda a aplicação, facilitando a gestão de um estado consistente do jogo e evitando problemas com múltiplas instâncias.
    - Acessibilidade Global: A instância única é globalmente acessível através do método getInstance(), proporcionando um ponto centralizado para aceder e gerir o estado do jogo. 
    - Eficiência de Recursos: O Padrão Singleton minimiza o uso de recursos ao manter uma única instância, de forma a evitar duplicação desnecessária e a garantir uma utilização eficaz de memória.

  _- Desvantagens:_
    - Estado Global: Apesar da acessibilidade global ser benéfica, pode levar a um estado global, tornando a aplicação mais desafiadora de compreender e manter. 
    - Desafios de Teste: Testar o Padrão Singleton pode ser desafiador, pois pode introduzir dependências entre diferentes partes da aplicação devido ao estado global.

  _- Comparação com Soluções Alternativas:_
    - Sem o uso do Padrão Singleton, poderia-se considerar a criação de uma instância de FlappyBird sempre que necessário. No entanto, isso poderia resultar em ineficiência de recursos e possíveis inconsistências 	no estado do jogo.
  
  _- Resumo:_
    - O Padrão Singleton traz benefícios em termos de eficiência de recursos e acessibilidade global, mas introduz desafios relacionados ao estado global e complexidades de teste. Comparativamente, as vantagens geralmente superam as 	desvantagens, especialmente em cenários onde uma única instância global é crucial para a integridade do sistema.

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS
**- Classe FlappyBird:**
  - Code Smell: 
  >A classe FlappyBird realiza várias responsabilidades, incluindo controle de estado, desenho, e execução do jogo. Isso viola o princípio de responsabilidade única.
  - Sugestão de Refactoring: 
  >Dividir as responsabilidades em classes distintas, como uma para o controle de estado, outra para o desenho, e uma terceira para a execução do jogo.

**- Método updatePipes na classe FlappyBird:**
  - Code Smell:
>O método updatePipes é extenso e realiza múltiplas operações. Além disso, contém um comentário explicativo, indicando complexidade
    - Sugestão de Refactoring:
>Dividir o método em funções menores, cada uma realizando uma tarefa específica.

**- Classe Map:**
- Code Smell:
>A classe Map possui um método extenso chamado entersOccupiedSpace, que é responsável por verificar a colisão do pássaro com os limites e tubos. Essa função viola o princípio de responsabilidade única.
- Sugestão de Refactoring:
>Refazer o método entersOccupiedSpace para dividir a lógica em funções separadas, cada uma responsável por uma verificação específica (colisão com limites, colisão com tubos, etc.).

### TESTING

![image](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/Coverage_tests.png)


### SELF-EVALUATION
A nossa autoavaliação do projeto reflete o trabalho conjunto que realizamos. Apesar da diferença de valores, 60% para 40%, não significa uma diferença no empenho ao longo do projeto. Ambos nos dedicados de igual forma e trabalhamos maioritariamente juntos no desenvolvimento projeto, mas o Dario assumiu tarefas mais complexas e, por isso, merece o reconhecimento na nota final.

- Bernardo Magalhães(A038819): 40%
- Dario Rodrigues(A038042): 60%
