# starwars-api-java

Uma api que satisfaz os seguintes Requisitos:

- A API deve ser REST
- Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente:
•	Nome
•	Clima
•	Terreno
- Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas pela API pública do Star Wars:  https://swapi.co/

Funcionalidades: 

- Adicionar um planeta (com nome, clima e terreno)
- Listar planetas
- Buscar por nome
- Buscar por ID
- Remover planeta

Desafio feito usando MongoDB, Spring Boot/Spring Web/Spring Data e Lombok.

As apis e os métodos para chamada são:

•	/api/planetas (Get) para listar todos os planetas cadastrados
•	/api/planetas/id/{id} (Get) para selecionar apenas um planeta pelo id
•	/api/planetas/nome/{nome} (Get) para selecionar apenas um planeta pelo nome
•	/api/planetas (Post) para cadastrar um planeta
•	/api/planetas (Put) para alterar um cadastro de um planeta
•	/api/planetas (Delete) para excluir um cadastro de um planeta, passando as informações dele
•	/api/planetas/{id} (Delete) para excluir um cadastro de um planeta passando o id