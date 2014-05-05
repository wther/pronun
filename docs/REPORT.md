Abstract
--------

In this thesis I will demonstrate how to use Java Enterprise Edition's Spring Framework to develop a web applications. I will present a working example of an educational website helping people to improve their English pronunciation.


Introduction
------------

Before going into the technical details of the Java toolset I used, I'd like to briefly present the motivations and the concept behind the pronunciation trainer application. 

### Motivations

In fact the biggest motivating factor is that English is becoming more and more the global language. Almost 40% of people in the European Union speak English as a second language, and this percentage is even higher among the young population [1]. The incresing use of the English language globally drives an incresing demand for educational software.

Secondly, even though English pronunciation may be harder to master then vocabulary or grammar, it might be the most important part of oral communication. A bad pronunciation not only gives away the fact that one isn't a native speaker immediately, it's often considered an indicator of a bad English skill all together.

Additionally even native speakers may be interested in learning new accents, they might be able to distinguish the different types of English accents, but they are usually unable to imitate them properly.

### English pronunciations

The English language, its accents and pronunciations is a widely researched field of linguistics. I have found that the best method to improve one's pronunciation is considered to be that one records and replays his own voice while trying to pronuounce a phrase [2]. This is why I'll concentrate the application's main functionality around these use cases.

1. Users can record their voice, and play it back
2. Users can fetch native samples from the server and play them
3. Users can request meta-information on native phrase samples
4. Users can select certain native samples based on the speaker's profile (region of origin, sex, etc.)
5. Users should have feedback on how well they have pronunced a certain phrase

Java Enterprise Edition
-----------------------

The Java programming language is a high-level object-orientated programming language. Java technology has three platforms, Java Standard Edition (SE), Java Enterprise Edition (EE) and Java Micro Edition (ME), each with its own intended environment to be used in. [3]

The Java EE platform provides an API and runtime environment for large-scale, multi-tiered, scalable, reliable, and secure network applications. The Java EE platform is based on the Java SE platform, and therefore it has the benefit of "write once deploy anywhere" application development. Additionally the technology is open-source, and it has received tremendous support and large set of libraries from the open-source community.

### Multi-tier applications

Two-tier, thick-client applications, with separated business logic and data access layers are easier develop but harder to maintain as any change in the business logic has to be deployed to the client. A more maintainable solution, the three-tiered applications is when the server hosts presentation layer [4]. 

Modern web applications have web-enabled their services in the business logic layer. Using the browser's client-side script languages like Javascript takes the burden of rendering from the server, and only the rendering scripts have to be moved to the client once. Client side scripts are not only capable of more sophisticated rendering, like animating or asynchronious communication with the server, but they can take provide better scalability.

### Enterprise Java Beans

Almost any Java object can be treated as bean. Beans are contextual, meaning that they are instatiated and managed by the stateful context that they belong to. 

Beans have a type and a qualifier to distinguish themself from other beans of the same type. Beans have a scope, which determines their lifecyle, singleton beans are application scoped, other beans can be transaction scoped, session scoped, etc. [5]

The main advantage of beans is their ease of use. If they depend on other beans, their dependencies will be automatically injected from the container. This not only elliminates the need to initialize business objects in the code, but helps to decouple the application modules. As dependecies can be injected using their type, the modules will not depend on the implementation of an interface as long as one is found in the bean container.

### Java Persistence API

The Java Persistence API provides a library for persisting POJOs (Plain Old Java Object). By providing the appropriate annotiations for the class and its fields, it takes care of persisting and reading them from relation databases. This doesn't only come with all the advantages of object-relational mapping, but also decouples the business layer from the actual database used.

### Summary

The Java Enterprise Edition already comes with a wide range of tools helping for developing web applications. It extends the Java SE API with useful new libraries helping to develop maintainable applications and forces commonly accepted design patterns to be used. 

In the next chapters I will present the various open-source tools I researched and used for the pronunciation trainer application. 

Maven
-----

The original purpose of Maven was to provide a simple, cross-platform tool for building complex Java applications. It follows the "convention of configuration" principle, meaning that it enforces a certain library and file structure. Maven also offers a shared repository for downloading common jar components, allows using custom plugins and provides various tools for quality control. [5]

### Project descriptor

Each Maven project has to define the Project Object Model (pom) file at the project's root containing the project's meta data. The header of the file contains the modules contained by the parent artifact and its name, version, etc.
    
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    	<modelVersion>4.0.0</modelVersion>
    	<groupId>com.webther.pronun</groupId>
    	<artifactId>pronun-parent</artifactId>
    	<version>1.0-SNAPSHOT</version>
    	<name>Pronunciation Trainer</name>
    	...
    	<modules>
    		<module>pronun-voice</module>
    		<module>pronun-data</module>
    		<module>pronun-webapp</module>
    	</modules>
    	....
    	
When building the project, it first builds all its modules and it tries to resolve its defined dependencies from either the local Maven repository or from one of the configured remote maven repositories. 

	<dependencyManagement>
		<dependencies>
			<!-- Modules -->
			<dependency>
				<groupId>com.webther.pronun.voice</groupId>
				<artifactId>pronun-voice</artifactId>
				<version>${project.version}</version>
			</dependency>
			....
			<dependency>
				<groupId>javax.servlet</groupId>
				<artifactId>jstl</artifactId>
				<version>1.2</version>
			</dependency>
			...
		</dependencies>
	</dependencyManagement>

### Built-in plugins

For functionality not provided by Maven out-of-the box it's necessary to use plugins. For example, the Pronuncation Trainer application depends on the TarsosDSP voice processing library. It can't be expected that its deployed in the local Maven repository on the build machine and it's not in any central Maven repository either. 

In this case I have used the **exec-maven-plugin** to deploy the JAR file in the local repository before the compilation phase. Adding the following plugin definition to the POM file will run the **maven install:install-file** command in the validation phase.

    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.2.1</version>
        <inherited>false</inherited>
        <executions>
        	<execution>
        		<phase>validate</phase>
        		<goals>
        			<goal>exec</goal>
        		</goals>
        		<configuration>
        			<executable>mvn</executable>
        			<arguments>
        				<argument>install:install-file</argument>
        				<argument>-Dfile=${basedir}/lib/TarsosDSP-1.7.jar</argument>
        				<argument>-DgroupId=tarsosdsp</argument>
        				<argument>-DartifactId=tarsosdsp</argument>
        				<argument>-Dversion=1.7.0</argument>
        				<argument>-Dpackaging=jar</argument>
        			</arguments>
        		</configuration>
        	</execution>
        </executions>
    </plugin>

### Custom plugins

As one of the main uses cases of the pronuncation trainer application is that the users should be able to listen to native samples. As the development of the application is already in the already stages I wanted it not to depend on a database, I've implemented a custom Maven plugin which gathers the samples during the packaging phase of the build. 

This plugin requires a file containing comma separated values of the native samples to be gathered, and downloads their native samples and their international phonetaic alphabet (ipa) representation into directory and file specified. As you'll see this directory is specified to be the **webapp** folder, which is going to be added to the web archive assembled by Maven.

    <plugin>
    	<groupId>com.webther.pronun</groupId>
    	<artifactId>pronun-loader-plugin</artifactId>
    	<version>1.0-SNAPSHOT</version>
    	<inherited>false</inherited>
    	<configuration>
    		<directory>${basedir}/pronun-webapp/src/main/webapp/samples/</directory>
    		<csvFile>${basedir}/pronun-webapp/src/main/resources/puzzles.csv</csvFile>
    		<outFile>${basedir}/pronun-webapp/src/main/resources/entityList.csv</outFile>
    	</configuration>
    	<executions>
    		<execution>
    			<phase>package</phase>
    			<goals>
    				<goal>load</goal>
    			</goals>
    		</execution>
    	</executions>
    </plugin>



[1]: http://ec.europa.eu/public_opinion/archives/ebs/ebs_237.en.pdf European Commision: Europeans and Language

[2]: http://reallifeglobal.com/7-tips-to-drastically-improve-your-pronunciation-in-english Real Life: 7 Tips to Drastically Improve Your Pronunciation in English

[3]: http://docs.oracle.com/javaee/: Oracle Coorporation: Java Platform, Enterprise Edition (Java EE) Technical Documentation

[4]: Dhrubojyoti Kayal, Apress, 2008: Pro Java™ EE Spring Patterns

[5]: Peter A. Pilgrim, Packt Publishing, 2013: Java EE 7 Developer Handbook





 








 


