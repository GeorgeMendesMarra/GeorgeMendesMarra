public class Carro2 {    
   public String cor;   
   public String marca; 
   public String modelo; 
   
   protected void andar() { 
   ligar();
     System.out.println("Carro andando"); 
   }
   protected void parar() { 
     System.out.println("Carro parado"); 
   }
   private void ligar() {
     System.out.println("Carro Ligado");
  }
}
/*
Qualificadores de acesso:

public: define que o conteúdo da classe é público e pode ser utilizado livremente por outras classes do mesmo pacote ou de outro pacote

protected: define que o conteúdo da classe está protegido e que só pode ser utilizado por classes do mesmo pacote

private: define que o conteúdo é privado e só pode ser utilizado internamente na própria classe
*/

