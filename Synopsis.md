## SYNOPSIS

The Objective of this demo project is to test the Pizza Order UI screen. Ability of an User to place pizza orders without any issues.
As a Quality Automation Engineer, Each feature of the UI element is thoroughly tested for its Format, Functionality and Design.
As a Quality Assurant to write selenium code to test each values of drop down , text box to enter valid input, check for mandatory field entries, 
radio button to select/unselect, buttons to click, Validate Title text and pop up message text and Spell check. 
The detailed approach is listed as below. 

#### Approach
1. Created a public git repo and cloned this demo project using git bash cmd "git clone"
2. I have Setup the project using Intellij IDE
3. Opened the index.html file from src/test/resource/files in a browser
4. Copied the url from the browser and updated the url value in src/test/resource/config.properties pointing my local.
5. In src/test/resources updated the config.properties file platform as windows.
6. From command line ran mvn clean install -U -DskipTests
7. I have updated the chromedriver to latest under /src/test/resources/chromedriver/ 
8. Installed the TestNg plugin
9. Used page Object Model to identify ( here locators are already known) all web elements
10. Created packages, classes , Used Encapsulation methods , page Objects for test design
11. Created positive, negative and boundary tests as sample example
11. Used suitable testNg annotations before each tests based on priority.
12. Updated the testng.xml with suite , test case name , tags , inclution and exclusion tests.
13. Ran the tests using testNg and generated the report using Extent tool ( external dependancy )

#### Few sample Test Cases we can create
1. Order Pizza with Cash and Submit
2. Order Pizza with credit and Submit
3. Reset the data entry and validate all fields are properly getting reset
4. Place an Order without entering mandatory fields
5. Boundary test the 'Name' field with 99,100 and more than 100 characters
6. Boundary test the 'Quantity' - maximum pizza one can order with limit to 0-10000
7. Validate data format of each fields

#### Few sample Defects

 1.  Able to select the 'Credit card' and 'Cash on pick up' at same time and able to submit the pizza order
 2.  Able to submit the order by entering the invalid email format
 3.  Able to submit the order by entering the invalid phone number format
 4.  Able to submit the order by entering the Quantity as zero.
 5.  Able to submit the order without selecting the Credit card and Cash on Pickup
 6.  Reset button do not reset the Toppings 1 Drop Down values
 7.  Reset button do not reset the Topppings 2 Drop Down Values
 8.  Once the radio button are selected , it is not possible to uncheck again
 9.  Cost Field should allow only numeric values. It allows to enter any character
 10. Quantity field should allow only Numeric value, it allows to enter any character
 11. Quantity and Cost fields cannot have negative numeric values. It allows negative values

