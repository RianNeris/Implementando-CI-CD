
# 🦸 Desenvolvimento de API RESTful para personagens 🚀

## Contexto:
Para atender a essa demanda, uma equipe de desenvolvedores talentosos decide criar uma API RESTful dedicada exclusivamente aos personagens dos jogadores. Esta API permitirá que os desenvolvedores de jogos integrem facilmente funcionalidades de personagens em seus próprios jogos, proporcionando uma experiência mais envolvente aos usuários.

## Requisitos Funcionais e Exemplos de URL/URI:

# NÃO ALTERAR O DATA.SQL

## Modelos das DTO's

PersonagemConsultaDto
```json
{
  "id": 1,
  "dataNascimento": "1971-02-20",
  "codinome": "Lanterna Verde Com Bateria Fraca",
  "habilidade": "Anel Descarregado",
  "poder": 65.0,
  "classificacao": "Mediano",
  "idadeAproximada": 53
}
```

PersonagemCriacaoDto
```json
{
  "dataNascimento": "1971-02-20",
  "nome":  "Hal Jordan",
  "codinome": "Lanterna Verde Com Bateria Fraca",
  "habilidade": "Anel Descarregado",
  "poder": 65.0
}
```

### 📋 Listagem de Todos os Personagens:
- Método HTTP: GET
- URL Completa: http://localhost:8080/personagens
- Descrição: Retorna todos os personagens cadastrados.
- Busca: JPQL ou Dynamic Finders
- DTO: PersonagemConsultaDto

<hr>

### 📋 Busca Personagens por ID:
- Método HTTP: GET
- URL Completa: http://localhost:8080/personagens/{id}
- Descrição: Retorna o personagem por id.
- Busca: JPQL ou Dynamic Finders
- DTO: PersonagemConsultaDto

<hr>

### 🆕 Cadastro de Personagens:
- Método HTTP: POST
- URL Completa: http://localhost:8080/personagens
- Validações Importantes:
    - <b>Nome</b>: Não pode ser nulo, conter espaços em branco ou vazio.
    - <b></b>
- Descrição: Cadastro de um novo personagem, retorne o personagem recém cadastrado com ID gerado.
- Busca: JPQL ou Dynamic Finders
- DTO: PersonagemCriacaoDto e PersonagemConsultaDto

<hr>

### 📋 Busca por Codinome dos Personagens:
- Método HTTP: GET
- URL Completa: http://localhost:8080/personagens/codinome?termo={termo}
- Descrição: Retorna os personagens por parte do nome sem case sensitive via RequestParam.
- Busca: JPQL ou Dynamic Finders
- DTO: PersonagemConsultaDto

<hr>

### 📋 Busca por Data Nascimento dos Personagens:
- Método HTTP: GET
- URL Completa: http://localhost:8080/personagens/nascidos-apos?data={data}
- Descrição: Retorna os personagens por data nascimento após a data informada via RequestParam.
- Busca: JPQL ou Dynamic Finders
- DTO: PersonagemConsultaDto

<hr>

### 📋 Busca o top 3 personagens por poder:
- Método HTTP: GET
- URL Completa: http://localhost:8080/personagens/top-3
- Descrição: Retorna o top 3 personagens mais poderosos.
- Busca: JPQL ou Dynamic Finders
- DTO: PersonagemConsultaDto

<hr>

### 📋 Busca o personagem com o menor poder:
- Método HTTP: GET
- URL Completa: http://localhost:8080/personagens/menor-poder
- Descrição: Retorna o personagem com o menor poder.
- Busca: JPQL
- DTO: PersonagemConsultaDto# Implementando-CI-CD
