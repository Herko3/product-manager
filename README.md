#Product Manager Application

### Entitások:
* Supplier
* Product

### Supplier
Attribútumai:
* Név (kötelező, max 255 karakter): a beszállító neve
* Ország (kötelező, max 255 karakter): az ország ahonnan a beszállító származik
* Cím (kötelező, max 255 karakter): a beszállító telephelyének címe
* Pénznem (kötelező, enum): a beszállító számlájának pénzneme
* Termékek (kapcsolat a Product entitásra): 1 beszállítóhoz több termék is lehet

Lekérdezések:
* összes beszállító
* 1 beszállító
* 1 beszállító összes terméke

Létrehozások:
* beszállító létrehozása

Frissítés:
* beszállító adatainak (kivéve termékek) frissítés

Törlés:
* beszállító törlése, ezáltal az összes hozzá tartozó temrmék is törlődik

### Product
Attribútumai:
* Cikkszám (kötelező, _egyedi_, max 255 karakter): a termék cikkszáma
* Név (kötelező, max 255 karakter): a termék neve
* Leírés (nem kötelező, max 255 karakter): leírás a termékhez, opcionális
* Nettó ár (kötelező): nettó ára
* Bruttó ár (kötelező): bruttó ára
* Tipus (kötelező, enum): típusa a terméknek

Lekérdezések:
* Összes termék lekérdezése (opcionális szűrés név részletre)
* 1 termék lekérdezése

Frissítés:
* A termék összes adatának frissítése

Törlés:
* 1 termék törlése


### Program
A program lényege a termékek kezelése, melyek nem állhatnak magukban, csak beszállítóval együtt. A termékek lekérdezése történhet az összes termék, név alapján szűrve, csak 1 termék vagy egy beszállító összes terméke. 