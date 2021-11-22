# Bibliotheque
Projet M2 MIAGE SID - Colleen Callonego, Gabriel Couroux

Prérequis :  
- JAVA 11  
- Node.js
- pgAdmin 4  

Dans pgAdmin créer une base de donnée nommée : "biblio"  

Modifier le fichier application.properties (chemin : "Bibliotheque\src\main\resources\application.properties") :   
Modifer les lignes suivantes pour que cela colle avec addresse où se trouve votre postgresql, l'username et le password pour se connecter  
  
"spring.datasource.url=jdbc:postgresql://localhost:5432/biblio  
spring.datasource.username=postgres  
spring.datasource.password=root"   

A la racine du dossier Bibliotheque lancer les commandes suivantes :
- npm install   
- npm install bootstrap-vue

Pour lancer l'application (toujours à la racine du dossier Bibliotheque) lancer les deux commandes dans deux terminaux différents (dans l'ordre):   
- java -jar target/Biblio-0.0.1-SNAPSHOT.jar (première commande)  
- npm run serve (deuxième commande)  
