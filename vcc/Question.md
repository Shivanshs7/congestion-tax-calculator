# As it mentioned that I have to spend 4hrs so keeping time Constraint,I have focused on 
implementing working bussiness logic

## If I will get Additional time I will do following things
1.  Make it Configurable for different city and different tax rules on Vehicle Type, Tax Amount, Holidays and Time.
2.	Return response from controller  for Vehicle Tax Exemption or on Holiday.
3.  Junit Implementation

### Focus Areas depending on the Time given for the assignment 
    a) Requirement Understanding 
    b) Current Code Understanding
    c) Code Changes and Spring Boot Dependencies
        •	Add an entry point for HTTP calls (consider using Spring Boot).
        •	Define input parameters (vehicle type, date with time).
    d) Testing and Fixing Bugs

#### Test Cases
    a) Maximum tax amount per day per vehicle (60 SEK).
    b) Vehicle congestion tax calculation during different times of the day.
    b) Exemptions are correctly applied for emergency vehicles, buses, diplomats, motorcycles, military vehicles, and foreign vehicles.
    c) Single charge Rule for a vehicle passing multiple tolling stations within 60 minutes.
    d) Holiday congestion tax is not charged on weekends, public holidays, days before a public holiday, and during July.

#### Question: 
    a) Under single charge rule for vehicle passing multiple tolling stations within 60 minutes, what business logic would be applicable to calculate the single charge?
       Will it be the maximum charge if the vehicle passes between two slots of 30 mins that have different charges?
    b) For valid user input and facilitate better response list of supported vehicle names needed.We have Tax Exemptions vechile name only.

##### Postman details 
1. URL http://localhost:8080/congestionTaxCalculatorService
2. Sample Request {
   "vehicle": "Busses",
   "timeList": ["2013-02-08 06:00:00", "2013-02-08 06:39:00", "2013-02-08 07:00:00"]
   }
3. Method POST
