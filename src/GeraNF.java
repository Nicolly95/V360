import java.util.concurrent.CountDownLatch;

public class GeraNF {

    public static void main(String[] args) {

        String[] clientes = {"Ana", "Bruna", "Carlos", "Daiane", "Eduardo", "Fernanda", "Gustavo","..."};

        // Contador para garantir que a terceira thread só comece após as duas primeiras terminarem
        CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread(() -> {
            for(int i=1; i<=10; i++) {
                System.out.println(Thread.currentThread().getName() + " Gerando NF nº " + i + "...");
            }
            latch.countDown();
            System.out.println(" ");
            try {
                Thread.sleep(300); // Adicionando um pequeno atraso para não sobrecarregar o sistema
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {
            for(int i=1; i<=10; i++) {
                System.out.println(Thread.currentThread().getName() + " Gerando NF nº " + i + "...");
            }
            latch.countDown();
            System.out.println(" ");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            try {
                latch.await();  // Aguarda até que as duas primeiras threads terminem
                System.out.println(" ");    //add uma linha em branco
                for (int c = 0; c < clientes.length; c++) { // Enviar uma NF por cliente
                    System.out.println(Thread.currentThread().getName() + " Enviando NF nº " + (c + 1) + " para o email do cliente " + clientes[c]);
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        });
        thread3.start();
    }
}