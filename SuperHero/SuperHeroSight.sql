DROP DATABASE IF EXISTS SuperHeroSightingsTest;

CREATE DATABASE SuperheroSightingsTest;

use SuperHeroSightingsTest;

Create table SuperHero(
heroId int primary key not null auto_increment,
name varchar(45) not null,
description varchar(45) not null,
superPower varchar(45) not null
);

Create table Organizations(
orgId int primary key auto_increment not null,
Name varchar(45) not null,
description varchar(45) not null,
address varchar(45) not null,
phone varchar(45) not null 

);

Create table HeroOrganization(
orgId INT NOT NULL,
foreign key (orgId)
REFERENCES Organizations(orgId),
heroId int not null,
FOREIGN KEY (heroId)
REFERENCES SuperHero(heroId)
);

Create table Location(
locId int primary Key auto_increment,
name varchar(45) not null,
description varchar(45) not null,
address varchar(45) not null,
latitude decimal(10, 2) not null,
longtitude decimal(10, 2) not null
);

Create table Sighting(
sighId int primary key not null auto_increment,
locId  int not null,
foreign key (locId)
References Location(locId),
Date date not null
);

Create table HeroSight(
sighId int not null,
FOREIGN KEY (sighId)
REFERENCES Sighting(sighId),
heroId int not null,
FOREIGN KEY (heroId)
REFERENCES SuperHero(heroId)
);





