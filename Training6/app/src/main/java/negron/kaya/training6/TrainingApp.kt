package negron.kaya.training6

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class TrainingApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}