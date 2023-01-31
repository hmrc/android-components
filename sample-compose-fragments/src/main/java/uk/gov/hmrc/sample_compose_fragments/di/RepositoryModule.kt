package uk.gov.hmrc.sample_compose_fragments.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.gov.hmrc.sample_compose_fragments.data.repository.RepositoryImpl
import uk.gov.hmrc.sample_compose_fragments.domain.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositoryImpl(repository: RepositoryImpl): Repository
}