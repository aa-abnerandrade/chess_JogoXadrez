# chess_JogoXadrez

Jogo de xadrez implementado em **Java 21** com interface de linha de comando, modelando um sistema de partida de xadrez com peças, tabuleiro e validações de movimentos, incluindo xeque, xeque-mate e promoção de peões.
> OBS: O jogo inicia uma nova partida a cada execução, sem persistência de estado.

---

## 📌 Stack
- Java 21
- Maven
- Lombok 1.18.32

---

## ⚙️ Running

A aplicação utiliza as configurações em `pom.xml`.

### A) Executando Localmente com IntelliJ
#### 1. Após clonar, utilize o IntelliJ para abrir o projeto e configure a versão do Java:
- Em *File* > *Project Structure* > selecione Java 21 > *Apply* > *OK*
#### 2. Na classe Application
- Clique no botão de reprodução ao lado da declaração da classe *Application* > *Run*

### B) Executando Via Maven
#### 1. Compile e execute:
```bash
mvn clean compile exec:java -Dexec.mainClass="Application"
```

---

## 🎮 Como Jogar

O jogo roda no terminal, imprimindo o tabuleiro e solicitando movimentos.

- **Movimentos:** Insira no formato origem-destino (ex: e2 e4). Use notação algébrica (a-h para colunas, 1-8 para linhas).
- **Peças:** Pawn (P), Rook (R), Knight (N), Bishop (B), Queen (Q), King (K).
- **Regras implementadas:** Movimentos válidos, captura, xeque, xeque-mate, roque, en passant, promoção de peão.
- **Comandos especiais:** Digite "resign" para desistir.

#### Exemplo de jogo:
```
Tabuleiro inicial:
8 r n b q k b n r
7 p p p p p p p p
6 . . . . . . . .
5 . . . . . . . .
4 . . . . . . . .
3 . . . . . . . .
2 P P P P P P P P
1 R N B Q K B N R
  a b c d e f g h

Turno das brancas. Digite origem e destino (ex: e2 e4):
e2 e4
```

---

## Observações importantes
- **Estado do jogo:** Não há persistência; cada execução inicia uma nova partida.
- **Validações:** Movimentos inválidos lançam exceções personalizadas (`ChessException`).
- **Dependências:** Usa Lombok para reduzir boilerplate em classes como `Position` e `ChessPosition`.

## Autoria
__Abner Andrade__
<div style="display: flex;">
    <a href = "https://www.linkedin.com/in/abnerandrade/"><img src="https://img.icons8.com/color/64/null/linkedin-2--v1.png" target="_blank"></a>
    <a href = "https://api.whatsapp.com/send?phone=5521973257039&text=Oi,%20Abner.%20Curti%20teu%20GitHub.%20%20Vamos%20trabalhar%20juntos?"><img src="https://img.icons8.com/color/64/null/whatsapp--v1.png" target="_blank"></a>
    <a href = "mailto:aa.abnerandrade@outlook.com"><img src="https://img.icons8.com/fluency/64/null/microsoft-outlook-2019.png" target="_blank"></a>
</div>
