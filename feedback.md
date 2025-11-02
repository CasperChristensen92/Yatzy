### Styrker

*   **God Projektstruktur:** Projektet har en klar og logisk struktur med adskillelse af ansvarsområder i `gui`- og `models`-pakker. Dette gør koden lettere at navigere i og vedligeholde.
*   **Klar UI-logik:** `YatzyGui`-klassen har, selvom den kan forbedres, en klar adskillelse af UI-opsætning (`initContent`) og hændelseshåndtering (`actionThrowDice`, `actionChooseCombination`).

### Forbedringsområder

*   **Ineffektiv Billedindlæsning:** `diceImage()`-metoden i `YatzyGui` indlæser terningebillederne fra filsystemet, hver gang den kaldes. Det ville være mere effektivt at indlæse disse billeder én gang ved programmets start og gemme dem i et array eller et map for hurtig hentning.
*   **Todo's som er implementeret** Fjern todo's i metoder der er implementeret, 
*   **Forenkl Komplekse Metoder:** Nogle af score-metoderne i `YatzyResultCalculator`, som `twoPairScore` og `threeOfAKindScore`, er lidt komplekse og kan være svære at forstå ved første øjekast. De kunne forenkles eller kommenteres for at gøre deres logik klarere.

I `threeOfAKindScore` kan I bruge samme logik som i `fourOfAKindScore`
```Java
if (sortedIntDice[2]==sortedIntDice[4]) return sortedIntDice[2]*3;
if (sortedIntDice[1]==sortedIntDice[3]) return sortedIntDice[1]*3;
if (sortedIntDice[0]==sortedIntDice[2]) return sortedIntDice[0]*3;
return 0;
```

Det er ikke nemt at gennemskue hvordan jeres `fourOfAKindScore()` metode fungerer. En simplere implementering af metoden 
kunne være at udnytte at fuldt hus er en af to møstre
[x, x, y, y, y] eller [x, x, x, y, y]

```Java
        if (sortedIntDice[0] == sortedIntDice[4]) { //5 ens er ikke fuldt hus.
            return 0;
        }
        if (sortedIntDice[0] == sortedIntDice[1] && sortedIntDice[2] == sortedIntDice[4]) {
            return sortedIntDice[0] * 2 + sortedIntDice[2] * 3;
        }
        if (sortedIntDice[0] == sortedIntDice[2] && sortedIntDice[3] == sortedIntDice[4]) {
            return sortedIntDice[0] * 3 + sortedIntDice[3] * 2;
        }
        return 0;
```

Jeres metode er dog korrekt. 

Det er lidt forvirende at scoren bliver ved med at opdaterer efter at man har valgt en række.
Det ville have været mere brugervenligt hvis en række blev låst. 
