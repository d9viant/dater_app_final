# Dater - A Tinder clone

A tinder clone built using core Java/JavaFX and MySQL with additional APIs in a client/server archicteture. It is structured in a three layer fashion consisting of client/common/server parts. I've Implemented a basic representation of the ELO Matchmaking system, chat system, an open source map library which utilizes BingMaps with the addition of the GeoLoc2Lite library to provide a basic system which tracks the address through the IP address on the network. Implementations of certain Design Patterns and multi-thread concurrency and data caching concepts are present.

Database schema: http://bit.ly/dater_app_db

Java Libraries used:

https://www.sothawo.com/projects/mapjfx/ - JavaFX Project for Map Implementations using only Core Java

https://dev.maxmind.com/geoip/geoip2/geolite2/ - A free geolocation database which points towards an street address using the user IP

https://www.daniel-braun.com/technik/reverse-geocoding-library-for-java/ - A reverse geolocation Openstreetmap API which returns the address details for the LAN and LONG passed attributes

JavaFX libraries used:

https://github.com/controlsfx/controlsfx

http://jfoenix.com/


