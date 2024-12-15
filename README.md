# Rental-Property-Management-System 🏠
Welcome to the Rental Property Management System repository! This project serves as the
first individual assignment for COSC2440 Further Programming (S3_2024) course.

## Application Description ℹ️
_____
This repository contains the source code for a Rental Property Management System
developed in Java. The system enables admin to efficiently manage, track, 
and process rental agreement. It includes functionalities such as adding, updating, and deleting rental agreement, 
as well as retrieving model (Tenant, Host, Owner Property, RentalAgreement) details and generating reports.

## Key Features 🔑
___
- Amin can view (retrieve) a list of all Tenant.
- Admin can view (retrieve) a list of all Host.
- Admin can view (retrieve) a list of all Owner.
- Admin can view (retrieve) a list of all Residential/Commercial Property.
- Admin can view (retrieve) a list of all Rental Agreement and can filter it base on owner's name, property's address, status.
- Admin can add a new Rental Agreement.
- Admin can delete an existing Rental Agreement.
- Admin can update an existing Rental Agreement.
- Admin is ability to generate a sortable report of the model based on ID after viewing the model.

## Getting Started ✈️
_____ 
To get started with the Rental Property Management System: 

1. Clone this repository to your local machine.
2. Compile and run the Java source code provided.
3. Follow the prompts in the text-based user interface to interact with the system.
4. Explore the functionalities and provide feedback for improvements.

## Sample Data Files 📁
___
- Payment (${src/data/Payment.csv}$) : Contains Payment ID, Amount, Date, Payment Method.
- Tenant (${src/data/Tenant.csv}$) : Contains Tenant ID, FullName, DateOfBirth, ContactInfo, Payments ID, RentalAgreements ID.
- Host (${src/data/Host.csv}$) : Contains Host ID, FullName, DateOfBirth, ContactInfo, Properties ID, Owners ID, Agreements ID.
- Owner (${src/data/Owner.csv}$) : Contains Owner ID, FullName, DateOfBirth, ContactInfo, Properties ID, Hosts ID, Agreements ID.
- Commercial Property (${src/data/CommercialProperty.csv}$) : Contains Property ID, Address, Pricing, Status, Owner ID, HostIDs, Business Type, Parking Spaces, Square Footage.
- Residential Property (${src/data/ResidentialProperty.csv}$) : Contains Property ID, Address, Pricing, Status, Owner ID, HostIDs, Bedrooms, Garden Available, Pet-Friendly.
- Rental Agreement (${src/data/RentalAgreement.csv}$): Contains Agreement ID, MainTenant ID, SubTenantIDs, PropertyID, Host ID, Owner ID, Period, ContractDate, RentingFee, Status.

## Addition Note ✍️
___
- **View feature in this program mean Admin is ability to retrieve instance(s) that meet the condition.**
- All report that admin had generated will be export as .csv in folder (${src/SaveReport/}$) after the program end.
- The dats for this system limit to 999 instances for each entity. (Please refer to Appendix B in Report pdf)
- This project uses emojis from [Emoji Library](https://emojipedia.org/).
## Author info 👨🏽‍🎓
___
- **Hoang Minh Thang** - [ThangHoang54](https://github.com/ThangHoang54).

## Acknowledgment 🙏
___
A heartfelt appreciation to the course instructors for their valuable guidance 
and resources provided for this assignment.

I appreciate your visit to the Rental Property
Management System repository. Happy coding! 🎉