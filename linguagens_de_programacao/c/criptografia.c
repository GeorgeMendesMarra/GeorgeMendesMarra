/* ============================================================================
 * CRIPTOGRAFIA EM C - EXEMPLOS DO MAIS SIMPLES AO MAIS COMPLEXO
 * ============================================================================
 *
 * Este arquivo junta todos os exemplos apresentados na conversa, organizados
 * como se cada tecnica fosse uma "classe": um bloco de funcoes relacionadas,
 * cada uma cifrando e decifrando (ou assinando/verificando) da sua forma.
 *
 * PARTE 1 (sem bibliotecas externas): cifras classicas / didaticas
 *   1. Cifra de Cesar
 *   2. Cifra de Vigenere
 *   3. Cifra XOR
 *
 * PARTE 2 (usando OpenSSL): criptografia real, usada na pratica
 *   4. Bytes aleatorios seguros
 *   5. Hash SHA-256
 *   6. HMAC (hash com chave)
 *   7. PBKDF2 (derivar chave a partir de senha)
 *   8. AES-256-CBC (cifra simetrica)
 *   9. AES-256-GCM (cifra simetrica + autenticacao)
 *  10. RSA (cifra assimetrica com par de chaves)
 *  11. Assinatura digital RSA
 *
 * COMO COMPILAR (precisa da lib OpenSSL -> pacote libssl-dev):
 *   gcc criptografia_completo.c -o criptografia_completo -lcrypto
 *
 * COMO EXECUTAR:
 *   ./criptografia_completo
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/rsa.h>
#include <openssl/pem.h>

/* ============================================================================
 * FUNCAO AUXILIAR GERAL: imprime um vetor de bytes em hexadecimal.
 * Usada por varios exemplos abaixo para mostrar dados "binarios" na tela,
 * ja que nem todo byte cifrado corresponde a um caractere imprimivel.
 * ============================================================================
 */
void imprimir_hex(const unsigned char *dados, int tamanho) {
    for (int i = 0; i < tamanho; i++) {
        printf("%02x", dados[i]); // %02x = 2 digitos hexadecimais, com zero a esquerda
    }
    printf("\n");
}

/* ============================================================================
 * PARTE 1.1 - CIFRA DE CESAR
 * Ideia: desloca cada letra um numero fixo de posicoes no alfabeto.
 * Ex.: com deslocamento 3, 'A' vira 'D', 'B' vira 'E', etc.
 * Fraqueza: so existem 25 chaves possiveis -> facil de quebrar por forca bruta.
 * ============================================================================
 */

// Cifra o texto "no lugar" (modifica o proprio vetor de caracteres)
void cesar_cifrar(char *texto, int deslocamento) {
    for (int i = 0; texto[i] != '\0'; i++) {
        char c = texto[i];

        if (isupper((unsigned char)c)) {
            // 'A' = 65 na tabela ASCII. Subtraimos 'A' para trabalhar na
            // faixa 0-25, aplicamos o deslocamento com modulo 26 (para "dar
            // a volta" no alfabeto) e somamos 'A' de volta no final.
            texto[i] = (c - 'A' + deslocamento) % 26 + 'A';
        }
        else if (islower((unsigned char)c)) {
            texto[i] = (c - 'a' + deslocamento) % 26 + 'a';
        }
        // espacos, numeros e pontuacao permanecem inalterados
    }
}

// Decifrar e o mesmo algoritmo com o deslocamento invertido.
// "26 - (deslocamento % 26)" garante um deslocamento positivo equivalente
// ao negativo, evitando numeros negativos no modulo em C.
void cesar_decifrar(char *texto, int deslocamento) {
    cesar_cifrar(texto, 26 - (deslocamento % 26));
}

void demo_cesar(void) {
    printf("\n=== 1. Cifra de Cesar ===\n");

    char mensagem[100];
    strcpy(mensagem, "Ola Mundo");
    int chave = 3; // deslocamento usado como "chave" da cifra

    printf("Original:  %s\n", mensagem);

    cesar_cifrar(mensagem, chave);
    printf("Cifrado:   %s\n", mensagem);

    cesar_decifrar(mensagem, chave);
    printf("Decifrado: %s\n", mensagem);
}

/* ============================================================================
 * PARTE 1.2 - CIFRA DE VIGENERE
 * Ideia: em vez de um deslocamento fixo (como no Cesar), usa uma
 * palavra-chave que se repete ao longo do texto. Cada letra da chave define
 * um deslocamento diferente para a letra correspondente do texto.
 * Fraqueza: com chave curta e texto longo, e quebravel por analise
 * estatistica (metodo de Kasiski).
 * ============================================================================
 */

void vigenere_cifrar(char *texto, const char *chave) {
    int tam_chave = strlen(chave);
    int j = 0; // indice que percorre a chave (so avanca ao processar uma letra)

    for (int i = 0; texto[i] != '\0'; i++) {
        char c = texto[i];

        if (isalpha((unsigned char)c)) {
            // pega a letra da chave correspondente e calcula seu deslocamento.
            // toupper() garante que a chave funcione independente de
            // estar em maiusculas ou minusculas
            int deslocamento = toupper((unsigned char)chave[j % tam_chave]) - 'A';

            if (isupper((unsigned char)c)) {
                texto[i] = (c - 'A' + deslocamento) % 26 + 'A';
            } else {
                texto[i] = (c - 'a' + deslocamento) % 26 + 'a';
            }

            j++; // so avanca a chave quando de fato processamos uma letra
        }
        // espacos e pontuacao passam direto e nao consomem posicao da chave
    }
}

void vigenere_decifrar(char *texto, const char *chave) {
    int tam_chave = strlen(chave);
    int j = 0;

    for (int i = 0; texto[i] != '\0'; i++) {
        char c = texto[i];

        if (isalpha((unsigned char)c)) {
            int deslocamento = toupper((unsigned char)chave[j % tam_chave]) - 'A';

            if (isupper((unsigned char)c)) {
                // +26 antes do modulo evita resultado negativo em C
                texto[i] = (c - 'A' - deslocamento + 26) % 26 + 'A';
            } else {
                texto[i] = (c - 'a' - deslocamento + 26) % 26 + 'a';
            }

            j++;
        }
    }
}

void demo_vigenere(void) {
    printf("\n=== 2. Cifra de Vigenere ===\n");

    char mensagem[100];
    strcpy(mensagem, "Ataque ao amanhecer");
    const char *chave = "CHAVE";

    printf("Original:  %s\n", mensagem);

    vigenere_cifrar(mensagem, chave);
    printf("Cifrado:   %s\n", mensagem);

    vigenere_decifrar(mensagem, chave);
    printf("Decifrado: %s\n", mensagem);
}

/* ============================================================================
 * PARTE 1.3 - CIFRA XOR
 * Ideia: usa a operacao bit a bit XOR (^). Propriedade chave:
 * (A XOR chave) XOR chave = A. Ou seja, cifrar e decifrar usam a MESMA
 * funcao. Funciona em qualquer byte, nao apenas letras.
 * Fraqueza: se a chave for menor que o texto e reutilizada (como aqui),
 * padroes se repetem e a cifra pode ser quebrada. So e realmente segura se
 * a chave for tao longa quanto o texto e usada uma unica vez.
 * ============================================================================
 */

// Cifra e decifra: aplica XOR byte a byte com a chave, repetindo-a
// ciclicamente (assim como no Vigenere)
void xor_processar(unsigned char *dados, int tam_dados,
                    const unsigned char *chave, int tam_chave) {
    for (int i = 0; i < tam_dados; i++) {
        dados[i] = dados[i] ^ chave[i % tam_chave];
    }
}

void demo_xor(void) {
    printf("\n=== 3. Cifra XOR ===\n");

    unsigned char mensagem[100];
    strcpy((char*)mensagem, "Mensagem secreta");
    int tam = strlen((char*)mensagem);

    unsigned char chave[] = "chaveXYZ";
    int tam_chave = strlen((char*)chave);

    printf("Original:      %s\n", mensagem);

    // ---------- CIFRAR ----------
    xor_processar(mensagem, tam, chave, tam_chave);
    printf("Cifrado (hex): ");
    imprimir_hex(mensagem, tam);

    // ---------- DECIFRAR ----------
    // e a MESMA funcao aplicada de novo com a mesma chave
    xor_processar(mensagem, tam, chave, tam_chave);
    printf("Decifrado:     %s\n", mensagem);
}

/* ============================================================================
 * PARTE 2.1 - BYTES ALEATORIOS SEGUROS
 * Base de praticamente tudo em criptografia real: gerar numeros que nao
 * podem ser previstos (diferente de rand(), que NAO e seguro para isso).
 * ============================================================================
 */

void demo_bytes_aleatorios(void) {
    printf("\n=== 4. Bytes aleatorios seguros ===\n");

    unsigned char buffer[16];

    // RAND_bytes gera bytes criptograficamente seguros usando o gerador
    // de numeros aleatorios do sistema operacional
    if (RAND_bytes(buffer, sizeof(buffer)) != 1) {
        fprintf(stderr, "Erro ao gerar bytes aleatorios\n");
        return;
    }

    printf("Bytes aleatorios: ");
    imprimir_hex(buffer, sizeof(buffer));
}

/* ============================================================================
 * PARTE 2.2 - HASH SHA-256
 * Transforma qualquer dado em um "resumo" de tamanho fixo. E uma via: nao
 * da para reverter o hash e obter o dado original. Serve para verificar
 * integridade (o dado mudou ou nao).
 * ============================================================================
 */

void demo_sha256(void) {
    printf("\n=== 5. Hash SHA-256 ===\n");

    const char *mensagem = "Ola, mundo!";

    // Buffer que vai receber o hash. EVP_MAX_MD_SIZE garante espaco
    // suficiente para qualquer algoritmo de hash suportado pela OpenSSL
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;

    // 1. Cria o "contexto" que guarda o estado do calculo do hash
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();

    // 2. Inicializa dizendo qual algoritmo usar (SHA-256)
    EVP_DigestInit_ex(ctx, EVP_sha256(), NULL);

    // 3. Alimenta o hash com os dados (pode ser chamado varias vezes,
    //    util para processar arquivos grandes em pedacos)
    EVP_DigestUpdate(ctx, mensagem, strlen(mensagem));

    // 4. Finaliza o calculo e pega o resultado
    EVP_DigestFinal_ex(ctx, hash, &hash_len);

    // 5. Libera o contexto (evita vazamento de memoria)
    EVP_MD_CTX_free(ctx);

    printf("Mensagem: %s\n", mensagem);
    printf("SHA-256:  ");
    imprimir_hex(hash, hash_len);
}

/* ============================================================================
 * PARTE 2.3 - HMAC (hash com chave)
 * Um hash comum so garante que o dado nao mudou. O HMAC usa uma chave
 * secreta, garantindo tambem que quem gerou o hash conhecia a chave --
 * util para verificar que uma mensagem e autentica (nao foi forjada).
 * ============================================================================
 */

void demo_hmac(void) {
    printf("\n=== 6. HMAC-SHA256 ===\n");

    unsigned char chave[32];
    RAND_bytes(chave, sizeof(chave)); // chave secreta compartilhada

    const char *mensagem = "transferir 100 reais";

    unsigned char resultado[EVP_MAX_MD_SIZE];
    unsigned int resultado_len;

    // HMAC() calcula tudo de uma vez: recebe o algoritmo de hash usado por
    // baixo (SHA-256), a chave e os dados a autenticar
    HMAC(EVP_sha256(),
         chave, sizeof(chave),
         (unsigned char*)mensagem, strlen(mensagem),
         resultado, &resultado_len);

    printf("Mensagem:    %s\n", mensagem);
    printf("HMAC-SHA256: ");
    imprimir_hex(resultado, resultado_len);

    // Na pratica: quem recebe a mensagem + o HMAC recalcula o HMAC com a
    // mesma chave e compara o resultado. Se bater, a mensagem e autentica.
}

/* ============================================================================
 * PARTE 2.4 - PBKDF2 (derivar chave a partir de senha)
 * Senhas digitadas por humanos sao fracas como chave direta. O PBKDF2
 * transforma uma senha em uma chave forte, aplicando o hash muitas vezes
 * de proposito (isso dificulta ataques de forca bruta).
 * ============================================================================
 */

void demo_pbkdf2(void) {
    printf("\n=== 7. PBKDF2 (senha -> chave) ===\n");

    const char *senha = "minhaSenh4Fraca";

    // "salt": valor aleatorio unico, guardado junto com a chave derivada.
    // Impede que a mesma senha sempre gere a mesma chave.
    unsigned char salt[16];
    RAND_bytes(salt, sizeof(salt));

    unsigned char chave_derivada[32]; // vai virar, por exemplo, uma chave AES-256

    // Parametros: senha, salt, numero de iteracoes (quanto mais, mais lento
    // e mais seguro contra forca bruta), algoritmo de hash, tamanho da saida
    PKCS5_PBKDF2_HMAC(senha, strlen(senha),
                       salt, sizeof(salt),
                       100000, // iteracoes (valor recomendado atualmente)
                       EVP_sha256(),
                       sizeof(chave_derivada), chave_derivada);

    printf("Senha:          %s\n", senha);
    printf("Salt:           ");
    imprimir_hex(salt, sizeof(salt));
    printf("Chave derivada: ");
    imprimir_hex(chave_derivada, sizeof(chave_derivada));

    // Importante: o salt precisa ser guardado (nao e secreto), pois sera
    // necessario para derivar a mesma chave de novo no futuro.
}

/* ============================================================================
 * PARTE 2.5 - AES-256-CBC (cifra simetrica)
 * Cifra e decifra dados usando a MESMA chave dos dois lados.
 * ============================================================================
 */

void demo_aes_cbc(void) {
    printf("\n=== 8. AES-256-CBC ===\n");

    unsigned char chave[32]; // 32 bytes = AES-256
    unsigned char iv[16];    // vetor de inicializacao, 16 bytes para AES
    RAND_bytes(chave, sizeof(chave));
    RAND_bytes(iv, sizeof(iv)); // o IV deve ser diferente a cada cifragem

    unsigned char texto[] = "Mensagem secreta";
    unsigned char cifrado[128];
    unsigned char decifrado[128];
    int len, cifrado_len, decifrado_len;

    // ---------- CIFRAR ----------
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();

    // Define o algoritmo (AES-256-CBC), a chave e o IV
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, chave, iv);

    // Processa os dados (pode ser chamado varias vezes para dados grandes)
    EVP_EncryptUpdate(ctx, cifrado, &len, texto, strlen((char*)texto));
    cifrado_len = len;

    // Finaliza (aplica o padding necessario no ultimo bloco)
    EVP_EncryptFinal_ex(ctx, cifrado + len, &len);
    cifrado_len += len;

    EVP_CIPHER_CTX_free(ctx);

    printf("Original:      %s\n", texto);
    printf("Cifrado (hex): ");
    imprimir_hex(cifrado, cifrado_len);

    // ---------- DECIFRAR ----------
    ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, chave, iv);
    EVP_DecryptUpdate(ctx, decifrado, &len, cifrado, cifrado_len);
    decifrado_len = len;
    EVP_DecryptFinal_ex(ctx, decifrado + len, &len);
    decifrado_len += len;
    EVP_CIPHER_CTX_free(ctx);

    decifrado[decifrado_len] = '\0'; // transforma o resultado em string valida
    printf("Decifrado:     %s\n", decifrado);
}

/* ============================================================================
 * PARTE 2.6 - AES-256-GCM (cifra + autenticacao, o padrao moderno)
 * O CBC so cifra -- nao detecta se alguem alterou o dado cifrado. O GCM
 * cifra e gera uma "tag" que comprova que os dados nao foram alterados.
 * E o modo recomendado atualmente para a maioria dos casos.
 * ============================================================================
 */

void demo_aes_gcm(void) {
    printf("\n=== 9. AES-256-GCM ===\n");

    unsigned char chave[32];
    unsigned char iv[12]; // GCM usa IV de 12 bytes por convencao
    RAND_bytes(chave, sizeof(chave));
    RAND_bytes(iv, sizeof(iv));

    unsigned char texto[] = "Dados confidenciais";
    unsigned char cifrado[128];
    unsigned char tag[16]; // tag de autenticacao, gerada durante a cifragem
    int len, cifrado_len;

    // ---------- CIFRAR ----------
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL);

    // Define o tamanho do IV (opcional aqui, pois 12 bytes ja e o padrao)
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, sizeof(iv), NULL);

    // Agora sim define a chave e o IV
    EVP_EncryptInit_ex(ctx, NULL, NULL, chave, iv);

    EVP_EncryptUpdate(ctx, cifrado, &len, texto, strlen((char*)texto));
    cifrado_len = len;

    EVP_EncryptFinal_ex(ctx, cifrado + len, &len);
    cifrado_len += len;

    // Pega a tag de autenticacao gerada durante o processo de cifragem
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, sizeof(tag), tag);
    EVP_CIPHER_CTX_free(ctx);

    printf("Original: %s\n", texto);
    printf("Cifrado:  ");
    imprimir_hex(cifrado, cifrado_len);
    printf("Tag:      ");
    imprimir_hex(tag, sizeof(tag));

    // ---------- DECIFRAR + VERIFICAR ----------
    unsigned char decifrado[128];
    int decifrado_len;

    ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), NULL, NULL, NULL);
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, sizeof(iv), NULL);
    EVP_DecryptInit_ex(ctx, NULL, NULL, chave, iv);

    EVP_DecryptUpdate(ctx, decifrado, &len, cifrado, cifrado_len);
    decifrado_len = len;

    // Informa qual tag deve ser conferida na finalizacao
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, sizeof(tag), tag);

    // Se a tag nao bater (dado foi alterado), a funcao retorna <= 0
    int ok = EVP_DecryptFinal_ex(ctx, decifrado + len, &len);
    EVP_CIPHER_CTX_free(ctx);

    if (ok > 0) {
        decifrado_len += len;
        decifrado[decifrado_len] = '\0';
        printf("Decifrado (autenticado com sucesso): %s\n", decifrado);
    } else {
        printf("ERRO: dado corrompido ou adulterado!\n");
    }
}

/* ============================================================================
 * PARTE 2.7 - RSA (cifra assimetrica com par de chaves)
 * Usa DUAS chaves diferentes: uma publica (cifra, pode ser compartilhada) e
 * uma privada (decifra, deve ficar em segredo).
 * ============================================================================
 */

void demo_rsa_cifrar(void) {
    printf("\n=== 10. RSA (cifra assimetrica) ===\n");

    // ---------- GERAR O PAR DE CHAVES ----------
    EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_RSA, NULL);
    EVP_PKEY_keygen_init(pctx);
    EVP_PKEY_CTX_set_rsa_keygen_bits(pctx, 2048); // tamanho recomendado atual

    EVP_PKEY *chave = NULL;
    EVP_PKEY_keygen(pctx, &chave); // gera as duas chaves (publica e privada) juntas
    EVP_PKEY_CTX_free(pctx);

    // ---------- CIFRAR COM A CHAVE PUBLICA ----------
    const char *mensagem = "segredo";
    unsigned char cifrado[256];
    size_t cifrado_len = sizeof(cifrado);

    EVP_PKEY_CTX *ectx = EVP_PKEY_CTX_new(chave, NULL);
    EVP_PKEY_encrypt_init(ectx);
    EVP_PKEY_CTX_set_rsa_padding(ectx, RSA_PKCS1_OAEP_PADDING); // padding seguro

    EVP_PKEY_encrypt(ectx, cifrado, &cifrado_len,
                      (unsigned char*)mensagem, strlen(mensagem));
    EVP_PKEY_CTX_free(ectx);

    printf("Mensagem: %s\n", mensagem);
    printf("Cifrado:  ");
    imprimir_hex(cifrado, cifrado_len);

    // ---------- DECIFRAR COM A CHAVE PRIVADA ----------
    unsigned char decifrado[256];
    size_t decifrado_len = sizeof(decifrado);

    EVP_PKEY_CTX *dctx = EVP_PKEY_CTX_new(chave, NULL);
    EVP_PKEY_decrypt_init(dctx);
    EVP_PKEY_CTX_set_rsa_padding(dctx, RSA_PKCS1_OAEP_PADDING);

    EVP_PKEY_decrypt(dctx, decifrado, &decifrado_len, cifrado, cifrado_len);
    EVP_PKEY_CTX_free(dctx);

    decifrado[decifrado_len] = '\0';
    printf("Decifrado: %s\n", decifrado);

    // Nota: RSA so cifra blocos pequenos (poucas centenas de bytes). Na
    // pratica, RSA cifra uma chave AES, e o AES cifra os dados de fato --
    // e a chamada "criptografia hibrida" (assim funciona o HTTPS).

    EVP_PKEY_free(chave);
}

/* ============================================================================
 * PARTE 2.8 - ASSINATURA DIGITAL RSA
 * Diferente de cifrar, aqui o objetivo e provar autoria: qualquer um pode
 * conferir a assinatura com a chave publica, mas so quem tem a chave
 * privada consegue cria-la.
 * ============================================================================
 */

void demo_rsa_assinatura(void) {
    printf("\n=== 11. Assinatura digital RSA ===\n");

    // Gera par de chaves RSA (mesmo processo do exemplo anterior)
    EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_RSA, NULL);
    EVP_PKEY_keygen_init(pctx);
    EVP_PKEY_CTX_set_rsa_keygen_bits(pctx, 2048);
    EVP_PKEY *chave = NULL;
    EVP_PKEY_keygen(pctx, &chave);
    EVP_PKEY_CTX_free(pctx);

    const char *mensagem = "Autorizo o pagamento de 500 reais";
    unsigned char assinatura[256];
    size_t assinatura_len;

    // ---------- ASSINAR (com a chave privada) ----------
    EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
    EVP_DigestSignInit(mdctx, NULL, EVP_sha256(), NULL, chave);
    EVP_DigestSignUpdate(mdctx, mensagem, strlen(mensagem));

    // Primeira chamada com NULL apenas descobre o tamanho da assinatura
    EVP_DigestSignFinal(mdctx, NULL, &assinatura_len);
    EVP_DigestSignFinal(mdctx, assinatura, &assinatura_len);
    EVP_MD_CTX_free(mdctx);

    printf("Mensagem:   %s\n", mensagem);
    printf("Assinatura: ");
    imprimir_hex(assinatura, assinatura_len);

    // ---------- VERIFICAR (com a chave publica) ----------
    // Aqui usamos o mesmo EVP_PKEY porque ele contem as duas chaves, mas em
    // um sistema real quem verifica teria apenas a parte publica.
    mdctx = EVP_MD_CTX_new();
    EVP_DigestVerifyInit(mdctx, NULL, EVP_sha256(), NULL, chave);
    EVP_DigestVerifyUpdate(mdctx, mensagem, strlen(mensagem));

    int valido = EVP_DigestVerifyFinal(mdctx, assinatura, assinatura_len);
    EVP_MD_CTX_free(mdctx);

    if (valido == 1) {
        printf("Resultado: assinatura VALIDA -- a mensagem e autentica.\n");
    } else {
        printf("Resultado: assinatura INVALIDA -- mensagem alterada ou chave errada.\n");
    }

    EVP_PKEY_free(chave);
}

/* ============================================================================
 * MENU PRINCIPAL
 * Permite escolher qual exemplo rodar, ou rodar todos em sequencia.
 * ============================================================================
 */

void mostrar_menu(void) {
    printf("\n===================================================\n");
    printf(" CRIPTOGRAFIA EM C - MENU DE EXEMPLOS\n");
    printf("===================================================\n");
    printf(" Cifras classicas (sem bibliotecas externas):\n");
    printf("  1. Cifra de Cesar\n");
    printf("  2. Cifra de Vigenere\n");
    printf("  3. Cifra XOR\n");
    printf(" Criptografia real (com OpenSSL):\n");
    printf("  4. Bytes aleatorios seguros\n");
    printf("  5. Hash SHA-256\n");
    printf("  6. HMAC-SHA256\n");
    printf("  7. PBKDF2 (senha -> chave)\n");
    printf("  8. AES-256-CBC\n");
    printf("  9. AES-256-GCM\n");
    printf(" 10. RSA (cifrar/decifrar)\n");
    printf(" 11. Assinatura digital RSA\n");
    printf("  0. Rodar TODOS os exemplos em sequencia\n");
    printf(" -1. Sair\n");
    printf("===================================================\n");
    printf("Escolha uma opcao: ");
}

void rodar_todos(void) {
    demo_cesar();
    demo_vigenere();
    demo_xor();
    demo_bytes_aleatorios();
    demo_sha256();
    demo_hmac();
    demo_pbkdf2();
    demo_aes_cbc();
    demo_aes_gcm();
    demo_rsa_cifrar();
    demo_rsa_assinatura();
}

int main(void) {
    int opcao;

    do {
        mostrar_menu();

        // le a opcao do usuario; se a entrada for invalida, encerra o loop
        if (scanf("%d", &opcao) != 1) {
            break;
        }

        switch (opcao) {
            case 1:  demo_cesar(); break;
            case 2:  demo_vigenere(); break;
            case 3:  demo_xor(); break;
            case 4:  demo_bytes_aleatorios(); break;
            case 5:  demo_sha256(); break;
            case 6:  demo_hmac(); break;
            case 7:  demo_pbkdf2(); break;
            case 8:  demo_aes_cbc(); break;
            case 9:  demo_aes_gcm(); break;
            case 10: demo_rsa_cifrar(); break;
            case 11: demo_rsa_assinatura(); break;
            case 0:  rodar_todos(); break;
            case -1: printf("Encerrando...\n"); break;
            default: printf("Opcao invalida!\n");
        }

    } while (opcao != -1);

    return 0;
}
