package uk.gov.hmrc.sample_compose_fragments.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.gov.hmrc.sample_compose_fragments.navigator.AppNavigator
import uk.gov.hmrc.sample_compose_fragments.navigator.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindNavigator(impl : AppNavigator) : Navigator

}