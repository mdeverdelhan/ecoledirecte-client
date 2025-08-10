# Changelog

Toutes les modifications notables apportées à `ecoledirecte-client` sont documentées dans ce fichier.

## [Non publié]

- Ajout de la version du site EcoleDirecte dans `EcoleDirecteApiConfig`
- Mise à jour de dépendances
- Mise à jour de l'authentification (pour prise en compte des cookies GTK)
  
## [1.3] - 2024-04-06

- Prise en compte de la double authentification `doubleauth`
- Déplacement des APIs d'authentification dans le package `auth`
- Ajout de l'API `paiementsenligne`
- Ajout de l'API `familledocuments`

## [1.2] - 2024-04-04

- Simplification de l'API `conseildeclasse` (mutualisation des objets d'appréciations)
- Ajout de l'API `contactetablissement`
- Transformation de `decodeAppreciation` en `decodeBase64Text`
- Transformation de `Class` en `Classe` dans l'API `login`

## [1.1] - 2024-03-29

- Ajout d'une classe utilitaire
- Documentation et ajout de commentaires

## [1.0] - 2024-03-23

- Première version de la bibliothèque `ecoledirecte-client`

