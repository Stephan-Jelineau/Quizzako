# Expression des Besoins - Projet Quizzako

## Sommaire
1. [Objectif du document](#1-objectif-du-document)
2. [Présentation](#2-présentation)
   1. [Présentation du projet](#21-présentation-du-projet)
   2. [Situation actuelle](#22-situation-actuelle)
   3. [Contraintes](#23-contraintes)
3. [Acteurs](#3-acteurs)
   1. [User](#31-user)
   2. [Student](#32-student)
   3. [Teacher](#33-teacher)
   4. [Admin](#34-admin)
   4. [Unknown](#35-unknown)
   5. [Résumé des Acteurs](#36-résumé-des-acteurs)
4. [Cas d'utilisation](#4-cas-dutilisation)
   1. [Groupe 1 : Gestion des comptes](#41-groupe-1--gestion-des-comptes)
      1. [Cas d'utilisation « Create Account »](#411-cas-dutilisation--create-account)
      2. [Cas d'utilisation « Log In »](#412-cas-dutilisation--log-in)
      3. [Cas d'utilisation « Log Out »](#413-cas-dutilisation--log-out)
      4. [Cas d'utilisation « Request Teacher Role »](#414-cas-dutilisation--request-teacher-role)
      5. [Cas d'utilisation « Grant Teacher Role »](#415-cas-dutilisation--grant-teacher-role)
   2. [Groupe 2 : Gestion des questionnaires](#42-groupe-2--gestion-des-questionnaires)
      1. [Cas d'utilisation « Choose category »](#421-cas-dutilisation--choose-category)
      2. [Cas d'utilisation « Answer question series »](#422-cas-dutilisation--answer-question-series)
      3. [Cas d'utilisation « Create questionnaire »](#423-cas-dutilisation--create-questionnaire)
      4. [Cas d'utilisation « Edit questionnaire »](#424-cas-dutilisation--edit-questionnaire)
      5. [Cas d'utilisation « Select questionnaire »](#425-cas-dutilisation--select-questionnaire)
      6. [Cas d'utilisation « Assign questionnaire »](#426-cas-dutilisation--assign-questionnaire)
      7. [Cas d'utilisation « Complete assigned questionnaire »](#427-cas-dutilisation--complete-assigned-questionnaire)
      8. [Cas d'utilisation « Consult questionnaire results »](#428-cas-dutilisation--consult-questionnaire-results)
   3. [Groupe 3 : Gestion des Quizz](#43-groupe-3--gestion-de-la-progression)
      1. [Cas d'utilisation « Consult category scores »](#431-cas-dutilisation--consult-category-scores)
      2. [Cas d'utilisation « Consult overall score»](#432-cas-dutilisation--consult-overall-score)
      3. [Cas d'utilisation « Consult assigned questionnaire scores »](#433-cas-dutilisation--consult-assigned-questionnaire-scores)
   3. [Groupe 4 : Gestion des Cohortes](#44-groupe-4--gestion-des-cohortes)
      1. [Cas d'utilisation « Create cohort »](#441-cas-dutilisation--create-cohort)
      2. [Cas d'utilisation « Answer question series »](#442-cas-dutilisation--answer-question-series)
      3. [Cas d'utilisation « Create questionnaire »](#443-cas-dutilisation--create-questionnaire)
      4. [Cas d'utilisation « Edit questionnaire »](#444-cas-dutilisation--edit-questionnaire)
      5. [Cas d'utilisation « Select questionnaire »](#445-cas-dutilisation--select-questionnaire)
      6. [Cas d'utilisation « Assign questionnaire »](#446-cas-dutilisation--assign-questionnaire)
      7. [Cas d'utilisation « Complete assigned questionnaire »](#447-cas-dutilisation--complete-assigned-questionnaire)

---
### 1. Objectif du document
Ce document présente l'expression des besoins du projet Quizzako en utilisant le langage de modélisation UML.  
Les acteurs et les interactions du système sont représentés à l'aide de diagrammes de cas d'utilisation.

### 2. Présentation


<br>

##### 2.1. Présentation du projet
Quizzako est une application web de jeu de quizz qui offre différentes fonctionnalités selon les types d'utilisateurs.  
Les questions sont seulement des questions à choix multiples.
Les utilisateurs peuvent créer des comptes, s'identifier, choisir des catégories de questions et répondre à des séries de questions.  
Le système sauvegarde les scores et la progression des utilisateurs identifiés.  
Il existe deux autres types d'utilisateurs principaux : STUDENT et TEACHER.  
Les TEACHER peuvent créer des questions, assigner des étudiants individuellement ou à travers une cohorte à des questionnaires et consulter les résultats de leurs STUDENT.
Aussi, un ADMIN permet de gérér les comptes des autres rôles.

##### 2.2. Situation actuelle
Rien n'existe

##### 2.3. Les contraintes
Application web

### 3. Acteurs

<br>

##### 3.1. User
Un utilisateur qui peut créer un compte, s'identifier, choisir des catégories de questions et répondre à des questionnaires, la progression est enregistrée.

##### 3.2. Student
Un utilisateur qui a le rôle de USER et qui permet également de recevoir des questionnaires d'un TEACHER directement ou via une cohort auquel il fait partie, partageant ses résultats avec le TEACHER.

##### 3.3. Teacher
Un utilisateur qui a le rôle de USER et qui peut créer des questions, assigner des étudiants à des questionnaires individuellement ou via des cohorts créées par ses soins puis consulter les résultats de ses étudiants. 
Le rôle de Teacher est attribué par l'Admin après la création de son compte User via une "demande de rôle Teacher".

##### 3.4. Admin
Un admin qui a les plein privilèges. Peut accepter une demande de rôle Teacher, modifier, supprimer un compte Teacher, User ou Student .

##### 3.5. Unknown
Un utilisateur non identifié qui peut effectuer des actions similaires à un USER, mais sans sauvegarde de progression.

##### 3.6. Résumé des Acteurs



@startuml
skin rose
:User:
:Student:
:Teacher:
:Admin:
:Unknown:
@enduml



### 4. Cas d'utilisation
<br>

#### 4.1. Groupe 1 : Gestion des comptes



@startuml
skin rose
:User: -> (Log Out)
:User: -> (Log In)
:User: -> (Create Account)
:User: -> (Request Teacher Role)
(Create Account) <|-- (Log In) : <<include>>
(Log In) <|-- (Log Out) : <<include>>
:Admin: -> (Grant Teacher Role)
(Request Teacher Role) <|-- (Grant Teacher Role) : <<include>>
:Admin: -down-> (Modify existing account)
:Admin: -down-> (Delete existing account)
@enduml



<br>
<br>
<br>

#### 4.1.1. Cas d'utilisation « Create Account »
<br>

##### Résumé
L'utilisateur créé un compte sur l'application.

##### Acteurs
User

##### Pré-conditions
L'utilisateur n'est pas connecté
L'utilisateur ne possède pas de compte associé à son email

##### Description
L'utilisateur saisit
     - un identifiant unique (adresse mail)
     - un mot de passe
     - s'il est Student ou non (checkbox)
     - prénom
     - nom
     
Le système vérifie :
   - l'unicité de l'identifiant
   - la validité du mot de passe

##### Post-conditions
Le compte est créé, et l'utilisateur peut se connecter.

##### Exceptions
La création du compte échoue (par exemple, base de données non disponible) : le système doit être remis dans l'état antérieur.

#### 4.1.2. Cas d'utilisation « Log In »
<br>

##### Résumé
L'utilisateur s'identifie sur l'application.

##### Acteurs
User

##### Pré-conditions
L'utilisateur n'est pas connecté
L'utilisateur a déjà créé un compte

##### Description
L'utilisateur entre son identifiant et son mot de passe.
Le système vérifie la validité de l'identifiant et du mot de passe.
Si les informations sont valides, l'utilisateur est connecté.

##### Post-conditions
L'utilisateur est connecté, avec les droits associés à son rôle.

#### 4.1.3. Cas d'utilisation « Log Out »

##### Résumé
L'utilisateur se déconnecte de l'application.

##### Acteurs
User

##### Pré-conditions
L'utilisateur est connecté

##### Description
L'utilisateur choisit l'option de déconnexion.


##### Post-conditions
Le système termine la session de l'utilisateur.
L'utilisateur est déconnecté et redirigé vers la page d'accueil.
<br>
#### 4.1.4. Cas d'utilisation « Request Teacher Role »

##### Résumé
L'utilisateur demande qu'on lui attribue le rôle de TEACHER

##### Acteurs
User

##### Pré-conditions
L'utilisateur est connecté
L'utilisateur n'est pas un STUDENT

##### Description
L'utilisateur clique sur le bouton "Request Teacher Role"
La requête est envoyée à l'Admin en charge

##### Post-conditions
L'utilisateur attend la validation de l'ADMIN, il peut jouir de ses privilèges USER en attendant

#### 4.1.5. Cas d'utilisation « Grant Teacher Role »

##### Résumé
L'administrateur accepte une demande de rôle TEACHER à un USER

##### Acteurs
Admin

##### Pré-conditions
Une demande de rôle est en attente de validation

##### Description
L'utilisateur clique sur le bouton "Grant Teacher Role" de la demande active après avoir vérifié les informations du compte demandeur
Le compte USER concerné reçoit les privilèges du rôle TEACHER

##### Post-conditions
Le compte est désormais visible comme TEACHER par l'admin.
Le TEACHER bénéficie des privilèges de son rôle.

#### 4.2. Groupe 2 : Gestion des questionnaires
<br>



@startuml
skin rose
:Unknown: -> (Answer Question Series)
:Unknown: -> (Choose Category)
:Unknown: <|-- :User:
:User: <|-- :Student:
:User: <|-- :Teacher:
:Teacher: -> (Create Questionnaire)
:Teacher: -> (Edit Questionnaire)
:Teacher: -> (Select Questionnaire)
:Teacher: -> (Assign Questionnaire)
:Teacher: -> (Consult Questionnaire Results)
:Student: -> (Complete Assigned Questionnaire)
(Choose Category) <|-- (Answer Question Series) : <<include>>
(Create Questionnaire) <|-- (Assign Questionnaire) : <<include>>
(Select Questionnaire) <|-- (Edit Questionnaire) : <<include>>
(Create Questionnaire) <|-- (Select Questionnaire) : <<include>>
(Assign Questionnaire) <|-- (Complete Assigned Questionnaire) : <<include>>
(Complete Assigned Questionnaire) <|-- (Consult Questionnaire Results) : <<include>>
(Select Questionnaire) <|-- (Create Questionnaire) : <<extend>>
@enduml



<br>
<br>
<br>

#### 4.2.1. Cas d'utilisation « Choose Category »

##### Résumé
L'utilisateur choisit une catégorie de questions.

##### Acteurs
User

##### Pré-conditions
L'utilisateur est connecté

##### Description
L'utilisateur navigue à travers les catégories disponibles.
L'utilisateur choisit une catégorie spécifique.

##### Post-conditions  
Les questions de la catégorie sélectionnée s'affiche

#### 4.2.2. Cas d'utilisation « Answer Question Series »

##### Résumé  
L'utilisateur répond à une série de questions.

##### Acteurs
User

##### Pré-conditions
L'utilisateur est connecté

##### Description
L'utilisateur répond successivement aux questions présentées.

##### Post-conditions  
Les réponses de l'utilisateur sont enregistrées.

#### 4.2.3. Cas d'utilisation « Create Questionnaire »

##### Résumé  
Un Teacher crée un nouveau questionnaire.

##### Acteurs
Teacher

##### Pré-conditions  
Le Teacher est connecté

##### Description
Le Teacher accède à la fonction de création de questionnaire.
Le Teacher saisit les détails du questionnaire, tels que les questions et les options de réponses disponible.


##### Déroulement Alternatif
Le Teacher sélectionne un questionnaire existant
Le Teacher utilise la fonctionnalité de création d'un nouveau questionnaire à partir d'un existant
Ce nouveau questionnaire est une copie de l'existant  

##### Post-conditions  
Le système enregistre le nouveau questionnaire.
Le nouveau questionnaire est disponible pour l'assignation.

#### 4.2.4. Cas d'utilisation « Edit Questionnaire »

##### Résumé  
Un Teacher modifie un questionnaire existant.

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté
Un questionnaire est sélectionné, ses détails s'affichent.


##### Description
Le Teacher accède à la fonction d'édition du questionnaire.
Le Teacher saisit les modifications.

##### Post-conditions  
Le système enregistre le questionnaire modifié.

#### 4.2.5. Cas d'utilisation « Select Questionnaire »

##### Résumé  
Un Teacher sélectionne un questionnaire existant.

##### Acteurs
Teacher

##### Pré-conditions  
Le Teacher est connecté
Le questionnaire existe déjà

##### Description
Le Teacher sélectionne le questionnaire existant en vue d'une action


##### Post-conditions  
Le questionnaire est sélectionné

#### 4.2.6 Cas d'utilisation « Assign Questionnaire »

##### Résumé  
Le Teacher assigne un questionnaire à un Student.

##### Acteurs  
Teacher

##### Pré-conditions
Le Teacher est connecté
Un questionnaire est déjà créé

##### Description
Le Teacher accède à la fonction d'assignation de questionnaire.
Le Teacher choisit un Student et un questionnaire à assigner.


##### Post-conditions  
Le questionnaire est assigné au Student sélectionné.

#### 4.2.7. Cas d'utilisation « Complete Assigned Questionnaire »

##### Résumé
Un Student complète un questionnaire qui lui a été assigné.

##### Acteurs  
Student

##### Pré-conditions
La Student est connecté
Un questionnaire lui a été assigné

##### Description
Le Student accède à la section des questionnaires assignés.
Le Student répond successivement aux questions du questionnaire assigné.

##### Post-conditions  
Les résultats sont enregistrées dans le profil de l'étudiant.
Les résultats sont envoyés au Teacher responsable du questionnaire.

#### 4.2.8. Cas d'utilisation « Consult Questionnaire Results »

##### Résumé  
Un Teacher consulte les résultats d'un questionnaire donnés par un étudiant.

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher léger est connecté
Un questionnaire a été complété par un Student

##### Description
Le Teacher accède à la section des résultats de questionnaire.
Le Teacher choisit un Student et le questionnaire associé.

##### Post-conditions  
Les résultats du questionnaire sont consultés par le Teacher.

### 4.3. Groupe 3 : Gestion de la Progression
<br>



@startuml
skin rose
:User: --> (Consult Category Scores)
:User: --> (Consult Overall Score)
:Student: --> (Consult Assigned Question Scores)
:User: <|-- :Student:
@enduml



<br>
<br>
<br>

#### 4.3.1. Cas d'utilisation « Consult Category Scores »

##### Résumé
L'utilisateur consulte le score par catégorie de question.

#####Acteurs
User

##### Pré-conditions
L'utilisateur est connecté

##### Description
L'utilisateur accède à la fonction de consultation de score par catégorie.
Le système affiche le score de l'utilisateur pour chaque catégorie de question.

##### Post-conditions
Affichage du score par catégorie

#### 4.3.2. Cas d'utilisation « Consult Overall Score »

##### Résumé
L'utilisateur consulte le score global de son compte.

##### Acteurs
User

##### Pré-conditions
L'utilisateur est connecté

##### Description
L'utilisateur accède à la fonction de consultation du score global.
Le système affiche le score global de l'utilisateur sur l'ensemble des questionnaires.

##### Post-conditions
Affichage du score global

#### 4.3.3. Cas d'utilisation « Consult Assigned Question Scores »

##### Résumé
Le Student consulte le score des questions qui lui ont été assignées.

##### Acteurs
Student

##### Pré-conditions
Le Student est connecté
Au moins un questionnaire lui a été assigné

##### Description

L'étudiant accède à la fonction de consultation des scores des questionnaires assignés.
Le système affiche le score de l'étudiant pour chaque questionnaire assignée.

##### Post-conditions
Affichage du score par questionnaire

### 4.4 Groupe 4 : Gestion des Cohortes
<br>



@startuml
skin rose
:Teacher: -up-> (Create cohort)
:Teacher: -up-> (Show cohort details)
:Teacher: -right-> (Edit cohort name)
:Teacher: -right-> (Delete cohort)
:Teacher: -down-> (Add Student in cohort)
:Teacher: -down-> (Remove Student from cohort)
:Teacher: -left-> (Assign questionnaire to cohort)
(Create cohort) <|-- (Add Student in cohort) : <<include>>
(Create cohort) <|-- (Remove Student from cohort) : <<include>>
(Create cohort) <|-- (Show cohort details) : <<include>>
(Create cohort) <|-- (Edit cohort name) : <<include>>
(Create cohort) <|-- (Delete cohort) : <<include>>
(Create cohort) <|-- (Assign questionnaire to cohort) : <<include>>
@enduml



<br>
<br>
<br>

#### 4.4.1. Cas d'utilisation « Create cohort »

##### Résumé
Un Teacher crée une cohorte pour regrouper des étudiants.

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté à son compte

##### Description
Le Teacher accède à la fonction de création de cohorte.
Le Teacher saisit le nom de la cohorte.

##### Post-conditions
Une nouvelle cohorte est créée et disponible pour l'ajout de Student

#### 4.4.2. Cas d'utilisation « Show cohort details »

##### Résumé
Un enseignant consulte les détails d'une cohorte existante

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté à son compte
Une cohorte existe

##### Description
L'enseignant accède à la fonction de consultation des détails de la cohorte 
et affiche les informations détaillées de la cohorte, tel que la liste des étudiants et les questionnaires assignés.

##### Post-conditions
Affichage des détails

#### 4.4.3 Cas d'utilisation « Edit cohort name »

##### Résumé
Un enseignant modifie le nom d'une cohorte existante

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté à son compte
Une cohorte existe

##### Description
Le Teacher accède à la fonction de modification du nom de la cohorte.
Le Teacher saisit le nouveau nom de la cohorte

##### Post-conditions
Le nom de la cohorte est modifié

#### 4.4.4. Cas d'utilisation « Delete cohort »

##### Résumé
Un Teacher supprime une cohorte existante

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté à son compte
Une cohorte existe

##### Description
Le Teacher accède à la fonction de suppression de la cohorte.
Le Teacher confirme la suppression de la cohorte

##### Post-conditions
La cohorte est supprimée

#### 4.4.5. Cas d'utilisation « Add Student in cohort »

##### Résumé
Un Teacher ajoute un Student à une cohorte existante

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté à son compte
Une cohorte existe

##### Description
Le Teacher accède à la fonction d'ajout d'étudiant à la cohorte.
Le Teacher sélectionne un étudiant à ajouter

##### Post-conditions
L'étudiant est ajouté à la cohorte

#### 4.4.6. Cas d'utilisation « Remove Student from cohort »

##### Résumé
Un Teacher retire un Student d'une cohorte existante

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté à son compte
Le Student fait partie de la cohorte sélectionnée

##### Description
Le Teacher accède à la fonction de suppression de Student de la cohorte.
Le Teacher sélectionne un Student à retirer.

##### Post-conditions
Le Student est retiré de la cohorte

#### 4.4.7. Cas d'utilisation « Assign questionnaire to cohort »

##### Résumé
Un Teacher assigne un questionnaire à une cohorte existante

##### Acteurs
Teacher

##### Pré-conditions
Le Teacher est connecté à son compte
Une cohorte existe
Des questionnaires sont disponibles

##### Description
Le Teacher accède à la fonction d'assignation de questionnaire à la cohorte.
Le Teacher sélectionne un questionnaire à assigner

##### Post-conditions
Le questionnaire est assigné à tous les Student de la cohorte, visible dans leurs comptes respectifs
