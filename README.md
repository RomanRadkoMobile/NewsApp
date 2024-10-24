# NewsApp 📰

An Android News Application built using MVVM, Retrofit, Room, Hilt, Jetpack Compose, and Jetpack Navigation.

## Features ✨

- 🗞️ Display a list of news articles from a remote API.
- 🔍 View detailed news information on a separate screen.
- 📶 Offline support using Room Database.
- 🧩 Modularized structure with MVVM architecture.
- 📦 Dependency injection using Hilt.
- 🧭 Seamless navigation with Jetpack Navigation.
- 🌈 Modern UI with Jetpack Compose.

## Tech Stack 🛠️

### 🧑‍💻 Architecture
- MVVM (Model-View-ViewModel): Ensures a clean separation between UI and business logic.

### 🗄️ Data Layer
- Retrofit: For making HTTP requests to fetch news from a REST API.
- Room: For local caching and offline data persistence.

### 🧑‍💻 Dependency Injection
- Hilt: For managing dependencies, simplifying DI with Android components.

### 🖌️ UI Layer
- Jetpack Compose: For building modern and declarative UI.
- Jetpack Navigation: For seamless navigation between the list and detail screens.

## Architecture Overview 🏗️

The app follows MVVM architecture with Hilt for dependency injection, Room for local storage, Retrofit for API calls, and Jetpack Compose for the UI.

- Model: Represents the business logic and handles data from the API and Room.
- ViewModel: Acts as a bridge between the Model and UI, providing data to the UI and handling logic.
- View: The UI built with Jetpack Compose, observing the ViewModel for data changes.

### Project Structure 🗂️

```bash
├── app/ 
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── me.radko.practice/
│   │   │   │       ├── data/                # Repository, API services, Room entities
│   │   │   │       ├── di/                  # Hilt modules for dependency injection
│   │   │   │       ├── domain/              # Models and business logic
│   │   │   │       ├── ui/                  # Compose screens (NewsList, NewsDetails)
│   │   │   │       ├── viewmodel/           # ViewModels for MVVM architecture
│   │   │   │       └── navigation/          # Jetpack Navigation host
│   │   │   ├── res/                         # Resources (layout, values, etc.)
│   │   │   └── AndroidManifest.xml          # App manifest file
├── build.gradle
├── settings.gradle
└── .gitignore
