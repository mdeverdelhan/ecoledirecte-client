# ecoledirecte-client

Bibliothèque Java d'interrogation de l'API EcoleDirecte : https://www.ecoledirecte.com/

### EcoleDirecte

Exemples d'appels cURL :

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
