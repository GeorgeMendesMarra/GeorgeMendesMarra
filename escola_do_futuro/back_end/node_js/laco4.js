const usuarios = [{id: 1, nome: 'Alan Turing'}, {id: 2, nome: 'Grace Hopper'}];

// Executa uma função de callback para cada elemento do array
usuarios.forEach((usuario, index) => {
    console.log(`Usuário ${index + 1}: ${usuario.nome}`);
});
