# 🎨 Utility Classes - Documentação Completa

## 📋 Índice

- [Margin](#margin)
- [Padding](#padding)
- [Border Style](#border-style)
- [Border Radius](#border-radius)
- [Border Width](#border-width)
- [Text](#text)
- [Width](#width)
- [Min-Width](#min-width)
- [Max-Width](#max-width)
- [Height](#height)
- [Min-Height](#min-height)
- [Max-Height](#max-height)

---

## 📐 Margin <a name="margin"></a>

Controla as margens externas dos elementos.

### Sintaxe

| Classe | Descrição |
|--------|-----------|
| `.m-{valor}` | Margem em **todos os lados** |
| `.mt-{valor}` | Margem **superior** |
| `.mr-{valor}` | Margem **direita** |
| `.mb-{valor}` | Margem **inferior** |
| `.ml-{valor}` | Margem **esquerda** |
| `.mx-{valor}` | Margem **horizontal** (direita + esquerda) |
| `.my-{valor}` | Margem **vertical** (superior + inferior) |

### Valores Disponíveis

| Classe | Tamanho | Equivalente |
|--------|---------|-------------|
| `.m-0` | `0px` | - |
| `.m-px` | `1px` | - |
| `.m-0_5` | `0.125rem` | `2px` |
| `.m-1` | `0.25rem` | `4px` |
| `.m-1_5` | `0.375rem` | `6px` |
| `.m-2` | `0.5rem` | `8px` |
| `.m-2_5` | `0.625rem` | `10px` |
| `.m-3` | `0.75rem` | `12px` |
| `.m-3_5` | `0.875rem` | `14px` |
| `.m-4` | `1rem` | `16px` |
| `.m-5` | `1.25rem` | `20px` |
| `.m-6` | `1.5rem` | `24px` |
| `.m-7` | `1.75rem` | `28px` |
| `.m-8` | `2rem` | `32px` |
| `.m-9` | `2.25rem` | `36px` |
| `.m-10` | `2.5rem` | `40px` |
| `.m-11` | `2.75rem` | `44px` |
| `.m-12` | `3rem` | `48px` |
| `.m-14` | `3.5rem` | `56px` |
| `.m-16` | `4rem` | `64px` |
| `.m-20` | `5rem` | `80px` |
| `.m-24` | `6rem` | `96px` |
| `.m-28` | `7rem` | `112px` |
| `.m-32` | `8rem` | `128px` |
| `.m-36` | `9rem` | `144px` |
| `.m-40` | `10rem` | `160px` |
| `.m-44` | `11rem` | `176px` |
| `.m-48` | `12rem` | `192px` |
| `.m-52` | `13rem` | `208px` |
| `.m-56` | `14rem` | `224px` |
| `.m-60` | `15rem` | `240px` |
| `.m-64` | `16rem` | `256px` |
| `.m-72` | `18rem` | `288px` |
| `.m-80` | `20rem` | `320px` |
| `.m-96` | `24rem` | `384px` |
| `.m-auto` | `auto` | Centralização |
| `.mx-auto` | `auto` | Centralização horizontal |
| `.my-auto` | `auto` | Centralização vertical |
| `.m-full` | `100%` | - |

---

## 📦 Padding <a name="padding"></a>

Controla o espaçamento interno dos elementos.

| Classe | Descrição |
|--------|-----------|
| `.p-{valor}` | Padding em **todos os lados** |
| `.px-{valor}` | Padding **horizontal** (direita + esquerda) |
| `.py-{valor}` | Padding **vertical** (superior + inferior) |
| `.ps-{valor}` | Padding no **início** do eixo (*start*) |
| `.pe-{valor}` | Padding no **final** do eixo (*end*) |
| `.pt-{valor}` | Padding **superior** (*top*) |
| `.pr-{valor}` | Padding **direita** (*right*) |
| `.pb-{valor}` | Padding **inferior** (*bottom*) |
| `.pl-{valor}` | Padding **esquerda** (*left*) |

> 💡 **Dica:** Os valores disponíveis seguem a mesma escala da tabela de margin.

---

## 🎨 Border Style <a name="border-style"></a>

Define o estilo visual das bordas.

| Classe | Efeito |
|--------|--------|
| `.border-solid` | ────── Linha **sólida** |
| `.border-dashed` | - - - - Linha **tracejada** |
| `.border-dotted` | ······ Linha **pontilhada** |
| `.border-double` | ══════ Linha **dupla** |
| `.border-hidden` | Borda **invisível** (ocupa espaço) |
| `.border-none` | Sem borda (não ocupa espaço) |

---

## 🔘 Border Radius <a name="border-radius"></a>

Arredonda os cantos dos elementos.

### Valores Globais

| Classe | Tamanho | Exemplo |
|--------|---------|---------|
| `.rounded-none` | `0px` | ▢ |
| `.rounded-sm` | `0.125rem` (2px) | ◻ |
| `.rounded` | `0.25rem` (4px) | ◇ |
| `.rounded-md` | `0.375rem` (6px) | ◇ |
| `.rounded-lg` | `0.5rem` (8px) | ◉ |
| `.rounded-xl` | `0.75rem` (12px) | ◉ |
| `.rounded-2xl` | `1rem` (16px) | ○ |
| `.rounded-3xl` | `1.5rem` (24px) | ○ |
| `.rounded-full` | `9999px` | ● |

### Por Direção

| Prefixo | Aplica-se a |
|---------|-------------|
| `.rounded-t-*` | **Topo** (canto superior esquerdo + direito) |
| `.rounded-r-*` | **Direita** (canto superior direito + inferior direito) |
| `.rounded-b-*` | **Inferior** (canto inferior esquerdo + direito) |
| `.rounded-l-*` | **Esquerda** (canto superior esquerdo + inferior esquerdo) |

### Por Canto Individual

| Prefixo | Canto |
|---------|-------|
| `.rounded-tl-*` | Superior **Esquerdo** |
| `.rounded-tr-*` | Superior **Direito** |
| `.rounded-br-*` | Inferior **Direito** |
| `.rounded-bl-*` | Inferior **Esquerdo** |

### Eixos Lógicos (CSS Logical Properties)

| Prefixo | Descrição |
|---------|-----------|
| `.rounded-s-*` | Início do eixo (*start*) |
| `.rounded-e-*` | Final do eixo (*end*) |
| `.rounded-ss-*` | Início do eixo secundário |
| `.rounded-se-*` | Final do eixo secundário |
| `.rounded-ee-*` | Final-final (lógico) |
| `.rounded-es-*` | Final-início (lógico) |

> 💡 **Todos os valores** (sm, md, lg, xl, 2xl, 3xl, full, none) estão disponíveis para **cada prefixo** acima.

---

## 📏 Border Width <a name="border-width"></a>

Define a espessura das bordas.

### Valores Disponíveis

| Classe | Espessura |
|--------|-----------|
| `.border-0` | `0px` |
| `.border` | `1px` (padrão) |
| `.border-2` | `2px` |
| `.border-4` | `4px` |
| `.border-8` | `8px` |

### Por Direção

| Prefixo | Descrição |
|---------|-----------|
| `.border-x-*` | Horizontal (esquerda + direita) |
| `.border-y-*` | Vertical (superior + inferior) |
| `.border-t-*` | Topo |
| `.border-r-*` | Direita |
| `.border-b-*` | Inferior |
| `.border-l-*` | Esquerda |
| `.border-s-*` | Início do eixo (*start*) |
| `.border-e-*` | Final do eixo (*end*) |

---

## 🔤 Text <a name="text"></a>

Controla o tamanho da fonte e altura da linha.

| Classe | Tamanho | Altura da Linha | Pixels |
|--------|---------|-----------------|--------|
| `.text-xs` | `0.75rem` | `1rem` | 12px / 16px |
| `.text-sm` | `0.875rem` | `1.25rem` | 14px / 20px |
| `.text-base` | `1rem` | `1.5rem` | 16px / 24px |
| `.text-lg` | `1.125rem` | `1.75rem` | 18px / 28px |
| `.text-xl` | `1.25rem` | `1.75rem` | 20px / 28px |
| `.text-2xl` | `1.5rem` | `2rem` | 24px / 32px |
| `.text-3xl` | `1.875rem` | `2.25rem` | 30px / 36px |
| `.text-4xl` | `2.25rem` | `2.5rem` | 36px / 40px |
| `.text-5xl` | `3rem` | `1` | 48px / sem linha |
| `.text-6xl` | `3.75rem` | `1` | 60px / sem linha |
| `.text-7xl` | `4.5rem` | `1` | 72px / sem linha |
| `.text-8xl` | `6rem` | `1` | 96px / sem linha |
| `.text-9xl` | `8rem` | `1` | 128px / sem linha |

---

## 📏 Width <a name="width"></a>

Controla a largura dos elementos.

### Valores Fixos (rem)

| Classe | Valor | Classe | Valor | Classe | Valor |
|--------|-------|--------|-------|--------|-------|
| `.w-0` | `0px` | `.w-12` | `3rem` | `.w-56` | `14rem` |
| `.w-px` | `1px` | `.w-14` | `3.5rem` | `.w-60` | `15rem` |
| `.w-0_5` | `2px` | `.w-16` | `4rem` | `.w-64` | `16rem` |
| `.w-1` | `4px` | `.w-20` | `5rem` | `.w-72` | `18rem` |
| `.w-1_5` | `6px` | `.w-24` | `6rem` | `.w-80` | `20rem` |
| `.w-2` | `8px` | `.w-28` | `7rem` | `.w-96` | `24rem` |
| `.w-2_5` | `10px` | `.w-32` | `8rem` | | |
| `.w-3` | `12px` | `.w-36` | `9rem` | | |
| `.w-3_5` | `14px` | `.w-40` | `10rem` | | |
| `.w-4` | `16px` | `.w-44` | `11rem` | | |
| `.w-5` | `20px` | `.w-48` | `12rem` | | |
| `.w-6` | `24px` | `.w-52` | `13rem` | | |

### Porcentagens

| Classe | Porcentagem | Classe | Porcentagem |
|--------|-------------|--------|-------------|
| `.w-1_2` | 50% | `.w-1_5` | 20% |
| `.w-1_3` | 33.33% | `.w-2_5` | 40% |
| `.w-2_3` | 66.67% | `.w-3_5` | 60% |
| `.w-1_4` | 25% | `.w-4_5` | 80% |
| `.w-3_4` | 75% | `.w-1_6` | 16.67% |
| `.w-full` | 100% | `.w-5_6` | 83.33% |

### Valores Especiais

| Classe | Descrição |
|--------|-----------|
| `.w-auto` | Automática |
| `.w-screen` | `100vw` (largura da tela) |
| `.w-min` | `min-content` |
| `.w-max` | `max-content` |
| `.w-fit` | `fit-content` |

---

## 📏 Min-Width <a name="min-width"></a>

Define a largura mínima do elemento.

### Valores Disponíveis

| Classe | Valor | Classe | Valor |
|--------|-------|--------|-------|
| `.min-w-0` | `0px` | `.min-w-12` | `3rem` |
| `.min-w-px` | `1px` | `.min-w-16` | `4rem` |
| `.min-w-1` | `0.25rem` | `.min-w-20` | `5rem` |
| `.min-w-2` | `0.5rem` | `.min-w-24` | `6rem` |
| `.min-w-3` | `0.75rem` | `.min-w-32` | `8rem` |
| `.min-w-4` | `1rem` | `.min-w-40` | `10rem` |
| `.min-w-5` | `1.25rem` | `.min-w-48` | `12rem` |
| `.min-w-6` | `1.5rem` | `.min-w-64` | `16rem` |
| `.min-w-7` | `1.75rem` | `.min-w-80` | `20rem` |
| `.min-w-8` | `2rem` | `.min-w-96` | `24rem` |

### Valores Especiais

| Classe | Descrição |
|--------|-----------|
| `.min-w-full` | `100%` |
| `.min-w-min` | `min-content` |
| `.min-w-max` | `max-content` |
| `.min-w-fit` | `fit-content` |

---

## 📏 Max-Width <a name="max-width"></a>

Define a largura máxima do elemento.

### Valores Fixos

| Classe | Valor | Classe | Valor |
|--------|-------|--------|-------|
| `.max-w-0` | `0px` | `.max-w-48` | `12rem` |
| `.max-w-px` | `1px` | `.max-w-64` | `16rem` |
| `.max-w-1` | `0.25rem` | `.max-w-80` | `20rem` |
| `.max-w-2` | `0.5rem` | `.max-w-96` | `24rem` |
| `.max-w-4` | `1rem` | `.max-w-xs` | `20rem` (320px) |
| `.max-w-8` | `2rem` | `.max-w-sm` | `24rem` (384px) |
| `.max-w-12` | `3rem` | `.max-w-md` | `28rem` (448px) |
| `.max-w-16` | `4rem` | `.max-w-lg` | `32rem` (512px) |
| `.max-w-20` | `5rem` | `.max-w-xl` | `36rem` (576px) |
| `.max-w-24` | `6rem` | `.max-w-2xl` | `42rem` (672px) |
| `.max-w-32` | `8rem` | `.max-w-3xl` | `48rem` (768px) |
| `.max-w-40` | `10rem` | `.max-w-4xl` | `56rem` (896px) |
| | | `.max-w-5xl` | `64rem` (1024px) |
| | | `.max-w-6xl` | `72rem` (1152px) |
| | | `.max-w-7xl` | `80rem` (1280px) |

### Breakpoints de Tela

| Classe | Largura |
|--------|---------|
| `.max-w-screen-sm` | `640px` |
| `.max-w-screen-md` | `768px` |
| `.max-w-screen-lg` | `1024px` |
| `.max-w-screen-xl` | `1280px` |
| `.max-w-screen-2xl` | `1536px` |

### Valores Especiais

| Classe | Descrição |
|--------|-----------|
| `.max-w-none` | Sem limite |
| `.max-w-full` | `100%` |
| `.max-w-prose` | `65ch` (largura ideal para texto) |
| `.max-w-min` | `min-content` |
| `.max-w-max` | `max-content` |
| `.max-w-fit` | `fit-content` |

---

## 📐 Height <a name="height"></a>

Controla a altura dos elementos.

### Valores Fixos (rem)

| Classe | Valor | Classe | Valor | Classe | Valor |
|--------|-------|--------|-------|--------|-------|
| `.h-0` | `0px` | `.h-12` | `3rem` | `.h-56` | `14rem` |
| `.h-px` | `1px` | `.h-14` | `3.5rem` | `.h-60` | `15rem` |
| `.h-0_5` | `2px` | `.h-16` | `4rem` | `.h-64` | `16rem` |
| `.h-1` | `4px` | `.h-20` | `5rem` | `.h-72` | `18rem` |
| `.h-1_5` | `6px` | `.h-24` | `6rem` | `.h-80` | `20rem` |
| `.h-2` | `8px` | `.h-28` | `7rem` | `.h-96` | `24rem` |
| `.h-2_5` | `10px` | `.h-32` | `8rem` | | |
| `.h-3` | `12px` | `.h-36` | `9rem` | | |
| `.h-3_5` | `14px` | `.h-40` | `10rem` | | |
| `.h-4` | `16px` | `.h-44` | `11rem` | | |
| `.h-5` | `20px` | `.h-48` | `12rem` | | |
| `.h-6` | `24px` | `.h-52` | `13rem` | | |

### Porcentagens

| Classe | Porcentagem | Classe | Porcentagem |
|--------|-------------|--------|-------------|
| `.h-1_2` | 50% | `.h-1_5` | 20% |
| `.h-1_3` | 33.33% | `.h-2_5` | 40% |
| `.h-2_3` | 66.67% | `.h-3_5` | 60% |
| `.h-1_4` | 25% | `.h-4_5` | 80% |
| `.h-3_4` | 75% | `.h-1_6` | 16.67% |
| `.h-full` | 100% | `.h-5_6` | 83.33% |

### Valores Especiais

| Classe | Descrição |
|--------|-----------|
| `.h-auto` | Automática |
| `.h-screen` | `100vh` (altura da tela) |
| `.h-min` | `min-content` |
| `.h-max` | `max-content` |
| `.h-fit` | `fit-content` |

---

## 📏 Min-Height <a name="min-height"></a>

Define a altura mínima do elemento.

| Classe | Descrição | Classe | Descrição |
|--------|-----------|--------|-----------|
| `.min-h-0` | `0px` | `.min-h-48` | `12rem` |
| `.min-h-px` | `1px` | `.min-h-64` | `16rem` |
| `.min-h-4` | `1rem` | `.min-h-80` | `20rem` |
| `.min-h-8` | `2rem` | `.min-h-96` | `24rem` |
| `.min-h-12` | `3rem` | `.min-h-full` | `100%` |
| `.min-h-16` | `4rem` | `.min-h-screen` | `100vh` |
| `.min-h-20` | `5rem` | `.min-h-min` | `min-content` |
| `.min-h-24` | `6rem` | `.min-h-max` | `max-content` |
| `.min-h-32` | `8rem` | `.min-h-fit` | `fit-content` |

---

## 📏 Max-Height <a name="max-height"></a>

Define a altura máxima do elemento.

| Classe | Descrição | Classe | Descrição |
|--------|-----------|--------|-----------|
| `.max-h-0` | `0px` | `.max-h-48` | `12rem` |
| `.max-h-px` | `1px` | `.max-h-64` | `16rem` |
| `.max-h-4` | `1rem` | `.max-h-80` | `20rem` |
| `.max-h-8` | `2rem` | `.max-h-96` | `24rem` |
| `.max-h-12` | `3rem` | `.max-h-full` | `100%` |
| `.max-h-16` | `4rem` | `.max-h-screen` | `100vh` |
| `.max-h-20` | `5rem` | `.max-h-none` | Sem limite |
| `.max-h-24` | `6rem` | `.max-h-min` | `min-content` |
| `.max-h-32` | `8rem` | `.max-h-max` | `max-content` |
| `.max-h-40` | `10rem` | `.max-h-fit` | `fit-content` |

---

## 📊 Resumo Rápido

| Categoria | Prefixos principais | Valores comuns |
|-----------|---------------------|----------------|
| **Margin** | `m-`, `mt-`, `mr-`, `mb-`, `ml-`, `mx-`, `my-` | `0` a `96`, `auto`, `full` |
| **Padding** | `p-`, `pt-`, `pr-`, `pb-`, `pl-`, `px-`, `py-`, `ps-`, `pe-` | `0` a `96` |
| **Border Radius** | `rounded-`, `rounded-t-`, `rounded-r-`, `rounded-b-`, `rounded-l-` | `none`, `sm`, `md`, `lg`, `xl`, `2xl`, `3xl`, `full` |
| **Border Width** | `border-`, `border-t-`, `border-r-`, `border-b-`, `border-l-` | `0`, `2`, `4`, `8` |
| **Text** | `text-` | `xs` a `9xl` |
| **Width** | `w-`, `min-w-`, `max-w-` | `0` a `96`, porcentagens, `screen`, `full` |
| **Height** | `h-`, `min-h-`, `max-h-` | `0` a `96`, porcentagens, `screen`, `full` |

---

> 📝 **Nota:** Todos os valores seguem a escala base de `0.25rem = 4px`, seguindo o padrão do Tailwind CSS.
