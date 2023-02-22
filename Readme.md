# Mobile Tech Test
## Instructions
Create an app that lists all messages and their details from JSONPlaceholder.
## Requirements:
- Load the posts from the JSON API and populate the view.
- Each cell/row should show the title of the post without clipping ( dynamic height ).
- Once a Post is tapped, the user should be taken to a Post Details Screen.
- The post details screen should contain:
- The post title and description.
- The post author information.
- The list of comments.
- Implement a mechanism to favorite/unfavorite a post.
- Favorite posts should be at the top of the list.
- Favorite posts should have a star indicator.
- Implement a mechanism to delete a post.
- Implement a mechanism to remove all posts except from the favorites ones.
- Implement a mechanism to load all posts from the API.
- Add meaningful Unit Tests.
## Extra points
- Provide an off line solution where:
- User should be able to see previously loaded posts.
- User should be able to see post details.

# Mobile Tech Test Solution

![Post List](https://github.com/borissoto/mobile-tech-test/blob/master/screenshots/main.png)
![Post Detail](https://github.com/borissoto/mobile-tech-test/blob/master/screenshots/detail.png)

## Installation
Requires Android Studio
```sh
Run app: Shift + F10
```
## Features
- i18n
- Databinding
- MVVM
- Single source of truth
- Clean Architecture
- Multi-Module architecture
- Coroutines
- DI Dagger
- DI Hilt
- Unit Testing

## Tech
- Kotlin
- Coroutines
- Retrofit
- Room
- Hilt

