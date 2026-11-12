package com.formacion.buscadordepeliculas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formacion.buscadordepeliculas.api.MovieItem
import com.formacion.buscadordepeliculas.api.RetrofitClient
import com.formacion.buscadordepeliculas.api.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etSearchQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etSearchQuery = findViewById(R.id.etSearchQuery)
        btnSearch = findViewById(R.id.btnSearch)
        rvMovies = findViewById(R.id.rvMovies)

        movieAdapter = MovieAdapter(emptyList()) { movie ->
            onMovieSelected(movie)
        }

        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.adapter = movieAdapter

        btnSearch.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        val query = etSearchQuery.text.toString().trim()
        if (query.isEmpty()) {
            Toast.makeText(this, getString(R.string.err_empty_query), Toast.LENGTH_SHORT).show()
            return
        }

        val apiKey = BuildConfig.OMDB_API_KEY
        RetrofitClient.instance.searchMovies(query, apiKey).enqueue(
            object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (response.isSuccessful) {
                        val searchResponse = response.body()
                        if ((searchResponse != null) && (searchResponse.response == "True") && (!searchResponse.search.isNullOrEmpty())) {
                            movieAdapter.updateMovies(searchResponse.search)
                        } else {
                            movieAdapter.updateMovies(emptyList())
                            val errorMsg = searchResponse?.error ?: getString(R.string.err_no_movies)
                            Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("OmdbSearch", "HTTP error code: ${response.code()}, message: ${response.message()}")
                        movieAdapter.updateMovies(emptyList())
                        Toast.makeText(this@MainActivity, getString(R.string.err_server), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    movieAdapter.updateMovies(emptyList())
                    val errorMsg = getString(R.string.err_connection, t.message ?: "")
                    Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_SHORT).show()
                }
            },
        )
    }

    private fun onMovieSelected(movie: MovieItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("imdbID", movie.imdbID)
        startActivity(intent)
    }
}
