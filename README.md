# Odonto Validation

Serviço de Validação de Consultas Odontológicas

### Link p/ apresentação da solução: 

## Integrantes do Grupo

### Kauã Almeida Silveira
- **Responsável por:** API em Java Spring Boot, integração com o banco de dados Oracle, e API Python com Roboflow para visão computacional.
### Rafael Vida
- **Responsável por:** DevOps no Azure e QA, garantindo o pipeline de integração contínua e a qualidade do software.
### Gustavo Maia
- **Responsável por:** Desenvolvimento do App em Kotlin para Android e site em C#.

## Descrição do Projeto

Projeto de validação de consultas odontológicas desenvolvido para a OdontoPrev. 
O objetivo principal deste projeto é combater sinistros e fraudes em consultas e procedimentos odontológicos, utilizando tecnologia de visão computacional no inicio e fim do procedimento, e validação de consultas por meio de um aplicativo mobile.

Obs: Vale ressaltar que o Aplicativo é direcionado aos dentistas e atendentes/recepcionistas.

GITHUB REPOSITÓRIO DO PROJETO JAVA MVC: https://github.com/ChallengeOdontoPrev/javaAdvancedMVC

GITHUB REPOSITÓRIOS DA SOLUÇÃO: https://github.com/orgs/ChallengeOdontoPrev/repositories

## Instruções para Rodar a Aplicação

## Pré-requisitos para rodar a API Spring Boot:
- Java 21 ou superior instalado (para a API Spring Boot)
- Maven instalado (para a API Spring Boot)
- As configurações do banco de dados Oracle estão no arquivo `application-dev.properties` na pasta `src/main/resources`,
  não é necessário instalar o banco de dados Oracle, pois a aplicação está utilizando um banco de dados disponibilizado
  em um servidor remoto da FIAP.

## Passo a Passo:

1. **Clone o repositório:**
   ```git clone https://github.com/ChallengeOdontoPrev/javaAdvancedMVC.git```
2. **Acesse a pasta do projeto no intellij:**
3. **Defina o JDK no Intellij para Run & build do projeto**
4. **Rode a aplicação Spring Boot, localizada na pasta raiz**
   ```ChallengeJavaApplication.java```

## Diagrama de Entidade-Relacionamento (DER)

![DER](./DER.png)
