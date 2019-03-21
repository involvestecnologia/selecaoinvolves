
## Olá dev := Candidato{}.

No time do GOA temos diversos desafios arquiteturais, nossa principal missão é trabalhar em cima dos obstáculos que reduzem a nossa capacidade de **escalabilidade**,portanto todas as soluções desenvolvidas no nosso time são voltadas para alta **disponibilidade** e alta **performance**, logo nos preocupamos muito com **custo**, escalabilidade, **qualidade** e performance.
Nosso contexto também tem grande foco em **microsserviços** e consequentemente nós sempre procuramos utilizar as melhores práticas em relação ao assunto, assim como as melhores práticas de infraestrutura de microsserviços.

## O desafio:

#### O desafio que queremos te propor envolve você desenvolver uma aplicação **multi tenant** para processar um arquivo csv de pontos de venda, persistir em um banco de dados(de sua escolha) e disponibilizar alguns endpoints para que esses dados sejam consultados.

**1** - O arquivo a ser enviado para a sua API é o de [pdvs.csv](data/pdvs.csv) disponível neste repositório na pasta **data**, e nós utilizaremos o seguinte comando para importa-lo:

``` 
curl -X POST \
  https://aplicacao/caminho \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data' \
  -F data=@pdv_list.csv
  -F tenant=<some-tenant-id>
``` 

**2** - Durante a importação algumas validações devem ser feitas:
   - se o CEP contém o número correto de caracteres, caso o CEP    não contenha o número de caracteres deverá ser salvo o CEP    no formato `00000000`
   - Se o nome da Cidade for vazio, adicionar "`NOME DESCONHECIDO`" ao campo nome da cidade

   - Se o nome do PDV for vazio, adicionar  "`NOME DESCONHECIDO`" ao campo nome do PDV


**2** - Você deverá disponibilizar um endpoint para consultas de pontos de venda, este endpoint tem o requisito mínimo de aceitar os filtros de tenant,cidade, nome do pdv e cep.

**3** - O máximo de memória que a sua aplicação deverá utilizar é 400 Mb de ram.

#### Dicas

- A principal linguagem na Involves é Java, mas nós não temos problemas com outras linguagens, principalmente Go, então se você desejar fazer em outra linguagem pode fazer.

- Nós gostamos de automação, então Docker, scripts e Makefiles são diferenciais, em resumo, facilidade para rodar o seu projeto é um requisito.

- A nomenclatura dos endpoints fica ao seu critério, porém é de suma importância que o seu teste possua a documentação dos endpoints, recomendamos um README bem detalhado.

- Você terá 10 dias para realizar esse teste.

- Ao finalizar o teste, disponibilize o código e nos envie o link, pode ser no github, gitlab ou bitbucket, isso também fica ao seu critério.

- Lembre-se de garantir que teremos acesso ao seu repositório.

- A memória da JVM não é apenas o HeapSize 
  - http://trustmeiamadeveloper.com/2016/03/18/where-is-my-memory-java/


#### Boa sorte
