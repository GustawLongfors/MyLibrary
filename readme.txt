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

User Stories

As a home library owner,  I want to be able to know exactly which books I have and where they are located in my apartment so that I can easily find them on shelves and feel a lot more in control about my collection -  critical fulfilled, must-have
As a home library owner, I want to be able to log in to the library application using my google account, so I dont’t have to remember new login and password. - critical, fulfilled, must-have
As a home library owner, I want to be able to know how many books I have, so I am more in control - critical, fulfilled, must-have
As a home library owner, I want to able to add new books to my list, so I know exactly which books I have - critical, fulfilled, must-have
As a home library owner, I want to be able to search my books, so I can find the one I’m looking for - critical, fulfilled, must-have
As a home library owner, I want to be able to edit and remove  my books, so I keep my book list updated - critical, fulfilled, must-have
As an app user, I want to be able to read easily all info on screen so I don’t have to strain my eye sight - medium, fulfilled (font and brightness), should-have added more options
As a home library owner, I want to be able to have a lot of information about my books, so I can easily identify them - partially fulfilled, language and ISDN number filed could-have been added
As a home library owner, I want to be able to search my book list with a lot of filters, so I can find exact books I’m looking for - partially fulfilled, could-have added specific filters
As a home library owner, I want my book list to have special screen design, so that it is nice to look at the app - not fulfilled, won’t have due to time constraints






