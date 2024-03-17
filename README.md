# ecoledirecte-client

`ecoledirecte-client` est une petite bibliothèque Java conçue pour faciliter l'interrogation de l'API du site [www.ecoledirecte.com](https://www.ecoledirecte.com/). Ce projet est distribué sous licence MIT.

## Introduction

Cette bibliothèque vise à simplifier l'accès à l'API d'École Directe, bien que celle-ci ne suive pas strictement les bonnes pratiques habituelles des services en ligne. Elle est conçue pour être légère et simple d'utilisation, bien que sa fiabilité puisse varier en fonction des changements dans l'API d'École Directe.

## Fonctionnalités

- Interrogation simplifiée de l'API d'École Directe.
- Prise en charge des fonctionnalités de base telles que la récupération des notes, des devoirs, etc.
- Facilité d'intégration dans des projets Java existants.

## Installation

Pour utiliser `ecoledirecte-client` dans votre projet, vous pouvez l'ajouter comme dépendance dans votre fichier `pom.xml` Maven :

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>ecoledirecte-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Utilisation

Voici un exemple d'utilisation de base :

```java
# TODO
```

## À propos d'École Directe
École Directe est une plateforme en ligne utilisée par de nombreuses écoles et établissements éducatifs pour faciliter la communication entre les enseignants, les élèves et les parents. Cette plateforme offre un accès à diverses fonctionnalités telles que la consultation des emplois du temps, la saisie des devoirs, le suivi des notes, les messages entre utilisateurs, et bien plus encore. Bien que pratique, l'API d'École Directe peut présenter des particularités et des limitations, ce qui peut rendre son intégration et son utilisation via des bibliothèques tierces comme ecoledirecte-client un peu plus complexes.

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
Veuillez noter que cette bibliothèque est fournie "telle quelle" et ne garantit pas un accès complet ou fiable à l'API d'École Directe. Elle est principalement destinée à un usage personnel et peut nécessiter des ajustements en cas de modifications dans l'API d'École Directe.

## Contributions
Les contributions sous forme de pull requests sont les bienvenues. Pour les changements importants, veuillez d'abord ouvrir une issue pour discuter de ce que vous aimeriez changer.

### Donations

Adresse Bitcoin : 13BMqpqbzJ62LjMWcPGWrTrdocvGqifdJ3

## Licence
Ce projet est distribué sous la licence MIT. Pour plus d'informations, consultez le fichier LICENSE.

