1)Used libraries:
Remote database:
Firebase - Realtime database

Auth
Firebase Auth using Google

Navigation
Navigation Component
Save Args Plugin

Dependency Injection
Hilt

Display images
Glide

2)Architecture
MVVM pattern View <-> ViewModel <-> Repository <-> Data
1 activity
Views build on Fragments using viewBinding feature

3)Clean code
- Used dependency injection by Android Hilt
- Different packages for different app layers like: ui, domain, data

4)Settings persistence using shred preferences


App features:
- Login by google
- Logout
- Display list of books
- Add new book
- Edit book
- Display book info
- Query book by title, author
- View profile data
- Change app mode(dark bright)
- Change app font size (small, regular, big)




Najtrudnieszą rzeczą było przekazywanie informacji zwrotnych z warstwy danych w przypadku gdy dane pobierane są asynchronicznie.
W aplikacji został użyty ResultCallback. Jest to rozwiązanie wystarczające dla tak małej i prostej aplikacji.
Docelowym rozwiązaniem powinno być uzycie RxJavy ewentualnie coroutines gdyby aplikacja była pisana w kotlinie.
RxJava jest biblioteką pozwalającą na efektywne przełączanie pomiędzy wątkami jednocześnie pozwala zwalniać obserwacje na wynik gdy juz nie jest nam to potrzebne.
Coroutines natomiast mają proste api pozwalajace pisać kod asynchroniczny w bardzo przyjemny sposób.
Decyczja o wyborze ResultCallback podjęta ze względu na łatwość implementacji dla tego projektu. RxJava wyamaga poznania nowego api, natomiast coroutines jest tylko w języku Kotlin

