# Padrões de Projeto — Categoria de Construção (Criacionais)

Trabalho da disciplina de **Padrões de Projeto** — Bacharelado em Engenharia de Software / UCSAL.

Este repositório explica e demonstra, em **Java**, os cinco padrões de projeto solicitados dentro da categoria de **construção**: **Builder**, **Factory Method**, **Abstract Factory**, **Prototype** e **Memento**. Cada padrão é ilustrado com um **mini-projeto que simula um problema do mundo real** (nada de `ClasseA`/`ClasseB`).

> **Nota de classificação (GoF):** na taxonomia clássica do *Gang of Four*, Builder, Factory Method, Abstract Factory e Prototype são padrões **criacionais** (de construção). O **Memento** é formalmente classificado como **comportamental**, mas foi incluído aqui na categoria de construção conforme o enunciado da atividade, por também tratar da **criação/captura de objetos que representam estado**.

---

## O que são padrões de construção?

Padrões de construção (criacionais) tratam da **forma como os objetos são criados**. Em vez de instanciar objetos diretamente com `new` espalhado pelo código, eles isolam e organizam essa criação, deixando o sistema mais flexível, desacoplado e fácil de manter. Cada padrão resolve um problema diferente de criação:

| Padrão | Problema que resolve |
|---|---|
| **Builder** | Construir um objeto complexo passo a passo, com muitos parâmetros opcionais. |
| **Factory Method** | Deixar uma subclasse decidir qual objeto concreto criar. |
| **Abstract Factory** | Criar famílias de objetos relacionados que devem ser usados juntos. |
| **Prototype** | Criar novos objetos clonando um já existente, em vez de construir do zero. |
| **Memento** | Capturar e restaurar o estado interno de um objeto sem violar seu encapsulamento. |

---

## Como compilar e executar

Requisito: **JDK 17+** (o projeto foi testado com JDK 21).

Na raiz do projeto:

```bash
# 1. Compilar todo o projeto para a pasta out/
javac -d out $(find src -name "*.java")

# 2. Executar a demonstração de cada padrão
java -cp out builder.Main
java -cp out factorymethod.Main
java -cp out abstractfactory.Main
java -cp out prototype.Main
java -cp out memento.Main
```

No Windows (PowerShell), o passo de compilação pode ser feito assim:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
```

---

## Estrutura do projeto

```
padroes-construcao/
├── README.md
├── .gitignore
└── src/
    ├── builder/           # Padrão Builder
    ├── factorymethod/     # Padrão Factory Method
    ├── abstractfactory/   # Padrão Abstract Factory
    ├── prototype/         # Padrão Prototype
    └── memento/           # Padrão Memento
```

Cada pacote tem uma classe `Main` própria, que executa a demonstração daquele padrão.

---

## 1. Builder

### Conceito

O **Builder** separa a construção de um objeto complexo da sua representação final, permitindo montá-lo **passo a passo**. É útil quando um objeto tem **muitos parâmetros** — vários deles opcionais — e usar um construtor único levaria ao problema do *"telescoping constructor"* (dezenas de construtores sobrecarregados, ou um construtor gigante em que não se sabe o que cada valor significa). Com o Builder, cada valor é definido por um método com nome descritivo e encadeável.

**Participantes:**
- **Produto** (`Computador`): o objeto complexo a ser criado — imutável.
- **Builder** (`Computador.Builder`): recebe os itens obrigatórios no construtor e expõe métodos encadeáveis para os opcionais; o método `construir()` valida e devolve o produto.

### Mini-projeto do mundo real: montador de computador em e-commerce

Uma loja online permite montar um PC escolhendo peças obrigatórias (processador, RAM, SSD) e adicionais opcionais (placa de vídeo, Wi-Fi, refrigeração líquida, garantia estendida). O **mesmo processo** gera desde um PC de escritório simples até um PC gamer completo:

```java
Computador gamer = new Computador.Builder("AMD Ryzen 7", 32, 1000)
        .comPlacaVideo("NVIDIA RTX 4070")
        .comRefrigeracaoLiquida()
        .comWifi()
        .comGarantia(3)
        .construir();
```

**Por que Builder aqui?** A leitura é autoexplicativa, os opcionais têm valores-padrão, e o objeto final é imutável e sempre válido (a validação acontece em `construir()`).

---

## 2. Factory Method

### Conceito

O **Factory Method** define uma interface (um método) para criar um objeto, mas **delega às subclasses** a decisão de qual classe concreta instanciar. Assim, o código que usa o objeto trabalha apenas com a **abstração** (a interface do produto) e não precisa conhecer as classes concretas nem usar `new` diretamente. Adicionar um novo tipo de produto é criar uma nova subclasse — sem alterar o código existente (princípio Aberto/Fechado).

**Participantes:**
- **Produto** (`Pagamento`): interface comum a todos os objetos criados.
- **Produtos Concretos** (`PagamentoPix`, `PagamentoCartaoCredito`, `PagamentoBoleto`).
- **Creator** (`Checkout`): declara o factory method abstrato `criarPagamento()` e contém a lógica comum.
- **Creators Concretos** (`CheckoutPix`, `CheckoutCartao`, `CheckoutBoleto`): cada um decide qual produto fabricar.

### Mini-projeto do mundo real: sistema de pagamentos

No checkout de uma loja, o cliente escolhe como pagar. Cada meio de pagamento tem sua própria regra (PIX confirma na hora, cartão aplica taxa, boleto tem vencimento), mas o **fluxo de finalizar a compra é idêntico**:

```java
Checkout comCartao = new CheckoutCartao();
comCartao.finalizarCompra(150.00); // cria o PagamentoCartaoCredito e o processa
```

**Por que Factory Method aqui?** O método `finalizarCompra()` na classe base não sabe (nem precisa saber) qual pagamento concreto está usando. Incluir um novo meio (ex.: PayPal) é só criar `PagamentoPayPal` + `CheckoutPayPal`.

---

## 3. Abstract Factory

### Conceito

O **Abstract Factory** fornece uma interface para criar **famílias de objetos relacionados** sem especificar suas classes concretas. Enquanto o Factory Method cria **um** produto, o Abstract Factory cria **vários produtos que devem ser compatíveis entre si**. Ao escolher uma fábrica concreta, o cliente garante que todos os objetos criados pertencem à mesma "família".

**Participantes:**
- **Produtos Abstratos** (`LampadaInteligente`, `FechaduraInteligente`, `Termostato`).
- **Produtos Concretos** (versões `SmartLife` e `CasaConnect` de cada dispositivo).
- **Fábrica Abstrata** (`KitCasaInteligenteFactory`): declara os métodos de criação de cada produto.
- **Fábricas Concretas** (`KitSmartLifeFactory`, `KitCasaConnectFactory`): cada uma produz uma família inteira.

### Mini-projeto do mundo real: automação residencial

Ao contratar um plano de casa inteligente, o cliente escolhe **um ecossistema**. A partir daí, lâmpada, fechadura e termostato precisam ser da mesma marca para conversarem entre si. Trocar de ecossistema é trocar **apenas a fábrica**:

```java
private static void instalarCasa(KitCasaInteligenteFactory fabrica) {
    fabrica.criarLampada().ligar();
    fabrica.criarFechadura().trancar();
    fabrica.criarTermostato().ajustarTemperatura(23);
}
// instalarCasa(new KitSmartLifeFactory());  ou  instalarCasa(new KitCasaConnectFactory());
```

**Por que Abstract Factory aqui?** Garante a **consistência da família**: é impossível instalar acidentalmente uma lâmpada SmartLife com uma fechadura CasaConnect. O método `instalarCasa` não conhece nenhuma marca — depende só da fábrica abstrata.

---

## 4. Prototype

### Conceito

O **Prototype** cria novos objetos **clonando** um objeto já existente (o protótipo), em vez de construí-lo do zero. É útil quando a criação de um objeto é **cara** (muitas etapas, muitos dados) e já existe um exemplar pronto que serve de base. A clonagem pode ser **rasa** (copia referências) ou **profunda** (*deep copy* — copia também os objetos internos); este projeto usa cópia profunda para que alterar o clone **não afete** o original.

**Participantes:**
- **Protótipo** (`Prototipo<T>`): interface que declara `clonar()`.
- **Protótipo Concreto** (`ContratoTrabalho`): implementa a clonagem, incluindo a cópia profunda da lista de cláusulas.

### Mini-projeto do mundo real: modelos de contrato em sistema jurídico

Um escritório de advocacia mantém um **modelo-base** de contrato de trabalho, com todas as cláusulas fixas já redigidas. Para cada novo funcionário, o modelo é **clonado** e só os dados variáveis são preenchidos — sem reescrever o contrato inteiro:

```java
ContratoTrabalho contratoMaria = modeloCLT.clonar();
contratoMaria.setContratado("Maria Oliveira");
contratoMaria.setSalario(4500.00);
contratoMaria.adicionarClausula("Cargo: Desenvolvedora Backend.");
```

**Por que Prototype aqui?** Evita reconstruir o modelo custoso a cada contrato. A execução comprova que a cópia é profunda: mesmo depois de personalizar vários clones, o **modelo-base permanece intacto**.

---

## 5. Memento

### Conceito

O **Memento** captura e externaliza o **estado interno** de um objeto para que ele possa ser **restaurado depois** — sem expor os detalhes internos desse objeto (respeitando o encapsulamento). É o padrão por trás de funções como **"desfazer" (Ctrl+Z)**.

**Participantes:**
- **Originador** (`EditorTexto`): o objeto cujo estado é salvo/restaurado; sabe criar e ler mementos.
- **Memento** (`SnapshotEditor`): guarda um retrato imutável do estado; só o originador acessa seu conteúdo.
- **Caretaker/Zelador** (`HistoricoEditor`): guarda os mementos (aqui em uma pilha) e os devolve, **sem** olhar dentro deles.

### Mini-projeto do mundo real: editor de texto com "desfazer"

Antes de cada alteração relevante, o editor salva um retrato do seu conteúdo. Ao desfazer, ele volta ao último retrato guardado:

```java
editor.digitar("Ola");
historico.guardar(editor.salvar());   // ponto de restauração
editor.digitar(", mundo");
// ...
editor.restaurar(historico.desfazer()); // volta ao estado anterior
```

**Por que Memento aqui?** O `HistoricoEditor` armazena o estado sem conhecer sua estrutura interna — ele só empilha e desempilha `SnapshotEditor`. O encapsulamento do editor fica preservado.

---

## Autoria

Trabalho acadêmico desenvolvido para a disciplina de Padrões de Projeto — UCSAL.
