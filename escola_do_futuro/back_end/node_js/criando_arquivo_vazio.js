// 'fs' é o módulo de File System (Sistema de Arquivos)
const fs = require('fs'); 
// O comando abaixo cria (ou sobrescreve) um arquivo chamado 'vazio.txt' com conteúdo vazio ('')
fs.writeFileSync('vazio.txt', ''); 
console.log("Arquivo criado no teu computador!");
