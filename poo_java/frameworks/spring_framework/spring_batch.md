# ⚙️ Spring Batch — visão geral

O **Spring Batch** é um framework do Spring para **processamento em lote (batch)**, ideal para:

* Grandes volumes de dados
* Processamentos demorados
* Tarefas periódicas
* ETL (Extract, Transform, Load)

Exemplos clássicos:

* Folha de pagamento
* Importação de CSV
* Processamento de logs
* Cálculo de indicadores
* Migração de dados

---

## 🧠 Conceitos fundamentais (cai muito!)

| Conceito          | Função                  |
| ----------------- | ----------------------- |
| **Job**           | Processo batch completo |
| **Step**          | Etapa do Job            |
| **ItemReader**    | Lê dados                |
| **ItemProcessor** | Processa / transforma   |
| **ItemWriter**    | Grava dados             |
| **JobRepository** | Metadados do batch      |
| **JobLauncher**   | Executa o Job           |

---

## 🏗️ Arquitetura básica

```
Job
 ├── Step 1
 │    ├── Reader
 │    ├── Processor
 │    └── Writer
 └── Step 2 ...
```

---

## 📄 Exemplo prático: Processar CSV

### 1️⃣ Entidade

```java
public class Cliente {
    private String nome;
    private String email;
    private Double saldo;
    // getters e setters
}
```

---

### 2️⃣ Reader (ler CSV)

```java
@Bean
public FlatFileItemReader<Cliente> reader() {
    return new FlatFileItemReaderBuilder<Cliente>()
        .name("clienteReader")
        .resource(new ClassPathResource("clientes.csv"))
        .delimited()
        .names("nome", "email", "saldo")
        .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
            setTargetType(Cliente.class);
        }})
        .build();
}
```

---

### 3️⃣ Processor (regra de negócio)

```java
@Bean
public ItemProcessor<Cliente, Cliente> processor() {
    return cliente -> {
        cliente.setSaldo(cliente.getSaldo() * 1.1);
        return cliente;
    };
}
```

---

### 4️⃣ Writer (salvar no banco)

```java
@Bean
public JdbcBatchItemWriter<Cliente> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Cliente>()
        .sql("INSERT INTO cliente (nome, email, saldo) VALUES (:nome, :email, :saldo)")
        .dataSource(dataSource)
        .beanMapped()
        .build();
}
```

---

### 5️⃣ Step

```java
@Bean
public Step step(JobRepository jobRepository,
                 PlatformTransactionManager transactionManager) {

    return new StepBuilder("step1", jobRepository)
        .<Cliente, Cliente>chunk(10, transactionManager)
        .reader(reader())
        .processor(processor())
        .writer(writer(null))
        .build();
}
```

📌 **Chunk** = quantidade de itens por transação.

---

### 6️⃣ Job

```java
@Bean
public Job job(JobRepository jobRepository, Step step) {
    return new JobBuilder("importClientes", jobRepository)
        .start(step)
        .build();
}
```

---

## 🔁 Fluxo do processamento

```
CSV → Reader → Processor → Writer → Banco
```

---

## 🔄 Execução do Job

```java
jobLauncher.run(job, new JobParameters());
```

📌 Cada execução gera metadados no banco (`BATCH_*`).

---

## 🚨 Controle de falhas

* Retry
* Skip
* Restart

```java
.faultTolerant()
.skipLimit(5)
.skip(Exception.class)
```

---

## ⏱️ Spring Batch + Scheduler

```java
@Scheduled(cron = "0 0 2 * * *")
public void executar() {
    jobLauncher.run(job, new JobParameters());
}
```

---

## 🎓 Texto pronto para TCC

> *O Spring Batch foi utilizado para o processamento em lote de grandes volumes de dados, garantindo controle transacional, rastreabilidade e tolerância a falhas.*

---

## ⚖️ Quando usar Spring Batch?

✔️ Grandes volumes
✔️ Processamento pesado
✔️ Tarefas periódicas
❌ Não usar para requisições HTTP

---

## 💡 Dica de ouro

> **Batch ≠ API REST**

Batch é offline, controlado e previsível.

---
