# Printful_challenge

## Tech stack - Library
- [Kotlin](https://kotlinlang.org/) , [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) , [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- [Hilt](https://developer.android.com/training/depvendency-injection/hilt-android)
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- JetPack
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
  - [MVVM Architecture] (View  - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit)
- [Moshi](https://github.com/square/moshi)
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
- [Glide](https://github.com/bumptech/glide)

## Architecture
The application is based on MVVM architecture and a repository pattern.

![architecture](https://raw.githubusercontent.com/fevziomurtekin/hackernewsapp/master/screenshot/mvvm.png)

## Features
- Fetches list of cat breeds from CAT API (the authentication token is passed on every request done to the API)
- The API data is paginated with the aid of Paging 3.0 library. This library allows to easily show Progress Bar while the data is being retrieve or to show a retry button if the connection fails
- The application assumes we have network connection because of time constraints (otherwise instead of using the response from the API I would've used only the entity model, after saving the data)
- Room is used to retrieve the saved data in order to display the cat breed detail

