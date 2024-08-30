# Expense Tracker Microservice

## Описание проекта

Этот микросервис предназначен для отслеживания транзакций и управления лимитами расходов. Он также интегрируется с внешним API для получения курсов валют и использует их для конвертации транзакций в USD. Сервис предоставляет REST API для записи транзакций, управления лимитами и получения отчетов.

## Требования

Для запуска проекта вам потребуются:

- JDK 17
- Maven 3.6+
- Docker и Docker Compose
- PostgreSQL
- API KEY alphavantage.co

## Установка и запуск

### 1. Клонирование репозитория

```bash
git clone https://github.com/kamzadias/expense-tracker.git
cd expense-tracker
```

### 2. Переменные окружения
Убедитесь, что вы создали файл .env в корне проекта перед запуском.
Пример .env файла:
```plaintext
API_KEY=your_api_key_here
SPRING_DATASOURCE_URL=your_datasource_url
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_db_password
```

### 3. Сборка проекта
```bash
mvn clean package
```

### 4. Запуск с помощью Docker Compose
```bash
docker-compose up --build
```

### 5. Доступ к Swagger UI
```URL
http://localhost:8080/swagger-ui.html
```

## Тестирование
Проект включает юнит-тест, которые можно запустить с помощью Maven:
```bash
mvn test
```

## Лицензия
Этот проект лицензирован под лицензией MIT.
