package com.bg.itunesvideo.data

import android.app.Application
import com.bg.imusicplayer.data.network.NetworkConnectionInterceptor
import com.bg.itunesvideo.data.db.ITunesVideoDatabase
import com.bg.itunesvideo.data.model.repository.ITunesVideoRepository
import com.bg.itunesvideo.data.network.ApiEndPoint
import com.bg.itunesvideo.data.viewmodel.HistoryViewModelFactory
import com.bg.itunesvideo.data.viewmodel.VideoDetailsViewModelFactory
import com.bg.itunesvideo.data.viewmodel.VideoListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Balaji Gaikwad on 31/1/21.
 *
 * ITunesVideoApplication class is used to initialise Kodein
 * dependency framework
 */
class ITunesVideoApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ITunesVideoApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiEndPoint(instance()) }

        bind() from singleton { ITunesVideoRepository(instance(),instance(),instance()) }

        bind() from singleton { ITunesVideoDatabase(instance()) }
        bind() from provider { HistoryViewModelFactory(instance(),instance()) }
        bind() from provider { VideoListViewModelFactory(instance(),instance()) }
        bind() from provider { VideoDetailsViewModelFactory(instance(),instance()) }


    }
}