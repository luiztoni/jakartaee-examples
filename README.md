# Jakarta EE - Examples


 📖 Dependências de build:

 * Distro OpenJDK 17 (sugestão: Eclipse Temurin) 
 * Maven 3.5+ /Build

## Informações
Esse repositório contém uma coletânea de projetos Jakarta EE compativeis com a especificação 10, o projeto 'servlet' é feito usando o container Eclipse Jetty 
e todos os demais usam IBM OpenLiberty.

## Executando os projetos

 * Jetty:
```sh
mvn jetty:run
```
 * OpenLiberty:
```sh
mvn liberty:run
```

Observação: Para executar o projeto 'messaging' o 'messaging-broker' precisa esta rodando.


#### License Apache License 2.0 Copyright (c) 2023 Luiz Toni
