package com.rafaeldeoliveira.quiz

data class Pergunta(
    val texto: String,
    val opcoes: List<String>,
    val respostaCorreta: Int // Índice da resposta correta
)

// Lista de perguntas
val perguntas = listOf(
    Pergunta(
        "Quando iniciou a Segunda Guerra Mundial",
        listOf("1935", "1940", "1939"),
        2
    ),
    Pergunta(
        "Qual o tanque de guerra mais famoso do conflito?",
        listOf("Tiger I", "M4 Sherman", "T34"),
        0
    ),
    Pergunta(
        "Qual o primeiro país invadido?",
        listOf("União Soviética", "Polônia", "México"),
        1
    ),
    Pergunta(
        "Quem foi o responsável pelo início da guerra?",
        listOf("Eu", "Adolf Hitler", "Winston Churchill", "Stalin"),
        1
    ),
    Pergunta(
        "Qual dos projetos é apenas uma teoria?",
        listOf("Tanques série E alemães", "Classe H de encouraçados alemães", "Die Glock"),
        2
    ),
    Pergunta(
        "O que impediu os alemães de construírem a bomba atômica primeiro?",
        listOf("Água pesada escassa", "Plutônio pouco abundante", "Falta de recursos financeiros"),
        0
    ),
    Pergunta(
        "Qual o blindado britânico construído para abater tanques Tiger?",
        listOf("M10 caça-tanques", "M3 Stuart", "Sherman Firefly"),
        2
    ),
    Pergunta(
        "Qual a maior canhão utilizado no conflito e que também é o maior da história?",
        listOf("Canhão utilizado na família Sherman", "Grande Gustav", "Big Bertha"),
        1
    ),
    Pergunta(
        "Qual foi o primeiro caça a jato operacional do conflito?",
        listOf("P-39", "Me-262 'Schwalbe'", "P-51 Mustang"),
        1
    ),
    Pergunta(
        "Qual foi o único país neutro no conflito?",
        listOf("Brasil", "Paraguai", "Suécia"),
        2
    ),
    Pergunta(
        "Nome das tropas alemãs que combateram na África: ",
        listOf("Afrika Korps", "FEB", "Smoking Snakes"),
        0
    ),
    Pergunta(
        "Qual o principal fuzil americano utilizado no conflito?",
        listOf("Kar-98k", "Mosing Nagant", "M1 Garand"),
        2
    ),
    Pergunta(
        "Qual a ordem mais famosa dada por Winston Churchill?",
        listOf("Senta a pua!", "Afundem o Bismarck", "Queima eles"),
        1
    ),
    Pergunta(
        "Qual o navio irmão do Bismarck?",
        listOf("Tirpitz", "Scharnhorst", "Gneisenau"),
        0
    ),
    Pergunta(
        "Quem foi o 'Inafundável Sam'?",
        listOf("Um gato", "Um marinheiro astuto", "Uma baleia"),
        0
    )
)

// Definir o número de perguntas que o sistema aleatório vai selecionar
val numeroDePerguntas = 5

// Embaralhar e selecionar apenas uma quantidade específica de perguntas
val perguntasEmbaralhadas = perguntas.shuffled().take(numeroDePerguntas)
