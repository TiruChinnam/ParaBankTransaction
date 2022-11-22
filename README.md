# ParaBankTransaction
Testng-maven-project
This is sample Testng + maven project in Java. There are two groups of tests "Sanity" and "Regression"

Regression tests are depends on Sanity tests

Regression tests will be executed only when Sanity tests are PASSED

### Configurations

Please update these details in `pom.xml` file.

Step 1: Add the following to the <build> -> <plugins> block in your pom.xml:
---------------------------------------------------------------------------
<plugins>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-plugin</artifactId>
<version>2.18.1</version>
<configuration>
<forkCount>0</forkCount>
<suiteXmlFiles>
<suiteXmlFile>testng.xml</suiteXmlFile>
</suiteXmlFiles>
</configuration>
</plugin>
</plugins>
	
---------------------------------------------------
Step 2. Add the following dependencies to pom.xml:
-------------------------------------------------
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-server</artifactId>
    <version>3.141.59</version>
</dependency>

<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.5</version>
</dependency>
		
<dependency>
	<groupId>io.github.bonigarcia</groupId>
	<artifactId>webdrivermanager</artifactId>
	<version>5.3.1</version>
</dependency>
	
Step 3. Create the testng.xml file in the root directory
----------------------------------------------------------	
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite thread-count="2" verbose="10" name="Suite">
	<test verbose="2" name="Tests" preserve-order="true">
		<classes>
			 <class name="com.parabank.tests.ParabankTests" />
		</classes>
	</test> <!-- Test -->
</suite> <!-- Suite -->
	
---------------------------------------------------------------

Run Tests:
To run the tests run the following command
mvn clean install or mvn test
	
------------------------------

### Report Generation:
TestNG Reports are generated in the following path
	
---------------
target/surefire-reports/index.html
	
----------------------------------------------
  
 
