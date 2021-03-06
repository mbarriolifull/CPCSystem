
# Cost Per Click Campaign System

## Project

This is a Java project using maven as abuild automation tool choosing the IntelliJ IDE. <br/>
It cointains all tests using junit framework needed in order to secure that all functionalities required can be done. Those tests are located 
at src/test/java, there is a test class for each class needed to be tested and they fulfill all usecases that could happen. <br/>
In order to pass those tests we either need to add a target for the test classes or inside those classes click on the "play" button. <br/>
Since I did not use the TDD approximation of Outside-In an acceptance test is not created even though since we are only 
developing single campaigns functionalities and we do test all use cases for each campaign type (common functionalities are tested in the Campaign should 
which uses a StandardCampaign).

## Campaign abstract class

Inside entities there is an abstract class called Campaign, this is the base class which all Campaign types inherit. <br/>
Initially this was an Interface defining the necessary methods that a Campaign should be able to perform, due to repetition
of code in all of the different campaigns, I made it an abstract class so its subclasses could inherit the functionality itself.<br/>
In the tests when we compare the expected campaing with the one that is expected to perform the funcionality we arrange
the expectedCampaign after the trigger (the part of the test where we call the method/funcionlity that we are testing)
in order to increase readability, it could also be set at the arrange part (where we declare all variables and instances
nedded to perform the test).

## Data encapsulation

All values used are meant to be encapsulated in it's own class.

## Services

We could have a service that would generate a date and in the tests we would stub it's output in order to
generate the expected date, but since Clicks are passed and are generated at the time of performing 
the click we assume that a Date object will be passed, this could be done with any other data type that would generate a
Timestamp (ex: Timestamp, Instant, LocalDate...). <br/>
As well we could have some type of service to generate IDs in case we needed to assign ID to new campaigns, since we do not 
need that and I assume that those IDs are always given, it is not created.

## Patterns 

#### Builder Pattern for the Click object

Since a Click has a complex constructor with many parameters, I decided to create a Builder for that object in order not
to commit unwanted mistakes at the time of creating the instance. <br/>
If we do not want to leave the constructor for the Object Click on public, we would have 2 options, either make it protected
and put the class ClickBuilder within the same package of Click, or make it Private and implement the ClickBuilder class inside 
the Click class.

#### State Pattern for a campaign state

Even though the different states of the campaign (Active, Paused, Finished) don't do anything at the time of performing 
some actions (ex: When in Paused stated, if the campaing performs a pause() or a charge() it executes nothing) I decided it'll be a good
idea to do it that way since the program could escalate with more states or the existing states could slightly change in
its behaviour.

#### Strategy not really implemented

Even though there is a different behaviour/strategy between the different campaigns at the time of calculating the price
of the clicks and refunding the fake/BOT clicks, since those behaviours are not implemented by the abstract class but
it is done at the sublasses instead, those different strategies are already determined by the type of subclass a Campaign is.

## Repositories (Click repository)

The only repository needed in order to implement this system is the one that stores the Clicks that are charged by a certain
campaign in order to be able to execute the refunds properly. This repository is first declared as an interface since it could
be implemented in various ways (SQL, in-memory), the one that is implemented and used at the moment is the class named "ClickRepository"
which is an in-memory repository that is injected in the campaign via Constructor in order to fulfill the SOLID principle: Dependency
Inversion Principle.

## Entrypoint

Whenever an entrypoint is implemented it will need to create the Campaigns choosing between a Standard, Top or Trial campaign (a Factory Method Pattern could be used)
and injecting a Click Repository via the constructor. <br/>
If we would want to store the several campaigns we will need a campaign repository which would have to implement the following funcionlities
in order to be able to perform campaign funcionalities: <br/>
* Add a campaign to the repository ( **void add(Campaign campaingToAdd)** )
* Retrieve a campaign from the repository based it's ID ( **Campaign retrieveCampaign(ID campaing_Id)**  )

Since currently there is no way to access a campaign ID we would need to implement in the Campaign entitiy a way to see if it has a certain ID, for example
a method given an ID that checks if it has the same one (this option would not break encapsulation), or a getter method for 
the ID field (which would break encapsulation).

## Sprint sections

In this section I will try to explain the parts of the system that belong to each Sprint requirement said in the Technical 
Challenge document. There should be some brief explanation about that as well in the several commits of the repository.

#### Sprint 1

In this part we only knew about one Campaign type which needed to be able to have different **states** (Paused, Active & Finished)
 meaning that no Clicks can be charged when in Paused or Finished state,
 perform **charges** depending on the Click type (premium or non-premium click) costing 0.05€ & 0.01€ respectivelyand changing 
 the state of the campaing to **FINISHED** whenever the budget reaches 0. <br/>
 
 Since there was no need to keep track of the Clicks in order to validate this Sprint the ClickRepository was not yet created.

#### Sprint 2

In this part we needed to check for **duplicated** clicks which were the ones that were performed by the same user in less than
15 seconds of time in between, those clicks that were duplicated could not be charged. To comply with this sprint a ClickRepository 
was needed to be implemented since at the time of charging for a click, first we needed to check at the campaign's past 
charged clicks to check whether or not it was duplicated. <br/>
At this point a way to compare users inside a click entity was also needed to be implemented.

#### Sprint 3

This was the part where we wanted to create several **types of campaigns** so at this moment, an interface is created for the 
several campaigns and the one that we already implemented was named StandardCampaign. <br/> 
Two types of campaing are created additionaly, a **Trial campaign** which would not have a budget since it does not charge any
clicks and it is free, and a **Top campaign** which would slightly differ from the standard one, only in charging prices. <br/>

When all campaigns were done, I could see that there was a lot of code repetition, in order to solve it, instead of an
interface I created an abstract class to implement there all the common code that the campaigns share.


#### Sprint 4

At this point we wanted a way to **refund fake clicks** given by a BOT (certain **user ID**) since a certain **date**. <br/>
We added functionalities at our repository to retrieve all clicks since a certain Date, a way to compare those clicks 
with the userID given and a way for the budget to efectuate refunds. <br/>
Since the different campaigns needed to perform different actions about those refunds, each campaing type implementes it's
own way to calculate those refunds. <br/>

To make a clarification about this sprint. As said in the challenge description a Top/Premium Campaign should refund all clicks
since a specific date if the cost of the clicks is not higher than the 5% of the total budget, I assumed that the total
budget was the one in which the campaign started.