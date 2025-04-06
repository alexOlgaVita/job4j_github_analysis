# GitHub аналитика

Этот проект является Spring Boot приложением, которое выполняет задачи по расписанию, анализируя данные с GitHub. 
Приложение должно регулярно собирать информацию о репозиториях и коммитах, а затем сохранять эти данные в базу данных.
Проект демонстрирует использование Spring Boot Scheduler для автоматизации этого процесса и применение аннотации @Async для асинхронного выполнения задач.

## Пример настройки

```java
@EnableScheduling
@EnableAsync
@SpringBootApplication
@AllArgsConstructor
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
```

**application.properties**:
```properties
spring.task.scheduling.pool.size=10
scheduler.fixedRate=3600000
```