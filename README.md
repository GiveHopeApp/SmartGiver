#SmartGiver

SmartGiver is a charity donation application that congregates only A and A+ rated charities. Users 
can search for charities by category or by keyword in the search bar. Once a charity is selected, the
user is taken to the charity's profile page which contains its mission statement and location. 
If the user decides to donate, they can select from predetermined amounts or enter a custom 
amount. After clicking the donate button, the user is taken to the donation form where payment 
information and email are entered. Upon submitting, the payment information is tokenized and 
sent to the PandaPay API for payment to the charity. The user receives a confirmation email that
the donation was successful. The application only tracks donation amounts and the EIN of the charity that was 
designated, so no credit card information is saved on the database.
  
##Try it for yourself: 
 
[SmartGiver](http://smartgiver.us)
  
##Project Setup

Clone the project from GitHub as a Spring project. Log in to MySQL as the root user and run the
scripts from the migration file in the sql folder. This will create the database for the 
project and a user. Copy the **application.properties.dist** file and paste it in the same 
directory, but remove the **.dist** extension so that the new file is named **applications.properties**.
Enter your database information into this file then link the database to your IDE and run the application.
After running the application, the tables will be created in the database. Now, run the seeder file in the sql folder
to populate the charities table with data. Finally, obtain a PandaPay API account. The PandaPay API has a publishable
key and secret key. The publishable key must be entered in donation.js file. The publishable key is passed as a 
parameter in the Panda.init command: **Panda.init("publishable_key", "panda_cc_form")**. The secret must be entered 
into the application.properties file as the value for **pandaPay.apiKey**.
   
##Project Organization

The project is organized based on the MVC design pattern. The views are contained in the 
resources folder and organized by the models that the views pertain to. The models and 
controllers are contained inside the java folder. The controllers are located inside the 
controllers folder and each model or feature is given its own controller. The 
GiveHopeController generally contains GetMappings for views that did not fit the other 
controllers. The models are contained inside the models folder and each model has a repository 
that is used to load data from the appropriate table for that model throughout the project. The
services present are used for SpringSecurityConfiguration or loading the the secret PandaPay 
API key.
   
##Further Documentation

[PandaPay API](https://www.pandapay.io/api-reference)
    
