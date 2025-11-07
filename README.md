🎓 Student Management Console App (Java Native)
📖 Description

Application Java console native permettant de gérer des étudiants et leurs notes dans différents cours.
Le projet inclut des opérations CRUD complètes, le calcul des moyennes, la détection des meilleurs et des étudiants en échec, ainsi qu’une persistance des données dans un fichier local (TXT/JSON).

🎯 Objectifs du projet

Gérer une liste d’étudiants avec leurs cours et leurs notes.

Calculer automatiquement les moyennes.

Identifier les meilleurs et les étudiants en échec.

Sauvegarder et charger les données depuis un fichier.

Fournir une interface console interactive simple à utiliser.

🧩 Fonctionnalités principales
👨‍🎓 Gestion des étudiants (CRUD)

➕ Ajouter un étudiant

📋 Afficher tous les étudiants

✏️ Modifier un étudiant

❌ Supprimer un étudiant

📚 Gestion des cours et notes

Ajouter un cours avec une note (0–20) à un étudiant

Supprimer un cours d’un étudiant

📊 Calculs et statistiques

Calculer la moyenne de chaque étudiant

Afficher le meilleur étudiant

Afficher les étudiants en échec (moyenne < 10)

💾 Persistance (fichier)

Sauvegarde automatique après chaque modification

Chargement des données au démarrage

Stockage dans students.txt ou students.json

🖥️ Interface console

Menu interactif utilisant System.out.println et Scanner

Navigation simple et intuitive

# Student Management Console

Une application console pour gérer les étudiants et leurs cours.

---

## Prérequis

- Java JDK 17 ou supérieur installé
- Git (pour cloner le dépôt)
- IDE (optionnel) : IntelliJ, Eclipse, VS Code, etc.

---

## Cloner le dépôt

```bash
git clone https://github.com/<votre-utilisateur>/<nom-du-repo>.git
cd <nom-du-repo>
Compilation et exécution
Option 1 : Depuis un IDE
Ouvrir le projet dans votre IDE favori (IntelliJ, Eclipse, VS Code, etc.)

Compiler le projet (Build Project)

Exécuter la classe principale : Main.java

Option 2 : Depuis la ligne de commande
Compiler le projet :

bash
Copy code
javac -d out/production/student-management-console src/**/*.java
Exécuter le programme :

bash
Copy code
java -cp out/production/student-management-console Main
