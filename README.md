# proposals

## Dodanie nowego wniosku
 POST /api/v1/proposals
```json
 {
    "title": "tytuł",
    "content": "treść"
 } 
 ```
 
 ## Aktualiazaja wniosku
 PUT /api/v1/proposals
```json
 {
    "title": "nowy tytuł",
    "content": "inna treść"
 } 
 ```
 
## Pobranie jedengo wniosku
 GET /api/v1/proposals/{id}/

## Pobranie jedengo wniosku
 GET /api/v1/proposals/?page={strona}&size={ilosc}&sortBy={status|title}

## Weryfikacja wniosku
 PATCH /api/v1/proposals/verify/{id}/

## Usunięcie wniosku
 PATCH /api/v1/proposals/delete/{id}/
```json
{
    "description": "wniosek niezasadny"
}
 ```
 
 ## Odrzucenie wniosku
 PATCH /api/v1/proposals/reject/{id}/
```json
{
    "description": "wniosek niezasadny"
}
 ```
 ## Akceptacja wniosku
 PATCH /api/v1/proposals/accept/{id}/
 
 ## Publikacja wniosku
 PATCH /api/v1/proposals/publish/{id}/
 
