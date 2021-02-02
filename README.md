# Projet-Transparency

Bienvenue,

Ce projet est un premier jet de la plateforme attendue par Transparency, il permet déjà de récupérer les données des déclarations des parlementaires (disponible en open source au format xml) et de les stocker en sql. Ensuite il est possible de comparer, lire et annoter ces déclarations. En attendant d'avoir un script pour générer la base de donnée, le fichier dump.sql contient une base de donnée fonctionnelle.

J'utilise : JavaEE, MySQL, JPA, Bootstrap et Glassfish

Si vous utilisez la base de donnée dump.sql, un utilisateur de niveau admin existe déjà (nom d'utilisateur : "a" , mot de passe : "a"), sinon vous devrez en créer un manuellement en base de donnée ou bypasser l'identification. Les utilisateurs de niveau admin peuvent créer/supprimer des utilisateurs et mettre à jour la base de donnée.

