package com.magicsamplecase.dagger

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.magicsamplecase.MainContainerFragment
import com.magicsamplecase.data.remote.CardsRemoteDS
import com.magicsamplecase.data.repository.CardsRepository
import com.magicsamplecase.domain.boundaries.CardsRepositoryBoundary
import com.magicsamplecase.presentation.navigator.Navigator
import dagger.Component
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit

@PresentationScope
@Component(
    modules = [
        DomainModule::class,
        DataModule::class,
        NavigatorModule::class,
        RouterModule::class
    ]
)
interface PresentationComponent {

    fun injectMainFragment(fragment: MainContainerFragment)

    fun provideCardsRepositoryBoundary() : CardsRepositoryBoundary
    fun providesCardsRDS() : CardsRemoteDS

    fun providesRouter(): Cicerone<Router>
    fun provideNavigator(): Navigator
}


@Module
class DomainModule {
    @Provides
    fun provideCardsRepositoryBoundary(remote: CardsRemoteDS) : CardsRepositoryBoundary = CardsRepository(remote)
}

@Module
class DataModule {

    @Provides
    fun provideHttpClient() : OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }

    @Provides
    fun providesRetrofit(client: OkHttpClient.Builder) : Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.magicthegathering.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client.build())
            .build()


    @Provides
    fun providesCardsRDS(retrofit: Retrofit): CardsRemoteDS =
        retrofit.create(CardsRemoteDS::class.java)
}

@Module
class NavigatorModule(
    private val activity: FragmentActivity,
    private val fragmentManager: FragmentManager,
    private val containerId: Int) {

    @Provides
    fun providesNavigator() = Navigator(activity, fragmentManager, containerId)
}

@Module
class RouterModule {
    @Provides
    @PresentationScope
    fun providesRouter(): Cicerone<Router> = Cicerone.create()
}


