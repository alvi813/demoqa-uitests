**How to run tests**

For launch all tests:
`mvn test -Dtest=`

**How to run tests in Intellij IDEA**

Run -> Edit configurations -> TestNG. JDK settings - VM options:
```
-ea
-Dtest=com.demoqa.LoginToPersonalProfileTest
```