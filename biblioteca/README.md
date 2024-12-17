# Sistema de Gerenciamento de Biblioteca

## Visão Geral
O Sistema de Gerenciamento de Biblioteca é uma aplicação desenvolvida para facilitar a administração de livros, membros, empréstimos e devoluções em uma biblioteca. A aplicação foi construída usando Java, Spring Boot e Thymeleaf, e segue a arquitetura MVC (Model-View-Controller) para garantir a separação de preocupações.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Thymeleaf

## Arquitetura MVC
O sistema segue a arquitetura MVC, que separa a aplicação em três componentes principais:
- **Model**: Representa os dados e a lógica de negócios do sistema. Inclui as entidades `Book`, `Author`, `Publisher`, `Member`, `Loan`, `ItemLoan` e `Fines`.
- **View**: Responsável por renderizar a interface do usuário. Utiliza templates HTML com Thymeleaf.
- **Controller**: Lida com as requisições HTTP e interage com a camada de serviço (`LibraryService`) para processar a lógica de negócios. O controlador principal é o `LibraryController`.

## Padrões de Design Implementados
### Factory Pattern
- **Localização**: `LibraryFactory` na pasta `com.biblioteca.service`.
- **Descrição**: O padrão Factory é usado para criar objetos sem expor a lógica de criação ao cliente. Ele promove acoplamento fraco e maior flexibilidade no código.
- **Implementação**: A classe `LibraryFactory` contém métodos estáticos para criar instâncias de `Book`, `Author`, `Publisher` e `Member`.

### Observer Pattern
- **Localização**: `BookIssueReturnSubject` e `Librarian` na pasta `com.biblioteca.observer`.
- **Descrição**: O padrão Observer permite criar um mecanismo de assinatura para que múltiplos objetos (observadores) sejam notificados sobre mudanças em outro objeto (sujeito).
- **Implementação**: A classe `BookIssueReturnSubject` mantém uma lista de observadores e os notifica sobre eventos como empréstimo e devolução de livros. A classe `Librarian` implementa a interface `Observer` e define o método `update` para lidar com notificações.

### Singleton Pattern
- **Implementação**: Utilizado na camada de serviço do Spring Boot, onde as classes de serviço são gerenciadas como singletons pelo contêiner IoC do Spring.
- **Descrição**: O padrão Singleton garante que uma classe tenha apenas uma instância e fornece um ponto global de acesso a ela. A classe `LibraryService` é um exemplo de singleton.

## Funcionalidades do Sistema
- **Gerenciamento de Livros**: Adicionar, remover e atualizar disponibilidade de livros. Visualizar lista de livros disponíveis.
- **Gerenciamento de Membros**: Adicionar e remover membros. Visualizar lista de membros registrados.
- **Gerenciamento de Autores**: Adicionar e remover autores. Visualizar lista de autores registrados.
- **Gerenciamento de Editoras**: Adicionar e remover editoras. Visualizar lista de editoras registradas.
- **Empréstimo e Devolução de Livros**: Empréstimo de livros com notificação aos observadores. Devolução de livros com notificação aos observadores. Cálculo de multas baseado na data de devolução.

## Diagramas
### Diagrama de Classes
Inclui todas as entidades do sistema e suas relações, além da integração do padrão Observer.
![Diagrama de Classes](/biblioteca/images/cD.png)
- **Empréstimo de Livro**: Classes utilizadas no empréstimo.
![Diagrama de Classes](/biblioteca/images/cDissue.png)
- **Devolução de Livro**: Classes utilizadas na devolução.
![Diagrama de Classes](/biblioteca/images/cDreturn.png)

### Diagramas de Sequência
- **Empréstimo de Livro**: Mostra o fluxo de interação ao emprestar um livro.
![Diagrama de Sequência - Empréstimo](/biblioteca/images/borrow.png)
- **Devolução de Livro**: Mostra o fluxo de interação ao devolver um livro.
![Diagrama de Sequência - Devolução](/biblioteca/images/return.png)

## Como Executar
1. **Pré-requisitos**: Certifique-se de ter Java e Maven instalados.
2. **Clonar o Repositório**: 
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd biblioteca
    ```
3. **Construir o Projeto**:
    ```bash
    mvn clean install
    ```
4. **Executar a Aplicação**:
    ```bash
    mvn spring-boot:run
    ```
5. **Acessar a Aplicação**:
    - Abra um navegador e vá para `http://localhost:8080/library`.


