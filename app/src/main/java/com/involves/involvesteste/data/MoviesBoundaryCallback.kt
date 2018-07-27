/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.involves.involvesteste.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.util.Log
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.source.local.MoviesLocalCache
import com.involves.involvesteste.data.source.remote.ApiService
import com.involves.involvesteste.data.source.remote.searchMovies

class MoviesBoundaryCallback(
        private val query: String,
        private val service: ApiService,
        private val cache: MoviesLocalCache
) : PagedList.BoundaryCallback<Movie>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData(query)
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchMovies(service, query, lastRequestedPage, { movies ->
            cache.insertMovies(movies, {
                lastRequestedPage++
                isRequestInProgress = false
            })
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}