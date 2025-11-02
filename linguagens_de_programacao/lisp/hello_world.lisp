;; Define uma função (defun) chamada 'hello-world'
;; que não recebe nenhum argumento (lista vazia: ()).
(defun hello-world ()
  ;; Chama a função 'format' para saída formatada.
  ;; 't' (true) é o primeiro argumento e representa o 'stream' de saída padrão (o console).
  ;; O segundo argumento é a string literal.
  ;; O ~% (til + porcentagem) é uma diretiva que insere uma nova linha.
  (format t "Hello, World!~%"))

;; Chama a função que acabamos de definir para executar o código.
(hello-world)
