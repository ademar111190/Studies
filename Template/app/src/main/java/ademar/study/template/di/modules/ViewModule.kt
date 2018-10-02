package ademar.study.template.di.modules

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class ViewModule(
        private val activity: FragmentActivity
) {

    @Provides
    fun provideActivity(): FragmentActivity = activity

    @Provides
    fun provideFragmentManager(): FragmentManager = activity.supportFragmentManager

}
