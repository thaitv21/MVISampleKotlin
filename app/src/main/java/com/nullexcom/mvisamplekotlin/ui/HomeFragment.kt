package com.nullexcom.mvisamplekotlin.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import com.jakewharton.rxbinding3.widget.editorActions
import com.nullexcom.mvisamplekotlin.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var attachObservable: Subject<Boolean> = PublishSubject.create()
    private lateinit var adapter: ResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ResultAdapter(context!!)
        rvResult.layoutManager = LinearLayoutManager(context)
        rvResult.adapter = adapter

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, Observer<HomeViewState> { render(it) })
        HomeViewIntent.bind(this, viewModel)
        attachObservable.onNext(true)
    }

    fun doOnStart(): Observable<Boolean> {
        return attachObservable
    }

    fun doOnTextChanged(): Observable<String> {
        return edtKeyword.afterTextChangeEvents().map { it.editable }.map { it.toString() }
    }

    fun doOnClickSearch(): Observable<Unit> {
        return edtKeyword.editorActions { it == EditorInfo.IME_ACTION_SEARCH }.map { }
            .mergeWith(imgSearch.clicks())
    }

    private fun render(state: HomeViewState) {
        when (state) {
            is HomeViewState.LoadingState -> {
                renderLoading()
            }
            is HomeViewState.ErrorState -> {
                renderError()
            }
            is HomeViewState.DataState -> {
                renderData(state)
            }
        }
    }

    private fun renderLoading() {
        layoutLoading.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }

    private fun renderError() {
        layoutLoading.visibility = View.GONE
        layoutError.visibility = View.VISIBLE
    }

    private fun renderData(state: HomeViewState.DataState) {
        layoutLoading.visibility = View.GONE
        layoutError.visibility = View.GONE
        adapter.refresh(state.movies)
    }
}
