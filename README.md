#spring-boot-test

## Execução
- Apenas testes unitários ``mvn clean install``  
- Apenas testes com integração ``mvn clean install -P integration-tests``  
 
## Visualizar relatório de cobertura
- Cobertura dos testes Unitário: Abrir index.html localizado no ``target/site/jacoco/index.html``
- Cobertura dos testes de Integração: Abrir index.html localizado no ``target/site/jacoco-it/index.html``
