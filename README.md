# ScreenShield: Comprehensive Protection for Sensitive Content

ScreenShield is a robust Kotlin library designed to safeguard your Android app's sensitive content by preventing screenshots and screen recordings. Built with Jetpack Compose, this library leverages Android's `FLAG_SECURE` flag to automatically protect your app's screens while they are active, ensuring that confidential information remains secure.

ScreenShield offers a seamless solution for enhancing your app's privacy, and it's currently used in the [Eyed Auth](https://play.google.com/store/apps/details?id=de.aploi.eyedauth) app, showcasing its effectiveness in real-world applications. 

The library's implementation draws inspiration from the work of [@ahmetfurkhans](https://github.com/ahmetfurkans/BlockScreenShotCompose).

## Key Features

- **Screenshot Protection**: Blocks users from taking screenshots of protected screens within your app.
- **Screen Recording Prevention**: Disables screen recording while your app is in the foreground, ensuring sensitive information cannot be captured.
- **Lifecycle Integration**: Automatically applies protection when your app is in use and removes it when the app goes into the background.

## Installation

To integrate ScreenShield into your Jetpack Compose project, simply drag and drop `ScreenShield.kt` into your codebase, add `import ScreenShield` where you want to use it and wrap the content you wish to protect with it.

## Usage

```kotlin
import ScreenShield

@Composable
fun YourProtectedScreen() {
    ScreenShield {
        // Your protected UI content
    }
}
```
> [!NOTE]  
> By wrapping your composables with `ScreenShield`, you ensure that screenshots and screen recordings are blocked whenever the screen is active.


## How It Works

ScreenShield uses Android's `WindowManager.LayoutParams.FLAG_SECURE` flag to prevent screenshots and screen recordings. The library is lifecycle-aware, automatically managing protection based on the current state of the app. Whether your app is in the foreground or background, ScreenShield ensures that your content is secure.

### Core Components:

1. **ScreenShield Composable**: The central composable that blocks screenshots and recordings.
2. **ComposableLifeCycle**: A lifecycle-aware composable that triggers protection based on the app's state.
3. **findActivity()**: A helper function that retrieves the current activity, essential for applying window flags.



## License

This project is distributed under the MIT License. For more details, refer to the [LICENSE](LICENSE) file.

## Contact

For inquiries or support, feel free to reach out to Carlo at [carlo@aploi.de](mailto://carlo@aploi.de).

## Authors

- [Carlo Esposito](https://www.github.com/cesp99)
- [Ahmet Furkan](https://github.com/ahmetfurkans) (Original implementation)

## Used By

This library is proudly used in the [Eyed Auth](https://play.google.com/store/apps/details?id=de.aploi.eyedauth) app, demonstrating its reliability in protecting sensitive user information.
