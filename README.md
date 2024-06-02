# University App

This is a Kotlin-based Android application that displays a list of universities from a remote API and stores the data locally using Room Database. The app supports refreshing the data and ensures that the local database is kept up-to-date with the latest information from the API.

## Key Features

- Fetches university data from a remote API.
- Stores university data locally using Room Database.
- Supports data refresh with the latest information from the API.
- Android jetpack
- Implements MVVM (Model-View-ViewModel) architecture.
- Follows Clean Architecture principles.
- Uses Hilt for Dependency Injection.
- Modular architecture with separate modules for different functionalities.
- Deep linking support for navigation between modules.

**Clone the repository:**
   git clone https://github.com/habi377/XischeAssesment.git

## Architecture 

-**MVVM (Model-View-ViewModel**

-**Clean Architecture**
 - Domain Layer: Contains the business logic and entity definitions(usecases).
 - Data Layer: Manages data sources, including the Room database and remote API service.
 - Presentation Layer: Handles UI components, ViewModels, and state management.

-**Modularization**
 - app Module (Main)
 - Data Module (Data layer)
 - Domain Module (Domain layer)
 - ModuleA (Listing Screen) (UI layer)
 - ModuleB (Details Screen) (UI layer)
 - Common  (Common Models)

-**Navigation Component**

-**Data Binding**

-**Flow API**

-**Room**

## Gradle
-**Version Catelog**

-**Kotlin DSL**
