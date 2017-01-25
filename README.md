# Studies

Just a personal study repository, nothing special and nothing more

## How to

The script `create.py` receives an argument `name` and create an android project with this name coping the Template project into Projects folder, for example:

```
$ ./create.py Test
```

will generate the android project `Projects/Test`, after it you just need to import the project on android studio and make wherever you want. 
You also can run the tests with:

```
[Projects/Test] $ ./gradlew clean \
core:testDebugUnitTest \
app:testDevelopmentDebugUnitTest
```

## Some points

- The project is write with clean architecture in mind, but it does not means that all clean rules are follow so do not use it as a clean guide.
- All code except the android views and extensions of android components are tested, it includes the presenters.
- The project is divided into two parts, `app` and `core`, you can think it like a `front-end` and a `back-end` because all code from `app` is about views and UI/UX in other hand all `core` code is about business rules and data
- The project is write with kotlin
- Fell free to use the script, fell free to make pull requests, but remember this repository is just a playground so do not take it too seriously :)
