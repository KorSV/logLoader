
# Загрузчик логов.

Программа загружает лог из файла в таблицу базы данных.

### Режимы работы
1. __WATCH__ - работа в фоновом режиме. В этом режиме программа отслеживает изменения в файле лога и записывает их в базу данных. Для запуска в этом режиме нужно запустить скрипт start.sh из корня папки logLoader предварительно 
указав параметры PATH - путь к отслеживаемому файлу (!!!в пути не нужно указывать завершающий слэш!!!),
и FILE - имя отслеживаемого файла.
1. __ALL__ - в этом режиме программа загружает из файла/файлов в базу данных все отсутствующие  записи. Для загрузки всех заархивированных логов из папки нужно запустить скрипт loadAllLogInDir.sh указав
параметры  LOGS - путь к папке с заархивированными логами (!!!в пути не нужно указывать завершающий слэш!!!),
параметр tmp_dir - путь к папке куда будут распаковываться логи по окончании работы папка будет очищена.
Для загрузки одного файла нужно запустить скрипт loadOneFile.sh указав параметры PATH - путь к загружаемому файлу
(!!!в пути не нужно указывать завершающий слэш!!!),FNAME - имя загружаемого файла.


### Настройка подключения к базе данных:

Программа записывает логи в базу данных в таблицу PAP_LOG.
Настройки типа, версии базы данных, схемы, логина и пароля производятся
в файле logLoader/conf/hibernate.cfg.xml.
здесь указывается IP адрес порт и SID базы данных логин и пароль
   
    <property name="hibernate.connection.url">jdbc:oracle:thin:@10.10.10.10:1521:TEST</property>
    <property name="hibernate.connection.username">********</property>
    <property name="hibernate.connection.password">********</property>

в этой строке указывается версия Oracle либо другой СУБД

    <property name="hibernate.dialect">org.hibernate.dialect.Oracle9iDialect</property>

в этой строке указывается схема по умолчанию

    <property name="hibernate.default_schema">polifem</property>

### Логирование

Результат работы программы записывается в файл лога путь к нему настраивается в конфигурационном файле:

>logLoader/conf/hibernate.cfg.xml.

В логе отражается время, уровень, имя экземпляра класса и строка кода в котором произошла ошибка, само сообщение
#### Уровни логирования
    TRACE - отладочные сообщения (более подробные чем TRACE)
    DEBUG - отладочные сообщения
    INFO - информация
    WARN - важная информация
    ERROR - ошибка
    FATAL - фатальная ошибка

При каждом последующем уровне количество выводимой информации уменьшается, но ее критичность при этом учеличивается.
Например если в конфигурационном файле уровень логирования указан INFO, то в файл будут записываться все уровни начиная от INFO и до FATAL
