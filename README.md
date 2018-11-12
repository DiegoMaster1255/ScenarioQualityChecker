# ScenarioQualityChecker

## Projekt na IO 2018

### Opis
Dla analityków dokumentujących wymagania funkcjonalne za pomocą scenariuszy nasza aplikacja SQC dostarczy informacji ilościowych oraz umożliwi wykrywanie problemów w wymaganiach funkcjonalnych zapisanych w postaci scenariuszy. Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

### Notacja scenariusza
• Scenariusz zawiera nagłówek określający jego tytuł i aktorów (zewnętrznych oraz system)

• Scenariusz składa się z kroków (każdy krok zawiera tekst)

• Kroki mogą zawierać pod-scenariusze (dowolny poziom zagłębień)

• Kroki mogą się zaczynać od słów kluczowych: IF, ELSE, FOR EACH

### Jak korzystać
Zapytania http należy wysyłać na adres localhost:8080/{nazwa_pliku}?funkcja={nazwa_funkcji}
gdzie:
• {nazwa_pliku} - należy podać nazwę pliku typu json, w którym zawarty jest scenariusz, który chcemy ocenić
Uwaga! Plik musi znajdować się w katalogu bazowym projektu (który jest przyjętą przez nas bazą danych)

• {nazwa_funkcji} - podajemy jedną z dostępnych (wymienionych poniżej) funkcji.
Wywołanie bez jawnego podania funkcji wykona funkcję domyślną, czyli zliczanie kroków scenariusza.
Przykład użycia: localhost:8080/Scenariusz?funkcja=slowaKluczowe, zwróci w pliku "output.json", znajdującym się w folderze
głównym projektu informację o ilości słów kluczowych w scenariuszu.
### Dostępne funkcje:
zliczKroki - ile kroków jest w całym scenariuszu
bledneKroki - które kroki nie zaczynają się od aktora
slowaKluczowe - ile kroków zaczyna się od słów kluczowych
scenariuszDoPoziomu - zapisuje scenariusz do określonego zagłębienia
Uwaga! w przypadku tej funkcji należy dodać kolejny argument do parametru "funkcja", który odpowiada poziomowi zagłębienia.
Przykład: localhost:8080/Scenariusz?funkcja=scenariuszDoPoziomu&funkcja=2.
scenariuszTekstowo - zapisuje scenariusz w pliku ZapisanyScenariusz.txt

