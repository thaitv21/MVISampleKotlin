package com.nullexcom.mvisamplekotlin.usecase

import com.nullexcom.mvisamplekotlin.models.Movie
import io.reactivex.Observable
import org.jsoup.Jsoup
import java.util.*

class RecommendUseCase {
    fun enqueue(): Observable<List<Movie>> {
        return Observable.defer {
            val movies: MutableList<Movie> = ArrayList()
            val document = Jsoup.connect("http://xemphimplus.net/").get()
            val elements = document
                .select("#halim-carousel-widget-3xx")
                .select("div > article")
            for (element in elements) {
                val imgUrl = element.select("figure").select("img")
                    .attr("data-src")
                val status = element.select(".status").text()
                val entryTitle = element.select(".entry-title").text()
                val originalTitle = element.select(".original_title").text()
                val url = element.select(".halim-thumb").attr("href")
                movies.add(
                    Movie(
                        entryTitle,
                        originalTitle,
                        url,
                        imgUrl,
                        status
                    )
                )
            }
            Observable.just<List<Movie>>(movies)
        }
    }
}