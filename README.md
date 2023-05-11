# FindYourWay

# Introduction

Ce projet a été réalisé par Erin LE BOULC'H, Nathan GUETTEVILLE, Benjamin DECKER et Thomas ARROUS. 

Ce fichier est un fichier d'informations destiné à des utilisateurs souhaitant lancer notre programme.

## Configuration requise

Avant tout, assurez vous d'avoir la version 11 de Java et d'avoir accès à la commande `bash`.

La commande `bash` vient généralement avec des OS UNIX. Si vous êtes sur Windows, utilisez Git Bash.

## Instructions

Pour démarrer le jeu, depuis le dossier `2023-ML1a-FindYourWay`, utilisez la commande `sh launch.sh` ou `./launch.sh`.

Après avoir exécuté la commande ci-dessus, une fenêtre appraîtra sur votre écran.

## L'application

Le but de cette application est d'arriver à se déplacer dans un bâtiment de la manière la plus efficace possible et donc ainsi de trouver le "meilleur chemin".

### Démarrage

Au démarrage de l'application, vous trouverez un menu où vous pourrez renseigner plusieurs informations :
    -le batiment dans lequel vous souhaitez obtenir un chemin, que vous pourrez séléctionner à l'aide d'un menu déroulant;
    -la salle de départ et de fin du chemin, à saisir manuellement;
    -un bouton 'GO', sur lequel vous pourrez appuyer une fois vos choix établis;
    -une case à chocher pour indiquer si vous avez la permission d'accès aux ascenseurs.  

Une fois le bouton 'GO' cliqué, la page va changer. Les plans vont apparaitre et vous aurez accès à de nouvelles fonctionnalités.

### Après avoir appuyé sur 'GO' 

Si vous aviez saisi une salle de départ et une salle d'arrivée, alors vous pourrez voir un chemin dessiné sur les plans ainsi que les directions à suivre à droite.
Si vous n'avez saisi qu'une seule salle, vous verrez un ou plusieurs points apparaître au niveau des portes de la salle en question.
Si vous n'avez rien saisi, vous aurez juste accès au plan.

### Autres fonctionnalités

A présent, en haut à droite, vous avez accès à deux boutons où des flèches sont dessinées, vous permettant de naviguer entre les étages. La flèches du haut permet de changer le plan par celui de l'étage supérieur, la flèche du bas, par celui de l'étage inférieur.

En haut, vous avez toujours les mêmes éléments présentés précédemment, c'est à dire :
    -deux emplacements pour saisir les salles de départ et de fin du chemin;
    -une case à cocher pour indiquer si vous avez accès au ascenseur; 
    -un bouton 'GO', sur lequel vous pourrez appuyer une fois vos choix rentrés.

Un nouvel élément fait son apparition : les favoris. En effet en haut à gauche, vous avez trois éléments qui sont apparus:
    -un JComboBox qui permet de sélectionner une salle ou un chemin préalablement ajouté à la liste des favoris;
    -un bouton '+' qui permet d'ajouter aux favoris les salles sélectionnées dans les emplacements de saisie;
    -un bouton '-'qui permet de supprimer le favori actuellement sélectionné.
Lorsque vous sélectionnez un favori, cela remplace automatiquement les emplacements de saisie de salle de départ et d'arrivée. Vous devrez donc sélectionner si vous souhaitez ou non utiliser un ascenseur, et appuyer sur 'GO'.