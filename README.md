# homework-3-aston

# REST-сервис для хранения данных о достопримечательностях.

### Создан с использованием Spring Boot, Hibernate, PostgreSQl, Liquibase.

## Fast start

1. Запустите Docker Desktop.
2. В терминале intellij idea, поднимите контейнер командой *docker-compose up*, дождитесь запуска
   контейнера.
3. Запустите приложение, метод main, в классе Application.
4. Для проверки работы можно использовать Postman.

##### Данные в БД создаются с помощью Liquibase.
Для сущности City доступны операции POST и PUT, для сущности Attraction основные CRUD операции,
а так же операции получения всех Attraction с параметрами сортировки по имени
(пример запроса /attractions?sortByName=true) и операции получения Attraction с фильтрацией по
типу (пример запроса attractions?filterByType=PARK)


#### Используется БД Postgres:alpine3.18, подключен pgAdmin. Порт Tomcat установлен 8090 в классе Application.

Для входа в pdAdmin, откройте адрес в браузере http://localhost:5055/, логин *PGadmin@gmail.com*,
пароль *PGadmin*,
далее для подключения существующей БД, нажимаем на *Server-Register-Server...*, Вкладка General поле
Name, пишем любое
имя нашей БД, во вкладке Connection поле Host name/address *pg_db-homework3-rest*, поле Port *5435*, поле
Maintenance *attractions_db*,
Username *admin*, Password *admin*.