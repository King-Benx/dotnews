# DotNews
Bringing you the latest news locally and internationally. Share and save your latest articles or better yet, view them directly from the source

## Screenshots
| ![home view](https://github.com/King-Benx/dotnews/blob/master/screenshots/home.png) | ![international view](https://github.com/King-Benx/dotnews/blob/master/screenshots/international.png) |
| ![detail view](https://github.com/King-Benx/dotnews/blob/master/screenshots/details.png) | ![stored view](https://github.com/King-Benx/dotnews/blob/master/screenshots/saved.png) |
| ![about](https://github.com/King-Benx/dotnews/blob/master/screenshots/about.png) |

## Getting Started and Installation

1. Clone this repository onto your local machine.
`git clone https://github.com/King-Benx/dotnews.git`

2. Locate the project on your machine. 

3. In Android Studio, under the file menu select open, then select an existing project.

4. Build the project.
`./gradlew build`

5. Select an emulator and run the application.

6. In addition, you can run the application using an Android device.

### Prerequisites

1. [Set up Android Studio](https://developer.android.com/studio/install) 

2. [Run application on emulator](https://developer.android.com/studio/run/emulator)

3. [Run application on android device](https://developer.android.com/studio/run/device)


### Coding Styles/ Conventions
- Google Java Style
- XML Naming Conventions

## Architecture
* MVVM

## Consumed API Endpoints

```
    https://newsapi.org/v2/everything?q={query}&apiKey={key}
```

```
    https://newsapi.org/v2/top-headlines?sources={source}&apiKey={key}
```

## Built With/ Powered by

* [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Asynchronous and event-based functionality
* [RxJava](https://github.com/ReactiveX/RxJava) - Asynchronous and event-based functionality
* [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Programming language
* [Android](https://www.android.com/) - Operating System
* [Retrofit](https://square.github.io/retrofit/) - Networking
* [Picasso](http://square.github.io/picasso/) - Image processing
* [Room](https://developer.android.com/topic/libraries/architecture/room) - Database management

## Versioning
1.0 

## Authors
[Asiimwe Benard](https://github.com/King-Benx)


## Credits
All this has been possible thanks to the services provided by these guys.
[powered by NewsAPI.org](https://newsapi.org)
