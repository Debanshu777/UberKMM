package com.debanshu777.uberkmm

import android.content.Context
import androidx.startup.Initializer

internal lateinit var applicationContext: Context
    private set

object KLocationContext

class KLocationInitializer : Initializer<KLocationContext> {
    override fun create(context: Context): KLocationContext {
        applicationContext = context.applicationContext
        return KLocationContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}