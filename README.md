# Objetivo

Este projeto foi concebido para...

# Demostrativo de estrutura de projeto

Para este projeto foi trabalhado conceitos de arquitetura de software para se usar como modelo. A estrutura de pastas está organizada de forma com que a navegação no projeto permanece nítida e suave.

Por exemplo, quando estamos trabalhando em um recurso, geralmente permanecemos no diretório em que precisamos trabalhar, sem a necessidade de alternar entre diretórios.

# Estrutura de Pastas
Árvore de arquivos :
```
├── data
│   ├── model
│   ├── network
│   └── service
|
├── shared
│   ├── views
│   │   └── ...
│   └── ...
|
├── ui
│   ├── login
│   └── profile
|
├── util
│   ├── ...
│   └── ...
				...
```
#### [**data**](https://github.com/jheimes-silveira/financial-institution-exemple-mvp/tree/master/app/src/main/java/br/com/jheimesilveira/instituicaofinanceira/data)
O pacote de dados contém todas as classes (e pacotes filho) diretamente relacionadas a qualquer tipo de gerenciamento de dados ou dados usado no aplicativo - seja classes e interfaces de rede, gerenciamento de preferências, classes de banco de dados, modelos de dados, modelo de solicitação e resposta de rede, ou qualquer outra coisa diretamente ligada aos dados do aplicativo.

#### **shared**
É o pacote para armazenar quaisquer classes que possam ser usadas nos diferentes recursos do pacote da interface do usuário. Por exemplo, temos classes gerais ErrorView e RefreshingView que são compartilhadas em várias telas diferentes, portanto, é natural colocar isso dentro de um pacote comum a toda a aplicação, se necessário, as classes no pacote comum também são agrupadas em pacotes filho para organização adicional

#### [**ui**](https://github.com/jheimes-silveira/financial-institution-exemple-mvp/tree/master/app/src/main/java/br/com/jheimesilveira/instituicaofinanceira/ui)
O pacote da 'ui' (interface do usuário) é responsável por manter todas as classes relacionadas aos componentes de interface do usuário do aplicativo. Dentro deste pacote, também temos pacotes filhos organizados por recurso. Isso torna super arrumado e extremamente fácil de navegar ao trabalhar com um recurso específico. Parece um pouco assim:

#### [**util**](https://github.com/jheimes-silveira/financial-institution-exemple-mvp/tree/master/app/src/main/java/br/com/jheimesilveira/instituicaofinanceira/util)
O pacote util é usado para conter qualquer tipo de classe Helper ou classe Utilitaria que podemos usar para coisas como criação de caixa de diálogo, criação de Snackbar, métricas de exibição, verificações de conectividade, guias personalizadas ou qualquer outra forma de tarefa que se enquadre em uma classe de utilitário.

# Arquitetura limpa em MVP

Esta amostra se baseia nos princípios da arquitetura Mvp. adicionando uma camada de domínio entre a camada de apresentação e os repositórios:
