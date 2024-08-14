# Desafio

O que foi proposto:
Fazer um scrap para pegar informações de algum site que contesse informações sobre um jogador de cs go(Fallen). A informação especifica era sobre flashbangs

## Como resolvi

O único site que possuía essas informações específicas sobre flashbangs era o hltv.org. Decidi então fazer o scraping dele. 
Embora seja relativamente fácil realizar o scraping, o site apresentou um desafio: ele recusa qualquer tentativa de conexão
automatizada, reconhecendo-a como um bot, o que resulta em um erro 403 (Forbidden). Em alguns sites, isso pode ser resolvido
adicionando os headers de solicitação adequados, permitindo que a conexão seja estabelecida normalmente. No entanto, essa abordagem
não funcionou com os métodos de scraping que conhecia (Jsoup e OkHttp). A única opção restante foi usar o Selenium, pois ele simula
um acesso mais semelhante ao humano do que um bot, e com isso consegui acessar e obter os dados desejados.
Outro ponto importante a ressaltar é que esse processo também poderia ser feito por baixo dos panos com selenium, porém outra vez
o site não ajudou, por que a cada acesso feito ele pede para que o usuario aceite os cookies do site para acessa-lo, então ao tentar
fazer o scrap sem abrir o nagevador dava erro de timeout por que não era possivel aceitar os cookies sem a parte grafica.

Dividi o codigo em quatro classes:
-   **Player:** Classe onde eu crio o objeto jogador para manipular os dados mais facilmente.
    
-   **PlayerMethods:** Nesta classe, criei três métodos para realizar as consultas nos dados obtidos através do scraping: `searchPlayerByName`, `getTop10ByRounds` e `getTop10BySuccess`. Os nomes são bastante explicativos: o primeiro procura o jogador pelo nome e devolve todos os
      status dele, e os outros dois ordenam os dados e mostram rankings. Um rank é baseado na quantidade de flashes gastas por round durante o
      período de tempo selecionado, e o outro é baseado na taxa de sucesso das flashes realizadas pelo jogador.
    
-   **SeleniumScrap:** Nessa classe é feito o scraping e também crio a lista de players com os dados obtidos.
    
-   **Main:** Este é o código que faz as consultas. Usei um switch case bem simples para realizar as consultas nos dados do scraping que obtive.
-  Observação: Ao falar de dados, vale lembrar que o que foi pedido era obter a quantidade de flashes feitas por um jogador específico neste ano. Entretanto,o jogador específico não aparece no site se o intervalo de datas for de 3 meses, 6 meses ou apenas este ano (2024). Em nenhum desses casos, não aparece nenhuma informação dele e nem de nenhum jogador de seu time, os dados so aparecem se o filtro de tempo estive em 12 meses ou de 2023 para baixo.


## Resultado apos pesquisar Fallen depois do scrap feito:

![Captura de tela 2024-08-14 052525](https://github.com/user-attachments/assets/675e3af8-3a23-498d-a0aa-e289dd6b85b4)
