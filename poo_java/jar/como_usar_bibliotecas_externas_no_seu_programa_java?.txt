1. Baixe a biblioteca Apache Commons Math
https://repo1.maven.org/maven2/org/apache/commons/commons-math3/3.6.1/commons-math3-3.6.1.jar
Salve esse .jar no mesmo diretório onde estiver seu arquivo .java, ou em algum lugar fácil de referenciar.
2. Código Java salvo como RaizesComplexas.java
3. Compilar no terminal
javac -cp commons-math3-3.6.1.jar RaizesComplexas.java
4. Executar no terminal
java -cp .:commons-math3-3.6.1.jar RaizesComplexas
Se estiver no Windows, substitua : por ;:
