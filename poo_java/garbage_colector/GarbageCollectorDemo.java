public class GarbageCollectorDemo {

    public static void main(String[] args) {
        // Exemplo 1: Objetos tornam-se elegíveis para GC ao sair do escopo
        createObjectsInScope();

        System.out.println("------------------------------------");

        // Exemplo 2: Objetos tornam-se elegíveis para GC quando suas referências são anuladas
        MyObject obj1 = new MyObject("Objeto A");
        MyObject obj2 = new MyObject("Objeto B");

        System.out.println("Objetos criados: " + obj1.name + ", " + obj2.name);

        // Desreferenciando obj1 - torna-o elegível para GC
        obj1 = null;
        System.out.println("Referência de Objeto A anulada (elegível para GC)");

        // Sugerir ao GC para rodar (não há garantia de execução imediata)
        System.gc(); // ou Runtime.getRuntime().gc();

        // Pausar um pouco para dar tempo ao GC, se ele for executado
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyObject obj3 = new MyObject("Objeto C");
        System.out.println("Novo objeto criado: " + obj3.name);

        // Desreferenciando obj2 - torna-o elegível para GC
        obj2 = null;
        System.out.println("Referência de Objeto B anulada (elegível para GC)");

        System.gc(); // Sugere novamente
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fim da demonstração do Garbage Collector.");
    }

    public static void createObjectsInScope() {
        System.out.println("Criando objetos dentro de um método...");
        MyObject localObj1 = new MyObject("Objeto Local 1");
        MyObject localObj2 = new MyObject("Objeto Local 2");
        System.out.println("Objetos locais criados: " + localObj1.name + ", " + localObj2.name);
        // Ao sair deste método, localObj1 e localObj2 se tornam elegíveis para GC,
        // pois não há mais referências a eles.
        System.out.println("Saindo do método - objetos locais elegíveis para GC.");
    }
}

// Classe de exemplo para demonstrar a finalização de objetos
class MyObject {
    String name;

    public MyObject(String name) {
        this.name = name;
        System.out.println(name + " criado.");
    }

    // O método finalize() é chamado pelo Garbage Collector antes de destruir o objeto.
    // OBS: O uso de finalize() é desencorajado em código moderno devido à sua natureza
    // não determinística e problemas de desempenho. Usado aqui apenas para fins de demonstração.
    @Override
    protected void finalize() throws Throwable {
        System.out.println(name + " está sendo finalizado pelo Garbage Collector.");
        super.finalize();
    }
}
