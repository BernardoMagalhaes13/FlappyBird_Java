## POO_TBG07 - FLAPPY BIRD

O objetivo deste projeto é desenvolver uma versão do jogo Flappy Bird utilizando a linguagem de programação Java. O grupo é composto por Bernardo Magalhães - A038819 e Dario Rodrigues - A038042.
Desde o planeamento até a fase de implementação, estaremos empenhados em criar uma aplicação interativa, que permita aos jogadores controlar um pássaro, desviando-se dos obstáculos, com o objetivo de acumular o máximo de pontos possivel. Estes pontos são obtidos cada vez que é ultrapassado um obstáculo com sucesso.

### IMPLEMENTED FEATURES

1. Menu Inicial
Desenvolvemos um menu inicial intuitivo onde permite iniciar o jogo quando o jogador pretender.
(print)

2. Controlo do Pássaro
Implementamos um sistema de controlo do pássaro através de cliques no rato, proporcionando aos jogadores a capacidade de controlar a altitude do pássaro para evitar colisões com obstáculos.

3. Gerar Obstáculos
Criamos um sistema dinâmico para gerar obstáculos aleatórios ao longo do percurso do pássaro.
(print)

4. Sistema de Pontuação
Implementamos um sistema de pontuação que regista a pontuação do jogador à medida que avança no jogo. No final do jogo, é exibido a pontuação final e o recorde atual de pontuação
(print)

6. Gráficos Simples
Utilizamos uma biblioteca text-based chamada Lanterna para criar elementos gráficos básicos que representam o cenário do jogo.
(print)

7. Sistema de Recompensa com Sementes
Adicionamos uma funcionalidade extra onde o jogador pode ganhar pontos adicionais ao apanhar sementes durante o jogo.

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

  ## UML
  ![image](https://github.com/DarioRodrigues17/tbg07/blob/main/docs/img/UML.png?raw=true)

### DESIGN

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:

- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

**Example of one of such subsections**:

------

#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE

**Problem in Context**

There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game (an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.). This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.

**The Pattern**

We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].

**Implementation**

The following figure shows how the pattern’s roles were mapped to the application classes.

![img](https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg)

These classes can be found in the following files:

- [Character](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/Character.java)
- [JumpAbilityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/JumpAbilityState.java)
- [DoubleJumpState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/DoubleJumpState.java)
- [HelicopterState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/HelicopterState.java)
- [IncreasedGravityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/IncreasedGravityState.java)

**Consequences**

The use of the State Pattern in the current design allows the following benefits:

- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.
- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

**Example of such a subsection**:

------

#### DATA CLASS

The `PlatformSegment` class is a **Data Class**, as it contains only fields, and no behavior. This is problematic because […].

A way to improve the code would be to move the `isPlatformSegmentSolid()` method to the `PlatformSegment` class, as this logic is purely concerned with the `PlatformSegment` class.

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.

**Example**:

- John Doe: 40%
- Jane Doe: 60%
