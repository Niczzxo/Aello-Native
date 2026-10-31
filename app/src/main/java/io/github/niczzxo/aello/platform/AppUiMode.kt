package io.github.niczzxo.aello.platform

/** User-selected interface mode for the shared Aello APK. */
enum class AppUiMode {
    AUTOMATIC,
    MOBILE,
    TV;

    fun resolve(deviceFormFactor: DeviceFormFactor): AppUiRoot = when (this) {
        AUTOMATIC -> if (deviceFormFactor == DeviceFormFactor.TV) AppUiRoot.TV else AppUiRoot.MOBILE
        MOBILE -> AppUiRoot.MOBILE
        TV -> AppUiRoot.TV
    }

    companion object {
        fun fromStorage(value: String?): AppUiMode =
            entries.firstOrNull { it.name == value } ?: AUTOMATIC
    }
}

/** The UI root rendered by [io.github.niczzxo.aello.MainActivity]. */
enum class AppUiRoot {
    MOBILE,
    TV,
}
