#Product Manager Application

### Program
A program lényege a termékek kezelése, melyek nem állhatnak magukban, csak beszállítóval együtt. A termékek lekérdezése történhet az összes termék, név alapján szűrve, csak 1 termék vagy egy beszállító összes terméke.
Továbbá a program tenderek kezelésére is alkalmas. A tenderhez szükség van a termékekre is.

### Entitások:
* Supplier
* Product
* Tender

### Supplier
Attribútumai:
* Név (kötelező, max 255 karakter): a beszállító neve
* Ország (kötelező, max 255 karakter): az ország ahonnan a beszállító származik
* Cím (kötelező, max 255 karakter): a beszállító telephelyének címe
* Pénznem (kötelező, enum): a beszállító számlájának pénzneme
* Termékek (kapcsolat a Product entitásra): 1 beszállítóhoz több termék is lehet

A következő végpontokon érjük el az entitást

| Http metódus | Vég pont               | Leírás                           |
| ------------ | -------------------    | -------------------------------- |
| GET          | `"api/suppliers"`      | lekérdezi az összes beszállítót     |
| GET          | `"api/suppliers/{id}"` | lekérdez egy beszállítót id alapján |
| POST         | `"api/suppliers"`      | létrehoz egy beszállítót            |
| PUT          | `"api/suppliers/{id}"` | módosít egy beszállítót id alapján  |
| DELETE       | `"api/suppliers/{id}"` | töröl egy beszállítót id alapján    |

### Product
Attribútumai:
* Cikkszám (kötelező, _egyedi_, max 255 karakter): a termék cikkszáma
* Név (kötelező, max 255 karakter): a termék neve
* Leírés (nem kötelező, max 255 karakter): leírás a termékhez, opcionális
* Nettó ár (kötelező): nettó ára
* Bruttó ár (kötelező): bruttó ára
* Tipus (kötelező, enum): típusa a terméknek

| Http metódus | Vég pont               | Leírás                           |
| ------------ | -------------------    | -------------------------------- |
| GET          | `"api/products"`      | lekérdezi az összes terméket     |
| GET          | `"api/products/{id}"` | lekérdez egy terméket id alapján |
| POST         | `"api/products"`      | létrehoz egy terméket            |
| PUT          | `"api/products/{id}"` | módosít egy terméket id alapján  |
| DELETE       | `"api/products/{id}"` | töröl egy terméket id alapján    |

### Tender
Attribútumai:
* Név (kötelező, max 255 karakter): a tender neve
* Árajánlat dátuma (kötelező, csak múlt vagy jövő): amikor az árajánlat elkészült
* termékek listája (nem lehet üres): az árajánlatban szereplő termékek

| Http metódus | Vég pont               | Leírás                           |
| ------------ | -------------------    | -------------------------------- |
| GET          | `"api/tenders"`      | lekérdezi az összes tendert     |
| GET          | `"api/tenders/{id}"` | lekérdez egy tendert id alapján |
| POST         | `"api/tenders"`      | létrehoz egy tendert            |
| PUT          | `"api/tenders/{id}"` | módosít egy tendert id alapján  |
| DELETE       | `"api/tenders/{id}"` | töröl egy tendert id alapján    |