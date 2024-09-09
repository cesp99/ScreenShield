import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


@Composable
fun ScreenShield(
    content: @Composable () -> Unit
) {
    // Get the current activity from the context
    val activity = LocalContext.current.findActivity()

    // Observe lifecycle events to set or clear the FLAG_SECURE
    LifecycleEventObserverComposable { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                // Prevent screenshots when the activity is visible
                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE
                )
            }

            Lifecycle.Event.ON_STOP -> {
                // Allow screenshots again when the activity is not visible
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            }

            else -> Unit
        }
    }

    // Display the content inside the secure screen
    content()
}






// Extension function to find the activity from a given context
fun Context.findActivity(): Activity {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is Activity) return currentContext
        currentContext = currentContext.baseContext
    }
    throw IllegalStateException("Activity not found in context chain")
}



// Composable function to observe lifecycle events
@Composable
fun LifecycleEventObserverComposable(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onLifecycleEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        // Create a LifecycleObserver to listen for lifecycle events
        val observer = LifecycleEventObserver { source, event ->
            onLifecycleEvent(source, event)
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // Remove the observer when the composable leaves the composition
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}
