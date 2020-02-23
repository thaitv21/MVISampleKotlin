package com.nullexcom.mvisamplekotlin.usecase

import com.nullexcom.mvisamplekotlin.models.Movie
import io.reactivex.Observable
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*

class SearchUseCase {
    fun enqueue(keyword: String?): Observable<List<Movie>> {
        return Observable.defer {
            val movies: MutableList<Movie> = ArrayList()
            try {
                val document =
                    Jsoup.connect("http://xemphimplus.net/wp-content/themes/halimmovies/halim-ajax.php")
                        .data("action", "halim_ajax_live_search")
                        .data("search", keyword)
                        .post()
                for (element in document.select(".exact_result")) {
                    val entryTitle = element.select(".label").text()
                    val originalTitle = element.select(".enName").text()
                    val url = element.select("a").attr("href")
                    val imgUrl = element.select("img").attr("src")
                    val date = element.select(".date").text()
                    val movie = Movie(
                        entryTitle,
                        originalTitle,
                        url,
                        imgUrl,
                        ""
                    )
                    movie.date = date
                    movies.add(movie)
                }
                println(movies)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Observable.just<List<Movie>>(movies)
        }
    }
}