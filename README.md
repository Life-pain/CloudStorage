# Дипломная работа «Облачное хранилище»

## Описание
Сервис предоставляет возможность:
1. Авторизоваться в облачном хранилище;
2. Загружать файлы в хранилище;
3. Редактировать имя загруженного файла;
4. Скачивать файлы из хранилища;
5. Удалять файлы из хранилища.
## Запуск FRONT приложения
FRONT приложения доступен по адресу https://github.com/Life-pain/netologyDiplomFrontend.git
Для запуска FRONT приложения необходимо:
1. Скачать репозиторий и распаковать его в локальную папку
2. Установить node.js версии 16.17 
3. Перейти в папку FRONT приложения в командной строке и выполнить команды `npm install` затем `npm run serve`
4. Открыть в браузере страницу по адресу http://localhost:8080/

FRONT отправляет запросы по адресу http://localhost:8090/

## ТЕСТИРОВАНИЕ
Тестовые данные: почта - `test`, пароль `test`
(тестовый пользователь добавляется в файле `src/main/resources/data.sql`)
