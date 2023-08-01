MyLibrary app is a book management app created to manage physical book collection and their physical location on shelves.This Android app enables a logged-in user to add, delete, search for books by title, author, location that one has in the library.

Link to the youtube video: https://youtu.be/9HLoGy1t6RE

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








