# **SAPAMERCAT - Gestió de Productes**

## Descripció del Projecte

Aquest projecte és una aplicació per gestionar els productes d’un supermercat, amb un carret de compra que actualitza el preu dels productes en temps real. Els productes es divideixen en tres tipus: **alimentació**, **tèxtil** i **electrònica**. L'aplicació permet gestionar els productes que tenim a l'inventari, afegir-los al carret i, quan passem per caixa, generar el tiquet de compra.

---

## **Decisions del Disseny**

### 1. **Per què utilitzar `String` per als codis de barres?**
He fet servir **`String`** per als codis de barres en comptes de **`int`** perquè els codis de barres no sempre són números (potser tenen lletres) i no necessitem fer cap càlcul amb ells. És més pràctic fer servir un **`String`**, ja que ens permet més flexibilitat i evita problemes amb codis de barres alfanumèrics.

### 2. **Per què he escollit aquestes col·leccions?**
- **`ArrayList`**: He utilitzat un **`ArrayList`** per guardar els productes a l'inventari perquè és fàcil d'usar i ens permet accedir ràpidament als productes per índex. Com que no afegirem ni eliminarem molts productes contínuament, funciona perfectament.

- **`Map<Producte, Integer>`**: Per al carret de la compra, he utilitzat un **`Map`** per poder guardar el producte i la quantitat de cada un. Així, si afegim més d’un mateix producte, el sistema només actualitza la quantitat i no duplica el producte, el que ens facilita molt el treball.

### 3. **Com es calcula el preu dels productes?**
- **Productes d'Alimentació**: El preu es redueix segons la data de caducitat utilitzant la següent fórmula:

  ```text
  preu - preu * (1 / (dataCaducitat - dataActual + 1)) + (preu * 0.1)
- **Productes d'Electrònica**: El preu varia segons els dies de garantia amb la següent fórmula:

  ```text
  preu + preu * (diesGarantia / 365) * 0.1
### 4. **Ús de Lambda Expressions i Streams**
He utilitzat **lambda expressions** i **Streams** per realitzar cerques i operacions sobre col·leccions de manera més eficient. Per exemple, per cercar productes pel codi de barres i ordenar-los segons la data de caducitat o la composició tèxtil.