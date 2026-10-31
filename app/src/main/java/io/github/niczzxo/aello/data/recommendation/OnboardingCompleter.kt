package io.github.niczzxo.aello.data.recommendation

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/** Seeds the engine with the topics picked during onboarding and marks onboarding done. */
class OnboardingCompleter
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        suspend fun complete(topics: Set<String>) = AelloNeuroEngine.completeOnboarding(context, topics)
    }
