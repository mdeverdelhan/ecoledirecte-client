# Pistes d'amélioration pour `ecoledirecte-client`

Ce document propose une feuille de route pragmatique pour améliorer la qualité, la maintenabilité et la robustesse de la bibliothèque.

## Priorité haute

1. **Mettre en place de vrais tests automatisés (unitaires + intégration mockée)**
   - Le test `EcoleDirecteClientTest` est essentiellement un brouillon local avec du code commenté, sans assertions.
   - L'objectif minimal : couvrir l'authentification, le parsing, les cas d'erreurs HTTP et la double-authentification avec `MockWebServer`.

2. **Éviter la construction manuelle de JSON par concaténation de chaînes**
   - Dans `postDoubleAuthReponse`, le JSON est construit à la main dans une string (`"{ \"choix\": ... }"`).
   - Risque : échappement incorrect, bugs subtils, difficulté de maintenance.
   - Recommandation : créer un objet/Map sérialisé via Gson (comme dans `buildLoginRequestBody`).

3. **Renforcer la gestion des erreurs API**
   - Les méthodes reposent surtout sur `response.isSuccessful()` puis parsing direct.
   - Ajouter un traitement systématique des codes fonctionnels de l'API EcoleDirecte (pas seulement HTTP), et enrichir les exceptions (endpoint, code, payload utile).

4. **Découper `EcoleDirecteClient` en couches plus petites**
   - La classe concentre transport HTTP, authentification, construction des requêtes et logique métier de nombreux endpoints.
   - Recommandation : extraire un composant `Transport`, un composant `Auth`, et des services par domaine (`eleves`, `classes`, `famille`, etc.).

## Priorité moyenne

5. **Factoriser la construction des endpoints**
   - Beaucoup d'URLs sont construites à la main avec concaténation.
   - Recommandation : centraliser les chemins dans un builder utilitaire pour réduire les erreurs et faciliter les évolutions d'API.

6. **Rendre la configuration HTTP plus explicite (timeouts, retry, user-agent)**
   - Le constructeur par défaut instancie `new OkHttpClient()` sans politique explicite.
   - Recommandation : proposer une configuration par défaut documentée (timeouts raisonnables, éventuellement retry contrôlé).

7. **Clarifier le cycle de vie d'authentification et la thread-safety**
   - Le client est stateful (`gtkCookies`, `authToken`, `fullyAuthenticated`) ; l'usage concurrent n'est pas documenté.
   - Recommandation : documenter clairement le contrat ou rendre le client immutable/stateless côté session.

8. **Valider les paramètres d'entrée**
   - Exemples : `eleveId`, `classeId`, `periodeId` et autres sont injectés dans les URLs sans validation explicite.
   - Recommandation : ajouter des préconditions pour échouer tôt avec messages explicites.

## Priorité faible (mais utile)

9. **Nettoyage du `pom.xml`**
   - `project.build.sourceEncoding` est défini deux fois.
   - Recommandation : supprimer le doublon, et envisager `maven-enforcer-plugin` pour verrouiller la version de Java/Maven.

10. **Améliorer la documentation “contributeur”**
   - Ajouter une section “Développement local” : exécution des tests, conventions de code, stratégie de versionning, et politique de support des endpoints.

11. **Uniformiser certains détails de style et de nommage**
   - Exemple : `postDoubleAuthReponse` (orthographe “réponse”) et homogénéité FR/EN selon conventions du projet.

12. **Mettre en place une CI plus stricte**
   - Pipeline recommandé : build + tests + vérification de style/format + publication conditionnelle release.

---

## Proposition de plan d'exécution (rapide)

- **Sprint 1** : tests unitaires critiques + sécurisation JSON + factorisation endpoints.
- **Sprint 2** : refactor architecture client (transport/auth/services) + erreurs enrichies.
- **Sprint 3** : hardening (validation paramètres, thread-safety), CI et documentation contributeur.
