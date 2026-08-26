/* ============================================================================
 * CRIPTOGRAFIA EM C++ - EXEMPLOS DO MAIS SIMPLES AO MAIS COMPLEXO
 * ============================================================================
 *
 * Mesmo conteudo da versao em C, reescrito em C++: cada tecnica vira uma
 * classe com metodos estaticos, usando std::string / std::vector em vez de
 * ponteiros e buffers manuais sempre que possivel.
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
 *   g++ -std=c++17 criptografia_completo.cpp -o criptografia_completo -lcrypto
 *
 * COMO EXECUTAR:
 *   ./criptografia_completo
 * ============================================================================
 */

#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
#include <vector>
#include <cctype>

#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/rsa.h>
#include <openssl/pem.h>

// Usamos um alias para deixar claro quando estamos lidando com dados
// "binarios" (bytes crus), em vez de texto legivel
using Bytes = std::vector<unsigned char>;

/* ============================================================================
 * FUNCAO AUXILIAR GERAL: converte um vetor de bytes para uma string
 * hexadecimal, para exibir na tela dados que nem sempre sao caracteres
 * imprimiveis (como texto cifrado).
 * ============================================================================
 */
std::string paraHex(const Bytes &dados) {
    std::ostringstream saida;
    saida << std::hex << std::setfill('0');

    for (unsigned char b : dados) {
        // std::setw(2) garante sempre 2 digitos por byte (ex.: "0a" e nao "a")
        saida << std::setw(2) << static_cast<int>(b);
    }
    return saida.str();
}

// Sobrecarga de conveniencia para converter string comum em Bytes
Bytes paraBytes(const std::string &texto) {
    return Bytes(texto.begin(), texto.end());
}

/* ============================================================================
 * PARTE 1.1 - CIFRA DE CESAR
 * Ideia: desloca cada letra um numero fixo de posicoes no alfabeto.
 * Fraqueza: so existem 25 chaves possiveis -> facil de quebrar por forca bruta.
 * ============================================================================
 */
class CifraCesar {
public:
    // Cifra recebendo o texto por valor (uma copia), assim a funcao pode
    // devolver o resultado sem alterar a string original do chamador
    static std::string cifrar(std::string texto, int deslocamento) {
        for (char &c : texto) {
            if (std::isupper(static_cast<unsigned char>(c))) {
                // 'A' = 65 na tabela ASCII. Normalizamos para 0-25, aplicamos
                // o deslocamento com modulo 26 e voltamos para a faixa de letras
                c = static_cast<char>((c - 'A' + deslocamento) % 26 + 'A');
            }
            else if (std::islower(static_cast<unsigned char>(c))) {
                c = static_cast<char>((c - 'a' + deslocamento) % 26 + 'a');
            }
            // espacos, numeros e pontuacao permanecem inalterados
        }
        return texto;
    }

    // Decifrar e cifrar com o deslocamento invertido
    static std::string decifrar(const std::string &texto, int deslocamento) {
        return cifrar(texto, 26 - (deslocamento % 26));
    }
};

void demoCesar() {
    std::cout << "\n=== 1. Cifra de Cesar ===\n";

    std::string mensagem = "Ola Mundo";
    int chave = 3;

    std::cout << "Original:  " << mensagem << "\n";

    std::string cifrado = CifraCesar::cifrar(mensagem, chave);
    std::cout << "Cifrado:   " << cifrado << "\n";

    std::string decifrado = CifraCesar::decifrar(cifrado, chave);
    std::cout << "Decifrado: " << decifrado << "\n";
}

/* ============================================================================
 * PARTE 1.2 - CIFRA DE VIGENERE
 * Ideia: como o Cesar, mas o deslocamento muda a cada letra, seguindo uma
 * palavra-chave que se repete ao longo do texto.
 * Fraqueza: com chave curta e texto longo, e quebravel por analise
 * estatistica (metodo de Kasiski).
 * ============================================================================
 */
class CifraVigenere {
public:
    static std::string cifrar(std::string texto, const std::string &chave) {
        size_t j = 0; // indice que percorre a chave (so avanca em letras)

        for (char &c : texto) {
            if (std::isalpha(static_cast<unsigned char>(c))) {
                // pega a letra da chave correspondente e calcula o deslocamento
                int deslocamento = std::toupper(
                    static_cast<unsigned char>(chave[j % chave.size()])) - 'A';

                if (std::isupper(static_cast<unsigned char>(c))) {
                    c = static_cast<char>((c - 'A' + deslocamento) % 26 + 'A');
                } else {
                    c = static_cast<char>((c - 'a' + deslocamento) % 26 + 'a');
                }
                j++; // so avanca a chave ao processar uma letra de verdade
            }
        }
        return texto;
    }

    static std::string decifrar(std::string texto, const std::string &chave) {
        size_t j = 0;

        for (char &c : texto) {
            if (std::isalpha(static_cast<unsigned char>(c))) {
                int deslocamento = std::toupper(
                    static_cast<unsigned char>(chave[j % chave.size()])) - 'A';

                if (std::isupper(static_cast<unsigned char>(c))) {
                    // +26 evita numero negativo antes do modulo
                    c = static_cast<char>((c - 'A' - deslocamento + 26) % 26 + 'A');
                } else {
                    c = static_cast<char>((c - 'a' - deslocamento + 26) % 26 + 'a');
                }
                j++;
            }
        }
        return texto;
    }
};

void demoVigenere() {
    std::cout << "\n=== 2. Cifra de Vigenere ===\n";

    std::string mensagem = "Ataque ao amanhecer";
    std::string chave = "CHAVE";

    std::cout << "Original:  " << mensagem << "\n";

    std::string cifrado = CifraVigenere::cifrar(mensagem, chave);
    std::cout << "Cifrado:   " << cifrado << "\n";

    std::string decifrado = CifraVigenere::decifrar(cifrado, chave);
    std::cout << "Decifrado: " << decifrado << "\n";
}

/* ============================================================================
 * PARTE 1.3 - CIFRA XOR
 * Ideia: usa a operacao bit a bit XOR (^). Propriedade chave:
 * (A XOR chave) XOR chave = A -- cifrar e decifrar usam o MESMO metodo.
 * Fraqueza: com chave curta reutilizada, padroes se repetem e a cifra pode
 * ser quebrada. So e realmente segura com chave do tamanho do texto, usada
 * uma unica vez.
 * ============================================================================
 */
class CifraXor {
public:
    // Cifra e decifra: aplica XOR byte a byte com a chave, repetindo-a
    // ciclicamente. Funciona com qualquer sequencia de bytes.
    static Bytes processar(const Bytes &dados, const Bytes &chave) {
        Bytes resultado(dados.size());

        for (size_t i = 0; i < dados.size(); i++) {
            resultado[i] = dados[i] ^ chave[i % chave.size()];
        }
        return resultado;
    }
};

void demoXor() {
    std::cout << "\n=== 3. Cifra XOR ===\n";

    std::string mensagemTexto = "Mensagem secreta";
    Bytes mensagem = paraBytes(mensagemTexto);
    Bytes chave = paraBytes("chaveXYZ");

    std::cout << "Original:      " << mensagemTexto << "\n";

    // ---------- CIFRAR ----------
    Bytes cifrado = CifraXor::processar(mensagem, chave);
    std::cout << "Cifrado (hex): " << paraHex(cifrado) << "\n";

    // ---------- DECIFRAR ----------
    // e o MESMO metodo aplicado de novo com a mesma chave
    Bytes decifrado = CifraXor::processar(cifrado, chave);
    std::string decifradoTexto(decifrado.begin(), decifrado.end());
    std::cout << "Decifrado:     " << decifradoTexto << "\n";
}

/* ============================================================================
 * PARTE 2.1 - BYTES ALEATORIOS SEGUROS
 * Base de praticamente tudo em criptografia real: gerar numeros que nao
 * podem ser previstos (diferente de rand(), que NAO e seguro para isso).
 * ============================================================================
 */
class Aleatorio {
public:
    static Bytes gerarBytes(size_t quantidade) {
        Bytes buffer(quantidade);

        // RAND_bytes gera bytes criptograficamente seguros usando o gerador
        // de numeros aleatorios do sistema operacional
        if (RAND_bytes(buffer.data(), static_cast<int>(buffer.size())) != 1) {
            throw std::runtime_error("Erro ao gerar bytes aleatorios");
        }
        return buffer;
    }
};

void demoBytesAleatorios() {
    std::cout << "\n=== 4. Bytes aleatorios seguros ===\n";

    Bytes buffer = Aleatorio::gerarBytes(16);
    std::cout << "Bytes aleatorios: " << paraHex(buffer) << "\n";
}

/* ============================================================================
 * PARTE 2.2 - HASH SHA-256
 * Transforma qualquer dado em um "resumo" de tamanho fixo. E uma via: nao
 * da para reverter o hash e obter o dado original.
 * ============================================================================
 */
class Sha256 {
public:
    static Bytes calcular(const std::string &mensagem) {
        Bytes hash(EVP_MAX_MD_SIZE); // espaco suficiente para qualquer hash
        unsigned int hashLen;

        // 1. Cria o "contexto" que guarda o estado do calculo do hash
        EVP_MD_CTX *ctx = EVP_MD_CTX_new();

        // 2. Inicializa dizendo qual algoritmo usar (SHA-256)
        EVP_DigestInit_ex(ctx, EVP_sha256(), nullptr);

        // 3. Alimenta o hash com os dados (pode ser chamado varias vezes,
        //    util para processar arquivos grandes em pedacos)
        EVP_DigestUpdate(ctx, mensagem.data(), mensagem.size());

        // 4. Finaliza o calculo e pega o resultado
        EVP_DigestFinal_ex(ctx, hash.data(), &hashLen);

        // 5. Libera o contexto (evita vazamento de memoria)
        EVP_MD_CTX_free(ctx);

        hash.resize(hashLen); // corta o vetor para o tamanho real do hash
        return hash;
    }
};

void demoSha256() {
    std::cout << "\n=== 5. Hash SHA-256 ===\n";

    std::string mensagem = "Ola, mundo!";
    Bytes hash = Sha256::calcular(mensagem);

    std::cout << "Mensagem: " << mensagem << "\n";
    std::cout << "SHA-256:  " << paraHex(hash) << "\n";
}

/* ============================================================================
 * PARTE 2.3 - HMAC (hash com chave)
 * Um hash comum so garante que o dado nao mudou. O HMAC usa uma chave
 * secreta, garantindo tambem que quem gerou o hash conhecia a chave.
 * ============================================================================
 */
class HmacSha256 {
public:
    static Bytes calcular(const Bytes &chave, const std::string &mensagem) {
        Bytes resultado(EVP_MAX_MD_SIZE);
        unsigned int resultadoLen;

        // HMAC() calcula tudo de uma vez: algoritmo de hash usado por baixo
        // (SHA-256), a chave e os dados a autenticar
        HMAC(EVP_sha256(),
             chave.data(), static_cast<int>(chave.size()),
             reinterpret_cast<const unsigned char*>(mensagem.data()), mensagem.size(),
             resultado.data(), &resultadoLen);

        resultado.resize(resultadoLen);
        return resultado;
    }
};

void demoHmac() {
    std::cout << "\n=== 6. HMAC-SHA256 ===\n";

    Bytes chave = Aleatorio::gerarBytes(32); // chave secreta compartilhada
    std::string mensagem = "transferir 100 reais";

    Bytes resultado = HmacSha256::calcular(chave, mensagem);

    std::cout << "Mensagem:    " << mensagem << "\n";
    std::cout << "HMAC-SHA256: " << paraHex(resultado) << "\n";

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
class Pbkdf2 {
public:
    static Bytes derivar(const std::string &senha, const Bytes &salt,
                          int iteracoes, size_t tamanhoChave) {
        Bytes chaveDerivada(tamanhoChave);

        // Parametros: senha, salt, numero de iteracoes (quanto mais, mais
        // lento e mais seguro contra forca bruta), algoritmo de hash e
        // tamanho da saida desejada
        PKCS5_PBKDF2_HMAC(senha.data(), static_cast<int>(senha.size()),
                           salt.data(), static_cast<int>(salt.size()),
                           iteracoes,
                           EVP_sha256(),
                           static_cast<int>(chaveDerivada.size()), chaveDerivada.data());

        return chaveDerivada;
    }
};

void demoPbkdf2() {
    std::cout << "\n=== 7. PBKDF2 (senha -> chave) ===\n";

    std::string senha = "minhaSenh4Fraca";

    // "salt": valor aleatorio unico, guardado junto com a chave derivada.
    // Impede que a mesma senha sempre gere a mesma chave.
    Bytes salt = Aleatorio::gerarBytes(16);

    Bytes chaveDerivada = Pbkdf2::derivar(senha, salt, 100000, 32);

    std::cout << "Senha:          " << senha << "\n";
    std::cout << "Salt:           " << paraHex(salt) << "\n";
    std::cout << "Chave derivada: " << paraHex(chaveDerivada) << "\n";

    // Importante: o salt precisa ser guardado (nao e secreto), pois sera
    // necessario para derivar a mesma chave de novo no futuro.
}

/* ============================================================================
 * PARTE 2.5 - AES-256-CBC (cifra simetrica)
 * Cifra e decifra dados usando a MESMA chave dos dois lados.
 * ============================================================================
 */
class AesCbc {
public:
    static Bytes cifrar(const std::string &texto, const Bytes &chave, const Bytes &iv) {
        Bytes cifrado(texto.size() + EVP_MAX_BLOCK_LENGTH); // espaco extra para o padding
        int len, cifradoLen;

        EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();

        // Define o algoritmo (AES-256-CBC), a chave e o IV
        EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), nullptr, chave.data(), iv.data());

        // Processa os dados (pode ser chamado varias vezes para dados grandes)
        EVP_EncryptUpdate(ctx, cifrado.data(), &len,
                           reinterpret_cast<const unsigned char*>(texto.data()), static_cast<int>(texto.size()));
        cifradoLen = len;

        // Finaliza (aplica o padding necessario no ultimo bloco)
        EVP_EncryptFinal_ex(ctx, cifrado.data() + len, &len);
        cifradoLen += len;

        EVP_CIPHER_CTX_free(ctx);

        cifrado.resize(cifradoLen);
        return cifrado;
    }

    static std::string decifrar(const Bytes &cifrado, const Bytes &chave, const Bytes &iv) {
        Bytes decifrado(cifrado.size() + EVP_MAX_BLOCK_LENGTH);
        int len, decifradoLen;

        EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
        EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), nullptr, chave.data(), iv.data());

        EVP_DecryptUpdate(ctx, decifrado.data(), &len, cifrado.data(), static_cast<int>(cifrado.size()));
        decifradoLen = len;

        EVP_DecryptFinal_ex(ctx, decifrado.data() + len, &len);
        decifradoLen += len;

        EVP_CIPHER_CTX_free(ctx);

        return std::string(decifrado.begin(), decifrado.begin() + decifradoLen);
    }
};

void demoAesCbc() {
    std::cout << "\n=== 8. AES-256-CBC ===\n";

    Bytes chave = Aleatorio::gerarBytes(32); // 32 bytes = AES-256
    Bytes iv = Aleatorio::gerarBytes(16);    // IV deve ser diferente a cada cifragem

    std::string texto = "Mensagem secreta";

    Bytes cifrado = AesCbc::cifrar(texto, chave, iv);
    std::cout << "Original:      " << texto << "\n";
    std::cout << "Cifrado (hex): " << paraHex(cifrado) << "\n";

    std::string decifrado = AesCbc::decifrar(cifrado, chave, iv);
    std::cout << "Decifrado:     " << decifrado << "\n";
}

/* ============================================================================
 * PARTE 2.6 - AES-256-GCM (cifra + autenticacao, o padrao moderno)
 * O CBC so cifra -- nao detecta se alguem alterou o dado cifrado. O GCM
 * cifra e gera uma "tag" que comprova que os dados nao foram alterados.
 * ============================================================================
 */
class AesGcm {
public:
    struct Resultado {
        Bytes cifrado;
        Bytes tag; // tag de autenticacao gerada durante a cifragem
    };

    static Resultado cifrar(const std::string &texto, const Bytes &chave, const Bytes &iv) {
        Resultado r;
        r.cifrado.resize(texto.size());
        r.tag.resize(16);
        int len, cifradoLen;

        EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
        EVP_EncryptInit_ex(ctx, EVP_aes_256_gcm(), nullptr, nullptr, nullptr);

        // Define o tamanho do IV (opcional aqui, pois 12 bytes ja e o padrao)
        EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, static_cast<int>(iv.size()), nullptr);

        // Agora sim define a chave e o IV
        EVP_EncryptInit_ex(ctx, nullptr, nullptr, chave.data(), iv.data());

        EVP_EncryptUpdate(ctx, r.cifrado.data(), &len,
                           reinterpret_cast<const unsigned char*>(texto.data()), static_cast<int>(texto.size()));
        cifradoLen = len;

        EVP_EncryptFinal_ex(ctx, r.cifrado.data() + len, &len);
        cifradoLen += len;

        // Pega a tag de autenticacao gerada durante o processo de cifragem
        EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, static_cast<int>(r.tag.size()), r.tag.data());
        EVP_CIPHER_CTX_free(ctx);

        r.cifrado.resize(cifradoLen);
        return r;
    }

    // Retorna par (sucesso, texto decifrado). Se a tag nao bater (dado
    // alterado), sucesso vem false e o texto deve ser ignorado.
    static std::pair<bool, std::string> decifrar(const Bytes &cifrado, Bytes tag,
                                                    const Bytes &chave, const Bytes &iv) {
        Bytes decifrado(cifrado.size());
        int len, decifradoLen;

        EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
        EVP_DecryptInit_ex(ctx, EVP_aes_256_gcm(), nullptr, nullptr, nullptr);
        EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, static_cast<int>(iv.size()), nullptr);
        EVP_DecryptInit_ex(ctx, nullptr, nullptr, chave.data(), iv.data());

        EVP_DecryptUpdate(ctx, decifrado.data(), &len, cifrado.data(), static_cast<int>(cifrado.size()));
        decifradoLen = len;

        // Informa qual tag deve ser conferida na finalizacao
        EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_TAG, static_cast<int>(tag.size()), tag.data());

        // Se a tag nao bater (dado foi alterado), a funcao retorna <= 0
        int ok = EVP_DecryptFinal_ex(ctx, decifrado.data() + len, &len);
        EVP_CIPHER_CTX_free(ctx);

        if (ok <= 0) {
            return {false, ""};
        }

        decifradoLen += len;
        return {true, std::string(decifrado.begin(), decifrado.begin() + decifradoLen)};
    }
};

void demoAesGcm() {
    std::cout << "\n=== 9. AES-256-GCM ===\n";

    Bytes chave = Aleatorio::gerarBytes(32);
    Bytes iv = Aleatorio::gerarBytes(12); // GCM usa IV de 12 bytes por convencao

    std::string texto = "Dados confidenciais";

    AesGcm::Resultado r = AesGcm::cifrar(texto, chave, iv);
    std::cout << "Original: " << texto << "\n";
    std::cout << "Cifrado:  " << paraHex(r.cifrado) << "\n";
    std::cout << "Tag:      " << paraHex(r.tag) << "\n";

    auto [ok, decifrado] = AesGcm::decifrar(r.cifrado, r.tag, chave, iv);

    if (ok) {
        std::cout << "Decifrado (autenticado com sucesso): " << decifrado << "\n";
    } else {
        std::cout << "ERRO: dado corrompido ou adulterado!\n";
    }
}

/* ============================================================================
 * PARTE 2.7 - RSA (cifra assimetrica com par de chaves)
 * Usa DUAS chaves diferentes: uma publica (cifra, pode ser compartilhada) e
 * uma privada (decifra, deve ficar em segredo).
 * ============================================================================
 */
class ParChavesRsa {
public:
    // RAII simples: gera o par de chaves no construtor e libera no destrutor,
    // evitando vazamento de memoria mesmo se ocorrer alguma excecao
    ParChavesRsa(int bits = 2048) {
        EVP_PKEY_CTX *pctx = EVP_PKEY_CTX_new_id(EVP_PKEY_RSA, nullptr);
        EVP_PKEY_keygen_init(pctx);
        EVP_PKEY_CTX_set_rsa_keygen_bits(pctx, bits);

        EVP_PKEY_keygen(pctx, &chave_); // gera as duas chaves (publica e privada) juntas
        EVP_PKEY_CTX_free(pctx);
    }

    ~ParChavesRsa() {
        if (chave_) EVP_PKEY_free(chave_);
    }

    // Impede copia (evitaria liberar a mesma chave duas vezes)
    ParChavesRsa(const ParChavesRsa&) = delete;
    ParChavesRsa& operator=(const ParChavesRsa&) = delete;

    EVP_PKEY* handle() const { return chave_; }

private:
    EVP_PKEY *chave_ = nullptr;
};

class Rsa {
public:
    static Bytes cifrar(ParChavesRsa &par, const std::string &mensagem) {
        Bytes cifrado(256);
        size_t cifradoLen = cifrado.size();

        EVP_PKEY_CTX *ectx = EVP_PKEY_CTX_new(par.handle(), nullptr);
        EVP_PKEY_encrypt_init(ectx);
        EVP_PKEY_CTX_set_rsa_padding(ectx, RSA_PKCS1_OAEP_PADDING); // padding seguro

        EVP_PKEY_encrypt(ectx, cifrado.data(), &cifradoLen,
                          reinterpret_cast<const unsigned char*>(mensagem.data()), mensagem.size());
        EVP_PKEY_CTX_free(ectx);

        cifrado.resize(cifradoLen);
        return cifrado;
    }

    static std::string decifrar(ParChavesRsa &par, const Bytes &cifrado) {
        Bytes decifrado(256);
        size_t decifradoLen = decifrado.size();

        EVP_PKEY_CTX *dctx = EVP_PKEY_CTX_new(par.handle(), nullptr);
        EVP_PKEY_decrypt_init(dctx);
        EVP_PKEY_CTX_set_rsa_padding(dctx, RSA_PKCS1_OAEP_PADDING);

        EVP_PKEY_decrypt(dctx, decifrado.data(), &decifradoLen, cifrado.data(), cifrado.size());
        EVP_PKEY_CTX_free(dctx);

        return std::string(decifrado.begin(), decifrado.begin() + decifradoLen);
    }
};

void demoRsaCifrar() {
    std::cout << "\n=== 10. RSA (cifra assimetrica) ===\n";

    ParChavesRsa par; // gera o par de chaves (2048 bits por padrao)

    std::string mensagem = "segredo";

    Bytes cifrado = Rsa::cifrar(par, mensagem);
    std::cout << "Mensagem: " << mensagem << "\n";
    std::cout << "Cifrado:  " << paraHex(cifrado) << "\n";

    std::string decifrado = Rsa::decifrar(par, cifrado);
    std::cout << "Decifrado: " << decifrado << "\n";

    // Nota: RSA so cifra blocos pequenos (poucas centenas de bytes). Na
    // pratica, RSA cifra uma chave AES, e o AES cifra os dados de fato --
    // e a chamada "criptografia hibrida" (assim funciona o HTTPS).
}

/* ============================================================================
 * PARTE 2.8 - ASSINATURA DIGITAL RSA
 * Diferente de cifrar, aqui o objetivo e provar autoria: qualquer um pode
 * conferir a assinatura com a chave publica, mas so quem tem a chave
 * privada consegue cria-la.
 * ============================================================================
 */
class AssinaturaRsa {
public:
    static Bytes assinar(ParChavesRsa &par, const std::string &mensagem) {
        size_t assinaturaLen;

        EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
        EVP_DigestSignInit(mdctx, nullptr, EVP_sha256(), nullptr, par.handle());
        EVP_DigestSignUpdate(mdctx, mensagem.data(), mensagem.size());

        // Primeira chamada com nullptr apenas descobre o tamanho da assinatura
        EVP_DigestSignFinal(mdctx, nullptr, &assinaturaLen);

        Bytes assinatura(assinaturaLen);
        EVP_DigestSignFinal(mdctx, assinatura.data(), &assinaturaLen);
        EVP_MD_CTX_free(mdctx);

        assinatura.resize(assinaturaLen);
        return assinatura;
    }

    static bool verificar(ParChavesRsa &par, const std::string &mensagem, const Bytes &assinatura) {
        // Aqui usamos o mesmo par de chaves porque ele contem a parte
        // publica e a privada, mas em um sistema real quem verifica teria
        // apenas a chave publica.
        EVP_MD_CTX *mdctx = EVP_MD_CTX_new();
        EVP_DigestVerifyInit(mdctx, nullptr, EVP_sha256(), nullptr, par.handle());
        EVP_DigestVerifyUpdate(mdctx, mensagem.data(), mensagem.size());

        int valido = EVP_DigestVerifyFinal(mdctx, assinatura.data(), assinatura.size());
        EVP_MD_CTX_free(mdctx);

        return valido == 1;
    }
};

void demoRsaAssinatura() {
    std::cout << "\n=== 11. Assinatura digital RSA ===\n";

    ParChavesRsa par;
    std::string mensagem = "Autorizo o pagamento de 500 reais";

    Bytes assinatura = AssinaturaRsa::assinar(par, mensagem);
    std::cout << "Mensagem:   " << mensagem << "\n";
    std::cout << "Assinatura: " << paraHex(assinatura) << "\n";

    bool valido = AssinaturaRsa::verificar(par, mensagem, assinatura);

    if (valido) {
        std::cout << "Resultado: assinatura VALIDA -- a mensagem e autentica.\n";
    } else {
        std::cout << "Resultado: assinatura INVALIDA -- mensagem alterada ou chave errada.\n";
    }
}

/* ============================================================================
 * MENU PRINCIPAL
 * Permite escolher qual exemplo rodar, ou rodar todos em sequencia.
 * ============================================================================
 */
void mostrarMenu() {
    std::cout << "\n===================================================\n";
    std::cout << " CRIPTOGRAFIA EM C++ - MENU DE EXEMPLOS\n";
    std::cout << "===================================================\n";
    std::cout << " Cifras classicas (sem bibliotecas externas):\n";
    std::cout << "  1. Cifra de Cesar\n";
    std::cout << "  2. Cifra de Vigenere\n";
    std::cout << "  3. Cifra XOR\n";
    std::cout << " Criptografia real (com OpenSSL):\n";
    std::cout << "  4. Bytes aleatorios seguros\n";
    std::cout << "  5. Hash SHA-256\n";
    std::cout << "  6. HMAC-SHA256\n";
    std::cout << "  7. PBKDF2 (senha -> chave)\n";
    std::cout << "  8. AES-256-CBC\n";
    std::cout << "  9. AES-256-GCM\n";
    std::cout << " 10. RSA (cifrar/decifrar)\n";
    std::cout << " 11. Assinatura digital RSA\n";
    std::cout << "  0. Rodar TODOS os exemplos em sequencia\n";
    std::cout << " -1. Sair\n";
    std::cout << "===================================================\n";
    std::cout << "Escolha uma opcao: ";
}

void rodarTodos() {
    demoCesar();
    demoVigenere();
    demoXor();
    demoBytesAleatorios();
    demoSha256();
    demoHmac();
    demoPbkdf2();
    demoAesCbc();
    demoAesGcm();
    demoRsaCifrar();
    demoRsaAssinatura();
}

int main() {
    int opcao;

    do {
        mostrarMenu();

        // le a opcao do usuario; se a entrada for invalida, encerra o loop
        if (!(std::cin >> opcao)) {
            break;
        }

        switch (opcao) {
            case 1:  demoCesar(); break;
            case 2:  demoVigenere(); break;
            case 3:  demoXor(); break;
            case 4:  demoBytesAleatorios(); break;
            case 5:  demoSha256(); break;
            case 6:  demoHmac(); break;
            case 7:  demoPbkdf2(); break;
            case 8:  demoAesCbc(); break;
            case 9:  demoAesGcm(); break;
            case 10: demoRsaCifrar(); break;
            case 11: demoRsaAssinatura(); break;
            case 0:  rodarTodos(); break;
            case -1: std::cout << "Encerrando...\n"; break;
            default: std::cout << "Opcao invalida!\n";
        }

    } while (opcao != -1);

    return 0;
}
