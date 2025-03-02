# Travel Agency Data Model Design  

## Overview  
This document describes the data model for a travel agency application. The model is designed to manage users, their trips, itineraries, preferences, and AI-driven recommendations. It also includes authentication and mapping functionalities.  

## Class Diagram  
The following Mermaid class diagram represents the relationships between the different entities in the system:  

```mermaid  
classDiagram  
    class User {  
        +String id  
        +String name  
        +String email  
        +String phoneNumber  
        +void register()  
        +void login(email, password)  
        +void logout()
        +uploadPhoto(url, description)  
    }  
    
    class Preferences {  
        +String id  
        +Boolean notificationsEnabled  
        +String preferredLanguage  
        +String theme  
        +void updatePreferences()
        +void getPreferences() 
    }  
    
    class Authentication {
        +void login(email, password)  
        +void logout()  
        +void resetPassword(email)  
    }  
    
    class Trip {  
        +String id  
        +String destination  
        +Date startDate  
        +Date endDate  
        +Double budget  
        +void addItineraryItem()  
        +void removeItineraryItem()
        +void updateTripDetails()   
    }  
    
    class ItineraryItem {  
        +String id  
        +String description  
        +Date date  
        +String location  
        +void updateItemDetails()  
    }  
    
    class Image {  
        +String id  
        +String url  
        +String description  
        +void uploadPhoto()  
    }  
    
    class AIRecommendations {  
        +String recommendationId  
        +String tripId  
        +String suggestedActivity  
        +Double rating  
        +void generateRecommendations()
        +void rateRecommendation(Double rating)  
    }  
    
    class Map {
        +String location
        +void showLocation()  
        +void getNearbyPlaces()  
    }  

    User "1" -- "1" Preferences : has  
    User "1" ..> Authentication : uses  
    User "1" -- "*" Trip : owns  
    User "1" -- "*" Photo : uploads  
    User "1" -- "*" AIRecommendations : views  

    Trip "1" -- "*" ItineraryItem : contains  
    Trip "1" -- "*" Photo : stores  
    Trip "1" -- "*" AIRecommendations : gets  
    Map ..> Trip : shows location of 
