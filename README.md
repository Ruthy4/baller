# Football-Fixtures


# Description

Football Fixtures is app that consumes fixtures data from https://www.football-data.org


# ScreenShots

<p float="left">
  <img src="https://user-images.githubusercontent.com/33810711/166926415-efcc856b-c9cb-45ce-b008-3cca461a885c.png" width="313" />
  <img src="https://user-images.githubusercontent.com/33810711/167015942-c18283c5-f865-481c-b91f-12fcae76c92b.png" width="321" /> 
  <img src="https://user-images.githubusercontent.com/33810711/167016028-98967162-b12a-4ea2-bf86-2978c4fcadf5.png" width="315" />
  <img src="https://user-images.githubusercontent.com/33810711/167016128-bf4dd249-555f-469a-b4c4-b666d920d467.png" width="325" /> 
  <img src="https://user-images.githubusercontent.com/33810711/167016241-347a0a51-e473-4510-b4af-3a88508568c7.png" width="325" />
  <img src="https://user-images.githubusercontent.com/33810711/167061193-3af70af6-ac0b-4877-aac4-136b6daf2dd0.png" width="307"/>

</p>


# Features

- Dependency injection (with Hilt)

- Google Material Design library

- Android architecture components to share ViewModels during configuration changes

- Edge To Edge Configuration

- Resource defaults

- colors.xml - colors for the entire project

- styles.xml - widget styles

- styles-text.xml - text appearances

- ViewModel - stores and manages UI-related data in a lifecycle conscious way

- Lifecycle- it performs an action when the life cycle state changes

- Retrofit - For networking

- Room - for offline caching

- ViewBinding - binding data to views

- [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations


- [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))

- `App` module - this is the main module. It contains code that wires multiple modules together like dependency injection setup and fundamental application configuration like retrofit configuration

- Testing
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/) via
    [android-junit5](https://github.com/mannodermaus/android-junit5))
    * [UT Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso))
    * [Mockk](https://mockk.io/) - mocking framework

- RetrofitService** - defines a set of API endpoints.
