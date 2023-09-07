# FindMyHome
FindMyHome is a web based application for finding roommates and houses based on preferences set by the users. Preferences like maximum number of roommates, pets policy, rent contribution, dietary habits etc. Users are suggested with people who are similar to their preferences in an order with highest matches at the top. Once two users like each other they are fromed into a group and the users will be unable to like any other users. Users can join existing groups which are suggested to the users based on their prefereneces. The user has an option to see the details of the members in the group and if the user like the group then the user is added to the group once any of the group member approve. Users can post lisitings and view all the listings. Users can form a group to apply for the listings or an user can apply for the listings individually.

## Deplloyed application link
[Our deplloyed application link](http://csci5308vm17.research.cs.dal.ca/)


## Technologies Used

**Front end:** React, Java Script

**Back end:** Spring Boot using Maven Wrapper, Java, Mysql

## Getting Started

### Prerequisites

For the front end we have used reactjs which requeries nodejs and npm.

**Check for if the npm and nodejs are installed**

```bash
npm -v
node -v
```

To install [npm & nodejs](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm).

For the back end we have developed using java version 17 & maven(any version after 3.8 ) wrapper based spring boot application

**Check for if java and maven are installed**

```bash
java -version
mvn -v
```

For installing [java & maven on windows](https://developers.arcgis.com/enterprise-sdk/guide/java/install-java-and-maven/), for [mac](https://www.digitalocean.com/community/tutorials/install-maven-mac-os).

For storing the data we have used MySQL(Any version after 8.0.26).

```bash
  mysql -version
```

For installing [MySQL on windows](https://www.javatpoint.com/how-to-install-mysql), for [mac](https://www.geeksforgeeks.org/how-to-install-mysql-on-macos/).

## Installation

The steps to install the application and run it.

Clone the repository:

```bash
git clone https://github.com/muskanvazirani/FindMyHome
```

Install the necessary dependencies:

For the frontend (React JS), navigate to the frontend directory:

```bash
cd FindMyHome/Frontend/react-group17/
```

and run:

```bash
npm install
```

For the backend, navigate to the backend directory:

```bah
cd FindMyHome/Backend/group17/
```

and run:

```bash
mvn clean install
```

Configure the database:

Create a MySQL database and user with appropriate permissions.
In the application.properties file (located in group17/Backend/group17/src/main/resources), update the database configuration to match your MySQL database:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/<database-name>?useSSL=false&serverTimezone=UTC
spring.datasource.username=<database-username>
spring.datasource.password=<database-password>
```

Start the application:

For the frontend (React JS), navigate to the frontend directory

```bash
cd group17/Frontend/react-group17/
```

and run:

```bash
npm start
```

For the backend (Spring Boot), navigate to the backend directory:

```bash
cd FindMyHome/Backend/group17/
```

and run:

```bash
mvn clean install
```

This will create a jar file inside the target folder, move to the target folder:

```bash
cd FindMyHome/Backend/group17/target/
```

and run the jar file:

```bash
java -jar group17-0.0.1-SNAPSHOT.jar.original
```

else run the commend in backend directory which will start the application directly:

```bash
mvn spring-boot:run
```

Access the application in your local:

The frontend will be available at http://localhost:3000.

The backend API will be available at http://localhost:8080.

## Demo

**User Scenarios:**
A college student who is moving to a new country would want to use the home finder application to search for affordable apartments or roommates near their university. They can open the application and enter property or roommate preferences, such as the number of roommates,pets, rent contribution. The application presents them with a list of available housing options, and they are able to view photos and read details. If they find a property that they are interested in, they are able to like it.

### Login Page:

The application starts with the login page. It also provides an option to Register if the user has not already registered.

<img width="646" alt="LoginPage" src="https://github.com/muskanvazirani/FindMyHome/assets/38426217/0d12e3e8-f9b7-4485-9882-d64ec5426d7b">


### Register:

The registration page provides an option to the user to register by providing basic personal information.

![Register](https://github.com/muskanvazirani/FindMyHome/assets/38426217/65ffe927-3206-41a6-b379-0241e8d4de51)

### Preferences:

The user can add property preferences and the roommate preferences. Property preference refers to the kind of property they are looking for and roommate preferences refer to the basic habits that would prefer in their roommates.

![preferences](https://github.com/muskanvazirani/FindMyHome/assets/38426217/e23298d7-4924-4d39-b810-a3294c51d3f5)

### All Listings

This page shows all the listings as per the preference of the user. To view the images of the listing the user can click on show details.
<img width="1192" alt="AllListings" src="https://github.com/muskanvazirani/FindMyHome/assets/38426217/6b84ee08-738a-494b-b71c-78f0bcbb65b2">

### Profile Page

Profile page provides an option to the user to edit their information and to view their group members, view the users they have liked, create a listing, view groups, edit preferences and view their like requests.


![profilePage](https://github.com/muskanvazirani/FindMyHome/assets/38426217/ce63ebde-8b74-4307-87d2-d32d8ba9b01b)

### Groups

If the user is not in a group then it shows the recommended groups to the user. If the user is already in a group then it won't recommend any group to the user.
<img width="1071" alt="Groups" src="https://github.com/muskanvazirani/FindMyHome/assets/38426217/56515db1-6779-4bb1-b500-9821eecf620e">


### Create Listings

From the profile page the user can create a listing and upload basic details along with the pictures.

![createListing](https://github.com/muskanvazirani/FindMyHome/assets/38426217/0146a91d-1eb1-44ed-9189-657b89e1b788)


### Signout

After signing out the user is re directed to the login and register page.

<img width="646" alt="LoginPage" src="https://github.com/muskanvazirani/FindMyHome/assets/38426217/51d85c08-f3ae-4c98-87c1-abde263f138b">

### Code Coverage
We have covered around 69% of the unit tests cases

![CodeCoverage](https://github.com/muskanvazirani/FindMyHome/assets/38426217/0f2e096d-38bd-4239-acfa-0bf4fe0e3c17)


## Authors

- Dhanesh Walte - B00934223 - dhaneshwalte@dal.ca

- Maulik Gajipara - B0034641 - ml477880@dal.ca

- Muskan Vazirani - B00912626 - muskan@dal.ca

- Naveen Kunapaneni - B00930132 - nv676973@dal.ca

- Sreya Gajjipara - B00922295 - sr228618@dal.ca

