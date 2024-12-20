# MovieApp

A modern Android movie app built with the latest Android development tools and best practices.

## Features
- Browse popular movies
- Search movies
- View movie details
- Save favorite movies
- Offline support

## Tech Stack
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **DI**: Hilt
- **Database**: Room
- **Network**: Retrofit
- **Async**: Kotlin Coroutines + Flow
- **Image Loading**: Coil

## Project Structure
- **app**: Main application module
- **core**:
  - common: Common utilities and base classes
  - data: Data layer implementation
  - domain: Domain layer with business logic
  - ui: Common UI components
- **features**:
  - details: Movie details feature
  - favorites: Favorite movies feature
  - home: Home screen feature
  - search: Movie search feature