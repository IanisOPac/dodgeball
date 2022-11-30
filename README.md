# Dodgeball
Projet de balle au prisonnier fait en java avec le framework JavaFX. 

Réalisé par :
- Rose Chapelle alias [Rosiflette](https://github.com/Rosiflette)
- Ianis Olivier-Pacaud alias [IanisOPac](https://github.com/IanisOPac)
- Florette Witzel alias [FloretteW](https://github.com/FloretteW)

## Installation
### Prérequis
- openJDK 18+ (guide d'installation à ce [lien](https://www.codejava.net/java-se/install-openjdk-18-on-windows))
- maven (installé par défaut avec IntelliJ et Eclipse)

### Exécution
#### - Sur Eclipse
- ouvrir le projet
- faire un clic-droit sur le fichier `pom.xml`
- cliquer sur "Run As..." puis sur "Maven build"
- ajouter dans le champs "Goals" : `javafx:run`
#### - Sur IntelliJ
- ouvrir le projet
- cliquer sur "Edit Configurations..."
- créer une configuration Maven
- ajouter dans le champs "Command Line" : `javafx:run`

## Une fois en jeu
La fenêtre de jeu devrait être comme suit :  

![dodgeball](https://user-images.githubusercontent.com/37707420/204805194-8b3a33cc-c3e5-4bc5-a4ec-e63cc7afc40a.jpg)
1. *Les joueurs, ceux en noir et blanc sont contrôlés par l'ordinateur*  
2. *Le ballon*  
3. *Bouton permettant de commencer et de mettre en pause le jeu*  
4. *Boutons permettant de créer une nouvelle partie avec plus ou moins de joueurs (max: 10)*  

#### Contrôles
Le **joueur du haut** se déplace avec Q et D et oriente sa visée avec Z et S. Il peut tirer avec la touche ESPACE.  
Résumé : **ZQSD + ESPACE**  

Le **joueur du bas** se déplace avec K et M et oriente sa visée avec O et L. Il peut tirer avec la touche ENTREE.  
Résumé : **OKLM + ENTREE**

#### Condition de victoire
La partie se termine lorsque l'une des deux équipes ne contient plus de joueur en vie. L'affichage du vainqueur se fait dans la console.

#### Lancer une nouvelle partie
Il suffit de cliquer sur le bouton **"+"** ou **"-"**.
