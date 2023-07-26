REST API, которое взаимодействует с файловым хранилищем AWS S3 и предоставляет возможность получать доступ к файлам и истории загрузок. Логика безопасности реализована средствами JWT токена. Приложение докеризировано и готово к развертыванию в виде Docker контейнера.


Сущности:
User (List<Event> events,  Status status, …)
Event (User user, File file, Status status)
File (id, location, Status status ...)
User -> … List<Events> events ...


Взаимодействие с S3 должно быть реализовано с помощью AWS SDK.


Уровни доступа:
ADMIN - полный доступ к приложению

MODERATOR - права USER + чтение всех User + чтение/изменение/удаление всех Events + чтение/изменение/удаление всех Files

USER - только чтение всех своих данных + загрузка файлов для себя


Технологии: Java, MySQL, Spring (Boot, Reactive Data, WebFlux, Security), AWS SDK, MySQL, Docker, JUnit, Mockito, Gradle.
