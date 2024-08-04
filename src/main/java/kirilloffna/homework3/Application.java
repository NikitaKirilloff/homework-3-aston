package kirilloffna.homework3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения, отвечающий за запуск Spring Boot приложения.
 * port: 8090
 */
@Slf4j
@SpringBootApplication
public class Application {

  /**
   * Главный метод, запускающий приложение.
   *
   * @param args Аргументы командной строки.
   */
  public static void main(String[] args) {
    log.info("Запуск приложения...");
    SpringApplication.run(Application.class, args);
    log.info("Приложение успешно запущено");
  }
}
