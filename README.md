# Databricks Connect Scala Example with ArrayType UDF Issue
This project serves as a demonstration of an issue encountered when using databricks-connect with user-defined functions (UDFs) that utilize ArrayType in Databricks Runtime 14.3 LTS. It illustrates how databricks-connect may not work as expected with UDFs involving complex data types, specifically leading to serialization issues.

## Issue Description
When attempting to use a UDF that operates on ArrayType data, the following exception is encountered:
```bash
Exception in thread "main" org.apache.spark.SparkException: java.io.InvalidClassException: org.apache.spark.sql.types.ArrayType; local class incompatible: stream classdesc serialVersionUID = -8361568169218636135, local class serialVersionUID = 8145303878994261768
```

This example specifically demonstrates the issue with an array manipulation UDF, which fails due to serialization problems between the driver and executor nodes.


## Custom UDF Library: custom_udf_scala_2.12-0.1.0.jar
The `custom_udf_scala_2.12-0.1.0.jar` library included in this project has been compiled from the source code available at the [[custom_udf_scala GitHub repository]](https://github.com/DarkDesire/custom_udf_scala). This JAR file contains the user-defined functions (UDFs) utilized in the project to demonstrate the serialization issue with ArrayType data in Databricks Runtime. For details on the implementation and to contribute or modify the UDFs, please refer to the source repository.

## Databricks Runtime 14.3 LTS

- **Operating System**: Ubuntu 22.04.3 LTS
- **Java Version**: Zulu 8.74.0.17-CA-linux64
- **Scala Version**: 2.12.15
- **Python Version**: 3.10.12
- **R Version**: 4.3.1
- **Delta Lake Version**: 3.1.0
- **Apache Spark Version**: 3.5.0

For more details, refer to the [Databricks documentation](https://docs.databricks.com/en/release-notes/runtime/14.3lts.html).

## Build Environment

- **SBT**: 1.9.8
- **JDK**: Java 1.8.0_402 @ Azul Zulu: 8.76.0.17, Windows
- **Scala Version**: 2.12.15

## Project Structure

* __build.sbt__: SBT build definition that specifies the project's dependencies, including databricks-connect version 14.3.0.
* __Main.scala__: Scala application that attempts to register and use UDFs with ArrayType, leading to a serialization exception.

```bash
.
├── .gitignore
├── .scalafmt.conf
├── README.md
├── build.sbt
├── lib
│   └── custom_udf_scala_2.12-0.1.0.jar
├── project
│   ├── build.properties
│   ├── metals.sbt
├── src
│   └── main
```


## Setup and Configuration
Databricks Configuration: Before running the application, configure your Databricks workspace details in __Main.scala__:

* DATABRICKS_HOST
* DATABRICKS_TOKEN
* DATABRICKS_CLUSTER_ID

<br/>Build: Use SBT to compile the project:
```
sbt compile
```
<br/>Run: Execute the main application:
```
sbt run
```
