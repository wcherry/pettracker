## Overview
You have been contracted by a new, shoe-string start-up for up to three hours of work in which you are expected to provide:

1. An architecture design of service(s). Documentation with visual examples encouraged.
2. A mock API service prototype. Include all source code, and it should be something that could be run/interacted with locally by start-up engineering reps.

Your work should be delivered as a shared GitHub repo.
The service(s) will support a mobile app, which the founders assure you will go viral within a month of its initial launch.
The service(s) will provide the following functionality:

1. Storing location data from cellular transmitters attached to pet collars and collected by units installed on cell towers.
2. Returning historical location data for a given pet (basic users can see the last 24 hours while premium users can see the last 30 days of data).
3. Searching for and returning contact information for opted-in owners of other pets within a 5-mile radius. [Note: This is premium functionality.]

You will provide an architecture diagram for the service(s) and related infrastructure. The founders are not available for consultation; list significant assumptions you make in your design (including work to be done in different phases of development).
The service prototype will use canned location data and will be used for initial integration as the mobile app is developed. Again, list significant assumptions made in creating the prototype.

## Assumptions
Owners can have multiple pets
Each each reciever transmits pets location once every 5 minutes
Total unique pets: 30,000,000
Total RPS: 100,000
Min geo res: 1/10th second of arc (3m)

## Data to store
OWNER: ownerid, zone, fname, lname, email, contact_phone, username, premium?
PET: petid, ownerid, petname, pet-type, share-opt-in?, collar-id
LOC: ownerid, petid, lat, long, time, zone

## APIs
GET:/api/owner/:ownerid 

GET:/api/owner/:ownerid/pet/:petid

GET:/api/owner/:ownerid/pet/:petid/location

GET:/api/owner/:ownerid/pet/:petid/location/history

POST:/api/location - Used by the service to create a new pet location

GET: /api/owner/:ownerid/nearby?distance= -> Not implemented yet 

## Prototype
To prep the database:
`docker run -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 mysql`

Load all the data files listed in src/main/resources/db/migrate
   
To execute the server:
`gradlew clean build bootRun`

Latest Location:
`curl "http://localhost:8080/api/owner/1/pet/1/location"`

Location History:
`curl "http://localhost:8080/api/owner/1/pet/1/location/history"`

Owner Info: 
`curl "http://localhost:8080/api/owner/1`

Insert a new location record for a pets collar:
`curl "http://localhost:8080/api/location" -H "Content-Type: application/json" --data '{"collarId": "123315523422", "longitude": -127.0, "latitude": 38.639095, "elevation"\n: 0.0, "when": 1596422882426}'`


## Resources Used
1. https://start.spring.io/
2. Docker
3. MySQL
4. Java8
5. Spring4


