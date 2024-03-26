# ecoledirecte-client

> [!NOTE]
> 🇬🇧 For once, this project is written and documented in French. The reason is that the EcoleDirecte service is used exclusively in France (as far as I know).
>
> 🇫🇷 Une fois n'est pas coutume ce projet est écrit et documenté en français. La raison en est que le service EcoleDirecte est utilisé exclusivement en France (pour autant que je sache).

`ecoledirecte-client` est une bibliothèque Java conçue pour faciliter l'interrogation de l'API de la plateforme [www.ecoledirecte.com](https://www.ecoledirecte.com/). Elle est distribuée sous licence MIT.

## Introduction

`ecoledirecte-client` vise à simplifier l'accès à l'API d'EcoleDirecte pour le développement Java. Elle se veut légère et simple d'utilisation. Sa fiabilité peut toutefois varier en fonction des changements dans l'API d'EcoleDirecte.

## Fonctionnalités

- Interrogation simplifiée de l'API d'EcoleDirecte.
- Prise en charge des fonctionnalités de base telles que la récupération des notes, des la vie scolaire, etc.
- Facilité d'intégration dans des projets Java existants.

## Installation

`ecoledirecte-client` est disponible sur Maven Central. Pour l'utiliser dans votre projet vous pouvez l'ajouter comme dépendance dans votre fichier `pom.xml` :

```xml
<dependency>
    <groupId>eu.verdelhan</groupId>
    <artifactId>ecoledirecte-client</artifactId>
    <version>1.0</version>
</dependency>
```

## Utilisation

Voici un exemple d'utilisation de base :

```java
EcoleDirecteClient client = new EcoleDirecteClient("https://api.ecoledirecte.com/v3");
        
// Authentification
LoginResponse login = client.authenticate("xxxx", "yyyy");
System.out.println("Jeton : " + login.getToken());

// Récupération de l'élève 1337
Eleve elv1337 = client.getEleve("1337");
System.out.println("Elève : " + elv1337.getPrenom() + " " + elv1337.getNom() + " (" + elv1337.getDateDeNaissance() + ")");

// Récupération du conseil de la classe 10 du professeur 42 pour la période A001
ConseilDeClasse cc = client.getConseilDeClasse("42", "10", "A001");
String app = cc.getEleves().getFirst().getAppreciationPP().getText();
System.out.println("Appréciation du professeur principal pour le 1er élève : "
        + new String(Base64.getMimeDecoder().decode(app)));
```

## À propos d'EcoleDirecte
EcoleDirecte est une plateforme en ligne utilisée par de nombreuses écoles et établissements éducatifs pour faciliter la communication entre les enseignants, les élèves et les parents. Cette plateforme offre un accès à diverses fonctionnalités telles que la consultation des emplois du temps, la saisie des devoirs, le suivi des notes, les messages entre utilisateurs, et bien plus encore. Bien que pratique, l'API d'EcoleDirecte peut présenter des particularités et des limitations, ce qui peut rendre son intégration et son utilisation via des bibliothèques tierces comme ecoledirecte-client un peu plus complexes.

### Exemples d'appels cURL

```bash

# Login / authentification
curl 'https://api.ecoledirecte.com/v3/login.awp' -H 'Accept: application/json, text/plain, */*' -H 'Content-Type: application/x-www-form-urlencoded' --data $'data={\n "identifiant": "unIdentifiant", "motdepasse": "unMotDePasse"\n}'

# Récupération des élèves de la classe 15
curl 'https://api.ecoledirecte.com/v3/classes/15/eleves.awp?verbe=get&' -H 'Accept: application/json, text/plain, */*' -H 'Content-Type: application/x-www-form-urlencoded' --data $'data={\n "token": "token-d-authentification-obtenu-via-login"\n}'

# Récupération des renseignements de l'élèves 1234
curl 'https://api.ecoledirecte.com/v3/eleves/1234.awp?verbe=get&' -H 'Accept: application/json, text/plain, */*' -H 'Content-Type: application/x-www-form-urlencoded' --data $'data={\n "token": "token-d-authentification-obtenu-via-login"\n}'

# Récupération des notes de l'élève 1234
curl 'https://api.ecoledirecte.com/v3/eleves/1234/notes.awp?verbe=get&' -H 'accept: application/json, text/plain, */*' -H 'content-type: application/x-www-form-urlencoded' --data $'data={\n "token": "token-d-authentification-obtenu-via-login"\n}'
```

## Avertissement

Veuillez noter que cette bibliothèque est fournie "telle quelle" et ne garantit pas un accès complet ou fiable à l'API d'EcoleDirecte. Elle est principalement destinée à un usage personnel et peut nécessiter des ajustements en cas de modifications dans l'API d'EcoleDirecte.

## Contributions

Les contributions sous forme de _pull requests_ sont les bienvenues. Pour les changements importants, veuillez d'abord ouvrir une _issue_ pour discuter de ce que vous aimeriez changer ou ajouter.

### Donations

Adresse Bitcoin : 13BMqpqbzJ62LjMWcPGWrTrdocvGqifdJ3

## Licence
Ce projet est distribué sous la licence MIT. Pour plus d'informations, consultez le fichier LICENSE.

