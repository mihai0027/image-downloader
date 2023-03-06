This is a simple Spring Boot application, build in Maven. The purpose of application is by
providing an url and a file path on your computer, saves all .jpeg/.jpg and .png images to your path.

The application follows Model-View-Controller design pattern

## Particularities:

- As it's a simple project, were not implemented tests or DTO
- For HTML parsing, it was used open source library - JSOUP
- For this project was used Java 17
- For demonstration purposes, was used in memory database H2, but it can be
  replaced easily with others RDMS.

## Instructions:

1. To save png/jpeg images to your computer, go to:

   ### <http://localhost:5555/download> and make a POST request in a JSON format, as in this example:

   {
   "strURL": "https://www.google.es/",
   "filePath": "your_file_path"
   }

2. To check the results, go to:
   ### <http://localhost:5555/h2-console/>

Use folowwing credentials:
JDBC URL: "jdbc:h2:mem:img-data"
User Name: "sa"
Password: "pass"

To check these setting or modify them, go to src/main/resources/application.properties
