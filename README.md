-------------------------------------
#### **Project Definition**

ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock consistency is the first priority for their
vision operations.

-------------------------------------

#### **Technologies**

-Java 11

-Spring-Boot-Application

-H2 Database

-Maven

-Docker

-Postman

-------------------------------------

#### **Swagger**

http://localhost/swagger-ui/


Dockerda run olduktan sonra ilk kullanıcının oluşması için /users/createdumpusers methodunun execute edilmesi gerekiyor.
sonrasında  "username":"sema" ve "password":"12345" ile /users/authenticate methodu execute edilip token alınır.



-------------------------------------

#### **Stock Consistency**

Aynı anda birden fazla ürün satın alırken oluşabilecek hata version kullanılarak **Optimistic locking** ile çözülmüştür.

-------------------------------------