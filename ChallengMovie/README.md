# ChallengMovie

* Projeto desenvolvido a fim de submissão para o teste de Desenvolvimento Mobile, o mesmo foi escrito com a linguagem Kotlin pela IDE Android Studio 
* Conforme solicitado foi realizado a comunicação com a api TMDB https://www.themoviedb.org/,
* Para fazer a comunicação com a api e necessario enviar a cada requissição uma aappkey o qual foi configurada no site da empresa e esta armazenada no arquivo AppConstants,

* para realizar as requisições Rest foi utilizado a lib Retrofi, 
* para facilitar as requisições e a conversão de objeto para Json e vice versa, foi implementado uma interface de resposta generica para que dessa forma a classe WebCleint precisa
informar o objeto a ser retornado para que assim o retrofit converter faça a conversao do objeto.*  

* Para comunicação e criação do banco de dados foi utilizado o Realm algumas outras libs foram adicionadas ao projeto como Picasso (download das imagens), 
* para o App funcionar offline a lista esta sendo salva no app, para futuras consultas depois, lembrando que ao menos um a vez o app precisa se conectar para realizar o download da lista
* Para realizar os testes da aplicaçao foi utilizado o teste robo do Firebase Testlab  