# Histórico da Criptografia e Algoritmos Simétricos e Assimétricos

---

## 1. Introdução

A criptografia é a ciência de proteger informações por meio de sua transformação em formatos ilegíveis para terceiros não autorizados. Seu desenvolvimento acompanha a própria história da comunicação humana, evoluindo desde técnicas manuais simples na antiguidade até algoritmos matemáticos complexos executados por computadores modernos.

A classificação fundamental dos algoritmos criptográficos divide-se em duas grandes categorias:

| Tipo | Característica Principal | Exemplos |
|------|-------------------------|----------|
| **Criptografia Simétrica** | Utiliza uma única chave tanto para cifrar quanto para decifrar a mensagem | AES, DES, RC4, Blowfish |
| **Criptografia Assimétrica** | Utiliza um par de chaves matematicamente relacionadas: uma pública e uma privada | RSA, Diffie-Hellman, ECC |

---

## 2. História da Criptografia: Das Cifras Manuais aos Algoritmos Modernos

### 2.1. A Antiguidade: Os Primeiros Algoritmos

O primeiro uso prático da criptografia remonta ao século VI a.C. .

- **Cifra de Atbash (c. 600 a.C.):** Descrita no Antigo Testamento, foi a primeira cifra conhecida. Trata-se de uma cifra de substituição simples onde a primeira letra do alfabeto é substituída pela última (A ↔ Z, B ↔ Y, etc.). Era utilizada para ocultar nomes e localizações geográficas .

- **Cifra de César (c. 100 a.C.):** Desenvolvida por Júlio César para correspondência secreta, esta cifra desloca cada letra do texto original por um número fixo de posições no alfabeto (por exemplo, 3 posições: A → D, B → E). É considerada uma cifra de substituição monoalfabética .

- **Cifra de Cifra (Scytale - c. 500 a.C.):** Utilizada na Grécia Antiga, foi um dos primeiros dispositivos criptográficos. Consistia em um bastão de madeira de diâmetro predeterminado onde uma tira de pergaminho era enrolada; a mensagem era escrita longitudinalmente e, ao desenrolar a tira, o texto aparecia embaralhado. Trata-se de uma cifra de transposição .

### 2.2. Idade Média e Renascimento: Avanços nas Cifras

- **Cifra de Vigenère (século XVI):** Desenvolvida na França, foi um marco revolucionário. Ao contrário das cifras anteriores que usavam um deslocamento fixo, a cifra de Vigenère utiliza uma palavra-chave e uma tabela (o quadrado de Vigenère) para aplicar deslocamentos variáveis ao longo do texto. Com isso, um mesmo caractere no texto original podia ser cifrado de formas diferentes, tornando-a resistente à análise de frequência. Por séculos, foi considerada "indecifrável" (le chiffre indéchiffrable) .

### 2.3. Século XX: A Era das Máquinas e a Criptografia Moderna

- **Máquina Enigma (década de 1920-1940):** Utilizada pela Alemanha na Segunda Guerra Mundial, a Enigma era uma máquina eletromecânica que implementava uma cifra de substituição polialfabética complexa. Seu funcionamento baseava-se em rotores que alteravam o alfabeto de substituição a cada tecla pressionada. A quebra do código Enigma por Alan Turing e sua equipe em Bletchley Park é considerada um dos feitos mais importantes da história da criptoanálise .

### 2.4. A Era Digital: Algoritmos de Computador

Com o advento dos computadores, a criptografia passou a ser implementada em software e hardware, surgindo os algoritmos modernos que utilizam operações matemáticas complexas.

- **Anos 1970:** O governo dos EUA publicou o **Data Encryption Standard (DES)**, o primeiro algoritmo de criptografia simétrica amplamente adotado como padrão governamental .

- **1976:** Whitfield Diffie e Martin Hellman publicaram o artigo **"New Directions in Cryptography"** , introduzindo o conceito de criptografia de chave pública (assimétrica) e o algoritmo Diffie-Hellman para troca segura de chaves .

- **1977:** Ron Rivest, Adi Shamir e Leonard Adleman (MIT) desenvolveram o algoritmo **RSA**, que se tornaria o padrão de fato para criptografia assimétrica .

- **1991:** Philip Zimmerman criou o **Pretty Good Privacy (PGP)** , um programa que utilizava criptografia assimétrica para proteger e-mails e arquivos. A publicação de seu código-fonte, apesar da oposição do governo dos EUA (que equiparava criptografia a armas), foi um marco na democratização da criptografia para o público em geral .

- **2001:** O **Advanced Encryption Standard (AES)** foi oficialmente adotado pelo NIST (National Institute of Standards and Technology) para substituir o DES, tornando-se o algoritmo simétrico mais utilizado no mundo .

- **2022:** O Ethereum concluiu a migração para Proof-of-Stake, reduzindo o consumo energético em 99,95% — um exemplo de aplicação criptográfica em blockchain.

---

## 3. Algoritmos de Criptografia Simétrica

A criptografia simétrica utiliza a mesma chave para cifrar e decifrar a mensagem. É a forma mais antiga de criptografia e ainda a mais utilizada para grandes volumes de dados devido à sua eficiência computacional .

### 3.1. Classificação: Cifras de Fluxo (Stream) vs. Cifras de Bloco (Block)

| Tipo | Funcionamento | Exemplos |
|------|---------------|----------|
| **Cifra de Fluxo (Stream)** | Cifra cada bit ou byte individualmente em tempo real, geralmente mais rápida e adequada para hardware com recursos limitados | RC4, ChaCha20 |
| **Cifra de Bloco (Block)** | Processa blocos de tamanho fixo (ex: 64 ou 128 bits) por vez, aplicando múltiplas rodadas de transformações | AES, DES, Blowfish, Twofish |

### 3.2. Principais Algoritmos Simétricos

#### 3.2.1. DES (Data Encryption Standard)

| Característica | Especificação |
|----------------|---------------|
| **Desenvolvedor** | IBM (com modificações do governo dos EUA)  |
| **Ano** | 1977  |
| **Tamanho do Bloco** | 64 bits  |
| **Tamanho da Chave** | 56 bits (mais 8 bits de paridade)  |
| **Status Atual** | Quebrado; não é mais considerado seguro  |

DES foi o primeiro padrão de criptografia simétrica amplamente adotado pelo governo dos EUA e pela indústria. No entanto, com o aumento do poder computacional, tornou-se vulnerável a ataques de força bruta. Em 1999, um DES foi quebrado em menos de 24 horas .

#### 3.2.2. Triple-DES (3DES / TDEA)

| Característica | Especificação |
|----------------|---------------|
| **Desenvolvedor** | Baseado no DES |
| **Ano** | 1998  |
| **Tamanho do Bloco** | 64 bits  |
| **Tamanho da Chave** | 168 bits (112 ou 168 bits efetivos)  |
| **Status Atual** | Ainda em uso (especialmente no setor financeiro), mas não recomendado para novas aplicações  |

O Triple-DES aplica o algoritmo DES três vezes com duas ou três chaves diferentes. Apesar de ainda oferecer resistência contra força bruta, seu pequeno tamanho de bloco (64 bits) o torna suscetível ao ataque "Sweet 32", que pode quebrar a chave após a criptografia de mais de 2³² blocos .

#### 3.2.3. AES (Advanced Encryption Standard)

| Característica | Especificação |
|----------------|---------------|
| **Desenvolvedor** | Joan Daemen e Vincent Rijmen (algoritmo Rijndael)  |
| **Ano** | 2001 (adotado como padrão)  |
| **Tamanho do Bloco** | 128 bits  |
| **Tamanhos de Chave** | 128, 192 ou 256 bits  |
| **Status Atual** | Padrão global; considerado altamente seguro  |

O AES foi selecionado após uma competição pública organizada pelo NIST. É o algoritmo simétrico de escolha para a maioria das aplicações atuais. Com chave de 256 bits, é considerado forte o suficiente para proteger dados militares de nível "Top Secret". Mesmo com o advento da computação quântica, o AES-256 manterá segurança equivalente a uma chave de 128 bits devido ao algoritmo de Grover .

#### 3.2.4. Família RC (Ron's Code / Rivest Cipher)

| Algoritmo | Características | Status |
|-----------|-----------------|--------|
| **RC2** | Cifra de bloco de 64 bits, chave de até 128 bits; desenvolvido em 1987 | Não considerado seguro  |
| **RC4** | Cifra de fluxo amplamente utilizada em SSL/TLS e WEP; desenvolvido em 1987 | Quebrado; não seguro  |
| **RC5** | Cifra de bloco com parâmetros variáveis (bloco: 32/64/128 bits; chave: até 2.040 bits; rodadas: até 255) | Patenteado; uso limitado  |
| **RC6** | Evolução do RC5 com bloco fixo de 128 bits; competidor do AES | Patenteado; uso limitado  |

#### 3.2.5. Blowfish e Twofish

| Algoritmo | Características | Status |
|-----------|-----------------|--------|
| **Blowfish** | Cifra de bloco de 64 bits; desenvolvido por Bruce Schneier em 1993; substituição para o DES | Amplamente utilizado, mas limitado pelo tamanho de bloco  |
| **Twofish** | Cifra de bloco de 128 bits; chaves de 128, 192 ou 256 bits; desenvolvido por Bruce Schneier; finalista da competição AES | Considerado seguro; utilizado principalmente em hardware  |

#### 3.2.6. Algoritmos Nacionais e Regionais

| Algoritmo | Origem | Características |
|-----------|--------|-----------------|
| **SEED** | Coreia do Sul | Bloco de 128 bits, chave de 128 bits; padrão nacional  |
| **Camellia** | Japão (NTT, Mitsubishi) | Bloco de 128 bits, chave de 128/192/256 bits; aprovado para IPSec e OpenPGP  |
| **ARIA** | Coreia do Sul | Bloco de 128 bits, chave de 128/192/256 bits; descrito em RFC 5794  |
| **SM1 / SM4** | China | Padrões nacionais chineses  |
| **Magma / Kuznyechik** | Rússia | Padrões GOST  |
| **CLEFIA** | Japão (Sony) | Bloco de 128 bits, chave de 192/256 bits; desenvolvido em 2007  |

### 3.3. Comparativo de Algoritmos Simétricos

| Algoritmo | Ano | Bloco (bits) | Chave (bits) | Segurança Atual |
|-----------|-----|--------------|--------------|-----------------|
| DES | 1977 | 64 | 56 | Quebrado |
| 3DES | 1998 | 64 | 112/168 | Vulnerável (Sweet 32) |
| RC4 | 1987 | Fluxo | Variável | Quebrado |
| Blowfish | 1993 | 64 | 32-448 | Limitado (bloco) |
| AES | 2001 | 128 | 128/192/256 | Altamente seguro |
| Twofish | 1998 | 128 | 128-256 | Seguro |
| Camellia | 2000 | 128 | 128-256 | Seguro |

---

## 4. Algoritmos de Criptografia Assimétrica

A criptografia assimétrica, também conhecida como criptografia de chave pública, utiliza um par de chaves matematicamente relacionadas: uma chave pública, que pode ser amplamente distribuída, e uma chave privada, mantida em segredo pelo proprietário .

### 4.1. Princípio Fundamental

Os algoritmos assimétricos baseiam-se em problemas matemáticos considerados "difíceis" de resolver em tempo computacional viável:
- **Fatoração de números inteiros grandes** (RSA)
- **Logaritmo discreto** (Diffie-Hellman, DSA, ElGamal)
- **Curvas elípticas** (ECC, ECDSA)

### 4.2. Principais Algoritmos Assimétricos

#### 4.2.1. Diffie-Hellman (DH)

| Característica | Especificação |
|----------------|---------------|
| **Desenvolvedores** | Whitfield Diffie e Martin Hellman  |
| **Ano** | 1976  |
| **Função Principal** | Troca segura de chaves (não cifra mensagens diretamente)  |
| **Base Matemática** | Logaritmo discreto  |
| **Status** | Amplamente utilizado em protocolos como IPSec, SSL/TLS  |

O Diffie-Hellman foi o primeiro algoritmo de chave pública publicado. Permite que duas partes que nunca se encontraram estabeleçam um segredo compartilhado por meio de um canal inseguro. Esse segredo é então utilizado como chave para um algoritmo simétrico .

**Funcionamento básico :**
1. As duas partes concordam publicamente com dois números: um primo grande (p) e um gerador (g)
2. Cada parte gera um número secreto privado (a e b)
3. Cada parte calcula um valor público: gᵃ mod p e gᵇ mod p
4. As partes trocam os valores públicos
5. Cada parte calcula o segredo compartilhado: (gᵇ)ᵃ mod p = (gᵃ)ᵇ mod p

**Vulnerabilidade:** Não autentica as partes envolvidas, sendo vulnerável a ataques de homem-no-meio (Man-in-the-Middle) se usado isoladamente .

#### 4.2.2. RSA (Rivest-Shamir-Adleman)

| Característica | Especificação |
|----------------|---------------|
| **Desenvolvedores** | Ron Rivest, Adi Shamir, Leonard Adleman (MIT)  |
| **Ano** | 1977 (público); Clifford Cocks (GCHQ) havia desenvolvido versão equivalente em 1973  |
| **Funções** | Cifragem de mensagens, assinatura digital  |
| **Base Matemática** | Fatoração de números inteiros grandes  |
| **Tamanhos de Chave** | 2.048 a 4.096 bits (recomendado)  |
| **Status** | Padrão global; mais utilizado no mundo  |

O RSA é considerado o algoritmo assimétrico mais importante e amplamente utilizado. Sua segurança baseia-se na dificuldade de fatorar o produto de dois números primos grandes (n = p × q). A chave pública é (n, e) e a chave privada é (n, d) .

**Geração de chaves RSA :**
1. Escolher dois números primos grandes aleatórios (p e q)
2. Calcular n = p × q (módulo)
3. Calcular λ(n) = lcm(p-1, q-1) (função totiente de Carmichael)
4. Escolher e (expoente público) tal que 1 < e < λ(n) e gcd(e, λ(n)) = 1 (comum: e = 65537)
5. Calcular d (expoente privado) tal que d × e ≡ 1 (mod λ(n))

**Uso típico:** Devido à sua lentidão (10 a 1.000 vezes mais lento que algoritmos simétricos), o RSA geralmente é utilizado para cifrar uma chave de sessão simétrica (como AES), que então cifra o conteúdo completo .

#### 4.2.3. DSA (Digital Signature Algorithm)

| Característica | Especificação |
|----------------|---------------|
| **Desenvolvedor** | NSA (National Security Agency)  |
| **Ano** | 1991  |
| **Função** | Assinatura digital (não cifragem)  |
| **Base Matemática** | Logaritmo discreto  |
| **Status** | Padrão do governo dos EUA; mais rápido para assinatura, mais lento para verificação  |

O DSA foi desenvolvido como alternativa ao RSA para assinaturas digitais. Embora ofereça segurança comparável, tem sido menos adotado em aplicações comerciais .

#### 4.2.4. ECC e ECDSA (Elliptic Curve Cryptography)

| Característica | Especificação |
|----------------|---------------|
| **Desenvolvedores** | Neal Koblitz e Victor S. Miller (1985)  |
| **Adoção Governamental** | NSA "Suite B" (2005)  |
| **Base Matemática** | Curvas elípticas sobre corpos finitos  |
| **Vantagem** | Mesma segurança com chaves significativamente menores  |

A criptografia de curvas elípticas representa a evolução mais recente dos algoritmos assimétricos. Sua principal vantagem é oferecer níveis de segurança equivalentes ao RSA com chaves muito menores e menor custo computacional, o que é especialmente importante para dispositivos móveis e sistemas com recursos limitados .

**Comparativo de tamanhos de chave (segurança equivalente):**

| Segurança (bits) | RSA/DSA (bits) | ECC (bits) |
|------------------|----------------|------------|
| 80 | 1.024 | 160-223 |
| 112 | 2.048 | 224-255 |
| 128 | 3.072 | 256-383 |
| 192 | 7.680 | 384-511 |
| 256 | 15.360 | 512+ |

**ECDSA (Elliptic Curve Digital Signature Algorithm):** É a aplicação do conceito de curvas elípticas para assinaturas digitais, baseada no DSA mas utilizando curvas elípticas como fundamento matemático .

### 4.3. Comparativo de Algoritmos Assimétricos

| Algoritmo | Ano | Base Matemática | Função Principal | Tamanho Chave (bits) | Velocidade |
|-----------|-----|-----------------|------------------|---------------------|------------|
| Diffie-Hellman | 1976 | Logaritmo discreto | Troca de chaves | 2.048-4.096 | Moderada |
| RSA | 1977 | Fatoração | Cifragem/Assinatura | 2.048-4.096 | Lenta |
| DSA | 1991 | Logaritmo discreto | Assinatura | 2.048-3.072 | Rápida (assinatura) |
| ECC | 1985 | Curvas elípticas | Cifragem/Assinatura | 256-512 | Rápida |

---

## 5. Aplicações Práticas dos Algoritmos Criptográficos

### 5.1. Protocolos de Segurança de Rede (TLS/SSL)

Mais de 95% do tráfego da internet é atualmente criptografado, principalmente por meio do protocolo TLS (Transport Layer Security) . O TLS utiliza uma **abordagem híbrida** que combina os dois tipos de algoritmos:

1. **Fase de Handshake (assimétrica):** Utiliza RSA ou ECDH para autenticar o servidor e estabelecer uma chave de sessão compartilhada de forma segura 
2. **Fase de Transferência (simétrica):** Utiliza AES-GCM ou ChaCha20-Poly1305 para cifrar os dados de forma eficiente 

**Cipher Suites comuns :**
- `TLS_AES_128_GCM_SHA256`: AES-128 em modo GCM com SHA256
- `TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256`: ECDH em curvas elípticas com ChaCha20

### 5.2. Proteção de Dados em Repouso

Ferramentas de criptografia de disco e arquivo utilizam predominantemente algoritmos simétricos:

| Ferramenta | Algoritmos | Ambiente |
|------------|------------|----------|
| BitLocker | AES-128/256 | Windows  |
| LUKS | AES, Serpent, Twofish | Linux  |
| FileVault | AES-XTS | macOS  |
| VeraCrypt | AES, Serpent, Twofish | Multiplataforma  |

### 5.3. E-mail e Mensagens

| Protocolo/Ferramenta | Algoritmos Utilizados |
|---------------------|----------------------|
| PGP/GPG | RSA, DSA, ElGamal (assimétrico) + AES, CAST (simétrico)  |
| SMTPS/IMAPS | TLS com AES, ChaCha20  |

---

## 6. Desafios e Futuro da Criptografia

### 6.1. Computação Quântica

O desenvolvimento de computadores quânticos em larga escala representa uma ameaça significativa para a criptografia atual :

| Algoritmo | Impacto da Computação Quântica |
|-----------|-------------------------------|
| RSA | Quebrado pelo algoritmo de Shor |
| ECC | Quebrado pelo algoritmo de Shor |
| Diffie-Hellman | Quebrado pelo algoritmo de Shor |
| AES-128 | Efetividade reduzida a 64 bits (Grover) |
| AES-256 | Efetividade reduzida a 128 bits (Grover) - ainda seguro |

### 6.2. Criptografia Pós-QUântica (PQC)

O NIST tem conduzido uma competição para selecionar algoritmos resistentes a computadores quânticos, com finalistas incluindo:
- **CRYSTALS-Kyber** (KEM - encapsulamento de chaves)
- **CRYSTALS-Dilithium** (assinatura digital)
- **FALCON** (assinatura digital)
- **SPHINCS+** (assinatura digital baseada em hash)

### 6.3. Crypto-Agility

A história demonstra que algoritmos considerados seguros eventualmente se tornam obsoletos (DES, RC4, MD5, SHA1). O conceito de **crypto-agility** refere-se à capacidade de sistemas substituírem algoritmos criptográficos de forma rápida e simples por meio de atualizações de software, sem necessidade de substituição de hardware .

---

## 7. Conclusão

A criptografia evoluiu de cifras manuais simples da antiguidade para algoritmos matemáticos complexos que garantem a segurança de toda a infraestrutura digital moderna. Enquanto os algoritmos simétricos, como o AES, fornecem a base para cifragem eficiente de grandes volumes de dados, os algoritmos assimétricos, como o RSA e ECC, resolveram o problema fundamental da distribuição de chaves e viabilizaram o comércio eletrônico e as comunicações seguras na internet.

O futuro da criptografia está em constante desenvolvimento, com a computação quântica impulsionando a transição para novos algoritmos resistentes a essa tecnologia emergente. A lição histórica é clara: a segurança criptográfica não é um estado permanente, mas um processo contínuo de adaptação e evolução.

---

## Referências

1. Cryptomathic. (2020). *Symmetric Encryption Algorithms and Crypto-Agility* 
2. Wikipedia. (2001). *Asymmetric algorithm* 
3. Security Vision. (2025). *Application of symmetric and asymmetric encryption algorithms* 
4. Exabeam. (2019). *How RSA and the Diffie-Hellman Key Exchange Became the Most Popular Cryptosystems* 
5. Springer. (2012). *Summary of Symmetric Cryptographic Algorithms* 
6. University of Texas Wikis. (2025). *Cryptographic Protocols - Asymmetric Key Algorithms* 
7. ScienceDirect. (2003-2014). *Asymmetric Algorithm / Symmetric Key Cipher* 
8. Wikipedia. (2001). *RSA cryptosystem* 

---
