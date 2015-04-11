#PRO
# Najważniejsze komendy w gicie:

1. `git clone https://github.com/demsey15/PRO`  - sklonowanie naszego repozytorium na swój komputer (trzeba zrobić tylko raz na początku)
2. `git remote add upstream https://github.com/demsey15/PRO` - dodanie naszego repozytorium jako główne (nadrzędne; zrobić tylko raz na początku)
3. `git pull upstream master` - synchronizowanie repozytorium z głównym repozytorium (robić jak najczęściej, żeby uniknąć problemów z synchronizacją)
4. `git add` - dodanie plików do zatwierdzenia (najczęściej wystarczy wpisać `git add .`)
5. `git commit -m "Your message"` - zatwierdzenie zmian przed wysłaniem do głównego repozytorium (piszcie sensowne wiadomości, bo je później widać na githubie i żeby było wiadomo od razu, co zostało zmienione :) )
6. `git push origin master` - wysłanie zmian do repozytorium (przed wysłaniem repozytorium musi być zsynchronizowane z głównym, dlatego ważne, żeby przed każdą pracą synchronizować, jesli ktoś inny wprowadził zmiany i ważne, żeby jak najczęściej wypychać zmiany do repozytorium, zeby uniknąć konfliktów)
7. `git status` - check status of your project
