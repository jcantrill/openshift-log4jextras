# Openshift log4j Extras

This project provides a RollingDailyFileAppender that accepts a maxBackupFiles parameter.  Example of use
with Elasticsearch:

```
    appender:
      file:
        type: io.openshift.log4jextras.appender.DailyRollingFileAppender
        file: ${path.logs}/${cluster.name}.log
        datePattern: "'_'yyyy-MM-dd-HH-mm'.log'"
        maxBackupIndex: 8
        append: true
        layout:
          type: pattern
          conversionPattern: "[%d{ISO8601}][%-5p][%-25c] %m%n"
```

The project is a direct download of https://www.codeproject.com/Articles/81462/DailyRollingFileAppender-with-maxBackupIndex
with subtle modifications to the package structure.

