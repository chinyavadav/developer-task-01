### Developer Task 1 

* You will receive instructions from Cassava Smartech on what's required.

## Issue 1: MobileNumberUtils LOGGER not static

MobileNumberUtils LOGGER not static hence it cannot be called from a static method

```
/Users/chinyavadav/Documents/zss/developer-task-01/econet-utils/src/main/java/com/econetwireless/utils/formatters/MobileNumberUtils.java:36:13
java: non-static variable LOGGER cannot be referenced from a static context
```

### Issue 2: Log4j2 Misconfiguration

Log4j2 in the intelligent-network-api & electronic-payments-api has a misconfigured log path to save logs. The current path looks to save in the computer's root folder instead of classpath.
Also there is no root logger defined for Log4j2

```
2021-03-27 13:12:43,859 main WARN No Root logger was configured, creating default ERROR-level Root logger with Console appender
2021-03-27 13:12:44,028 main ERROR Unable to create file /data/logs/intelligent-network-api/default/application.log java.io.IOException: No such file or directory
2021-03-27 13:16:24,531 main WARN No Root logger was configured, creating default ERROR-level Root logger with Console appender
```

### Issue 3: COMPILATION ERROR: Cannot find PreInsert

PreInsert annotation cannot be found in com.econetwireless.epay.domain.SubscriberRequest

```
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /Users/chinyavadav/Documents/zss/developer-task-01/electronic-payments-domain/src/main/java/com/econetwireless/epay/domain/SubscriberRequest.java:[42,6] cannot find symbol
  symbol:   class PreInsert
  location: class com.econetwireless.epay.domain.SubscriberRequest
```

## Issue 4: Compilation failure: Compilation failure: wrong use of super && this keywords

```java
this(super);
```

## Issue 5: JPA persist & update methods not found

SubscriberRequestDao extends JPA repository which in turn has no method persist or update defined

## Issue 6: Error Executing JPARepository method findByPartnerCode

Mismatching Entity name and reference in @NamedQuery 
 
 ```
 "select r from request r where r.partnerCode = :partnerCode order by r.dateCreated desc ")
13:53:40.216 [main] ERROR org.hibernate.internal.SessionFactoryImpl - HHH000177: Error in named query: SubscriberRequest.findByPartnerCode
            org.hibernate.hql.internal.ast.QuerySyntaxException: Request is not mapped [select r from Request r where r.partnerCode = :partnerCode order by r.dateCreated desc ]
```





