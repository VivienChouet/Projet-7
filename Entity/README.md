#Projet 7

API pour le projet 7 du cursu Develloppeur JAVA de Openclassroom

L'ensemble du projet est disponible sur Github : https://github.com/Slaschh/Projet-7

##Description :
API permettant la gestion des stocks/emprunts de livre d'une bibliotheque.

##Technologie : 
    -   Spring Boot
    -   Hibernate
    -   MySQL
    -   JWT
    -   Lombok
    -   API REST
    
## Endpoint : 

- Batch :
    - /batch/ GET (liste des personne n'ayant pas rendu un livre et n'ayant pas eu de relance )
    - /batch/{id} POST (indique que la reservation N° à recu une relance)
   
- Book : 
    - /book/ GET (liste de tout les livre en db)
    - /book/ POST (ajout d'un livre en db)
    - /book/ PUT (Mise a jour d'un livre)
    - /book/{id} GET (info sur le livre N° id)
 
- Exemplaire :
    - /exemplaire/ GET (liste des exemplaires en db)
    - /exemplaire/ POST (ajout d'un exemplaire en db)
    - /exemplaire/ PUT (mise a jour d'un exemplaire)
    - /exemplaire/{id} GET (Info sur l'exemplaire N°id)
    - /exemplaire/{id} DELETE (supression de l'exemplaire N°id)
    - /exemplaire/book/{id} GET (Liste d'exemplaire du livre N°id)
    
- Reservation :
    - /reservation/ GET (Liste des reservation en db)
    - /reservation/ POST (ajout d'une reservation en db)
    - /reservation/{id} GET (Info sur la reservation N°id)
    - /reservation/{id} PUT (Mise a jour de la reservation N°id)
    - /reservation/{id} DELETE (Suppresion de la reservation N°id)
    - /reservation/myreservation GET (Liste des reservations de l'utilisateur connecté)
    - /reservation/extension/{id} POST (Ajout d'une extension a la location initial)
    - /reservation/return/{id} POST (Indique en db que le l'exmplaire N°id est retourné)
    
- User : 
    - /user/ GET (Liste des utilisateurs en db)
    - /user/ POST (ajout d'un utilisateur en db)
    - /user/ PUT (mise a jour d'un utilisateur)
    - /user/{id} GET (info sur l'utilisateur N°id)
    - /user/{id} DELETE (supression de l'utilisateur N°id)
    - /user/loggin POST (demande de connexion)
    - /user/token GET (demande du token de l'utilisateur connecté)
    
 

##Amelioration a mettre en place : 

Systeme de recherche dans la db pour les livres

##Bug :
