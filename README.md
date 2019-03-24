# Template Service Application

This is a template service that performs three activities
1. Fetch template by id
2. Fetch template by key
3. Setup all the data

## How to run the application

To run this application, start the sbt shell and type
##### run 9001

This will start the notification service at port 9001. Please hit the /setup endpoint that will create the table in H2 in memory database and insert the data into the database. This is purposely created as it isOS agnostic works across all platforms.

## Tech stack used
Scala, Play framework, H2 database(in memory), Slick, Scalafmt for code hygiene

H2 has been specifically chosen to avoid any external DB installations.

#### Endpoints

There are three endpoints

/setup :- For setting up all the data

/templates/ids/:id :- For fetching templates by id

/templates/keys/:key :- For fetching template by key


#### Tech Debt

Unit test cases missing for a lot of files
