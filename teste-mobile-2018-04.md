# Seleção Desenvolvedor(a)

Olá, estamos muito felizes pelo seu interesse em fazer parte da nossa equipe de desenvolvimento. Gostaríamos de conhecer um pouco mais das suas habilidades como desenvolvedor(a) e, para isso, preparamos o teste descrito abaixo.

## Descrição

Aqui na Involves (se pronuncia Invôlves) nós gostamos muito de filmes e sentimos a necessidade de nos mantermos atualizados sobre os novos filmes. Entendemos que temos uma oportunidade de desenvolvimento e queremos trabalhar na primeira versão de um app para manter nossos usuários atualizados sobre os novos filmes. Para isso resolvemos usar a API do [TMDB](https://www.themoviedb.org/documentation/api?language=pt).

### Requisitos

A versão mínima do aplicativo deve ter:

- A lista dos próximos filmes a estrear, mostrando capa, título e data de lançamento.

- Ao selecionar um filme, detalhes do filme: Sinópse, gênero, descrição, capa e data de lançamento.

Após cumprir os [critérios de avaliação](#avalia%C3%A7%C3%A3o) para os itens anteriores, seu projeto pode evoluir com as seguintes funcionalidades:

- Cache para funcionamento offline.

- Opção para a atualizar a lista dos filmes.

- Pesquisar por filmes.

Utilize o endpoint de [upcoming movies](https://developers.themoviedb.org/3/movies/get-upcoming) para listar os filmes a estrear e o endpoint [search](https://developers.themoviedb.org/3/search/search-movies) para pesquisar.

Mantenha em mente que o seu aplicativo pode deslanchar mundialmente e você precisará implementar novas funcionalidades, seu código deverá estar preparado para tanto. 

A equipe trabalhando no projeto pode crescer (inclusive internacionalmente), então seu código deve ser legível e conciso.

Escolha uma plataforma aderente com a nossa realidade atual: Android SDK ou iOS SDK, ou seja, desenvolva em uma das plataformas "nativas".


## Avaliação

A nossa avaliação vai ser minusciosa em cima das tecnologias e decisões que você tomou. Para que seu projeto seja bem avaliado, você deverá cumprir com os requisitos apresentados a seguir. O não cumprimento destes requisitos prejudicará a avaliação do seu projeto.

Obrigatoriamente, **você deverá**:

- No README, incluir instruções para configurar e rodar o app.

- Incluir testes no seu projeto.

- Escrever um código claro e legível, com componentes de responsabilidade bem definida.

- Tratar adequadamente o ciclo de vida da aplicação, incluindo tratamento de rotação de tela, restaurar o aplicativo e outros pontos do ciclo de vida.

- Remover todo código não utilizado, comentado ou de funcionalidade incompleta.

Para as vagas de nível **Pleno** ou **Sênior**, você **deverá também**:

- Tratar adequadamente multithreading e a utilização de recursos.

- Tratar adequadamente erros ou falhas em tempo de execução.

- Utilizar técnicas modernas para networking e storage, se necessário com apoio de bibliotecas.

- Gerenciar o consumo de memória e impedir que _leaks_ ocorram.

- Criar uma interface simples e aderente aos padrões modernos da plataforma.

## Sobre a entrega

- Crie um repositório git público (GitHub, GitLab, Bitbucket, etc.). Caso não seja possível, entre em contato com a nossa equipe para negociar alternativas.


- Crie um branch para desenvolvimento e não _commit_ nada no master.

- Não faça fork deste projeto.

- Abrar um _pull-request_ com o código e envie o link para mobile@involves.com.br com o título **"[teste-{ano}] {nome_do_candidato}"**.
