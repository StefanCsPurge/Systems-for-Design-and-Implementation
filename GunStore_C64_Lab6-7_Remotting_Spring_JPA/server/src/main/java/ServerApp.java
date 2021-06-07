
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ServerApp {
    public static void main(String[] args) {

        new AnnotationConfigApplicationContext("config");

        System.out.println("Server up and running!");

    }
}
