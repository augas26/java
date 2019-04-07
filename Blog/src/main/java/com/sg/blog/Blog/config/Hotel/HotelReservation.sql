drop database if exists Hotel;

create database Hotel;

use Hotel;

create Table Rooms(
  RoomNo INT PRIMARY KEY NOT NULL,
  Floor INT NOT NULL,
  RoomType VARCHAR(20) NOT NULL,
  Rate DECIMAL(4,2) NOT NULL,
  OccupancyLimit INT NOT NULL
  );
  
  create Table Addon(
  addonID INT PRIMARY KEY NOT NULL,
  Description VARCHAR(45) NOT NULL,
  Type_Item Varchar(45) NOT NULL
  );
  
  Create table Amenties(
  AmenId INT primary key not null,
  Name VARCHAR(40) NOT NULL
  );

CREATE TABLE Customer(
  CustId INT PRIMARY KEY NOT NULL,
  Name VARCHAR(20) NOT NULL,
  Phone INT NOT NULL,
  Email VARCHAR(30) NOT NULL,
  NumOfRoomBooked INT NOT NULL,
  Age INT NOT NULL,
  Address VARCHAR(16) NOT NULL,
  City VARCHAR(15) NOT NULL
  );
  
  
  CREATE TABLE Billing(
  billingId INT PRIMARY KEY NOT NULL,
  taxInfo DECIMAL(10, 2) NOT NULL,
  total DECIMAL(10, 2) NOT NULL,
  Details Varchar(45) NOT NULL,
  AddonId INT NOT NULL,
  FOREIGN KEY (AddonId) 
  REFERENCES Addon(AddonId)
  );
  
  Create Table Reservation(
  ReservationId INT primary KEY NOT NULL,
  startDate date not Null,
  endDate date not null,
  CustomerName VARCHAR(45) NOT NULL,
  CustomerAge varchar(45) NOT NULL
  );
  
  Create Table Price(
  priceId INT primary KEY NOT NULL,
  roomCharges DECIMAL(6, 2) NOT NULL,
  Name varchar(45) NOT NULL,
  Season VARCHAR(45) NOT NULL,
  billingId INT NOT NULL,
  foreign key (billingId)
  REFERENCES Billing (billingId)
  );
  
  Create Table CustomerBilling(
  custId INT NOT NULL,
  FOREIGN KEY (custId)
  REFERENCES Customer(custId),
  billingId INT NOT NULL,
  FOREIGN KEY (billingId)
  REFERENCES Billing(billingId)
  );
  
  Create Table Promotion(
  promId INT PRIMARY KEY NOT NULL,
  client_Type VARCHAR(45) NOT NULL,
  discount decimal(10, 2) NOT NULL,
  promStart DATE NOT NULL,
  promEnd DATE NOT NULL,
  codeId varchar(45) NOT NULL
  );
  
  
  Create Table RoomPrice(
  priceId INT NOT NULL,
  FOREIGN KEY (priceId)
  REFERENCES Price(priceId),
 RoomNo INT NOT NULL,
  FOREIGN KEY (RoomNo)
  REFERENCES Rooms(RoomNo));
  
  Create Table RoomAmenties(
  RoomNo INT NOT NULL,
  FOREIGN KEY (RoomNo)
  REFERENCES Rooms(RoomNo),
  AmenId INT NOT NULL,
  FOREIGN KEY (AmenId)
  REFERENCES Amenties(AmenId)
  );
  
  Create Table PromReservation(
  promId INT NOT NULL,
  FOREIGN KEY (promId)
  REFERENCES Promotion(promId),
  ReservationId INT NOT NULL,
  FOREIGN KEY (ReservationId)
  REFERENCEs Reservation(ReservationId)
  );
  
  
  
  
  
  

