package com.formacion.buscadordepeliculas

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.formacion.buscadordepeliculas.api.MovieDetail
import com.formacion.buscadordepeliculas.api.RetrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvYear: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvRuntime: TextView
    private lateinit var tvDirector: TextView
    private lateinit var tvCountry: TextView
    private lateinit var tvPlot: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivPoster = findViewById(R.id.ivDetailPoster)
        tvTitle = findViewById(R.id.tvDetailTitle)
        tvYear = findViewById(R.id.tvDetailYear)
        tvGenre = findViewById(R.id.tvDetailGenre)
        tvRuntime = findViewById(R.id.tvDetailRuntime)
        tvDirector = findViewById(R.id.tvDetailDirector)
        tvCountry = findViewById(R.id.tvDetailCountry)
        tvPlot = findViewById(R.id.tvDetailPlot)

        val imdbId = intent.getStringExtra("imdbID")
        if (imdbId.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.err_invalid_id), Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        fetchMovieDetail(imdbId)
    }

    private fun fetchMovieDetail(imdbId: String) {
        val apiKey = BuildConfig.OMDB_API_KEY
        RetrofitClient.instance.getMovieDetail(imdbId, apiKey).enqueue(
            object : Callback<MovieDetail> {
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    if (response.isSuccessful) {
                        val movieDetail = response.body()
                        if ((movieDetail != null) && (movieDetail.response == "True")) {
                            displayMovieDetail(movieDetail)
                        } else {
                            val errorMsg = movieDetail?.error ?: getString(R.string.err_no_detail)
                            Toast.makeText(this@DetailActivity, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("OmdbDetail", "HTTP error code: ${response.code()}, message: ${response.message()}")
                        Toast.makeText(this@DetailActivity, getString(R.string.err_server), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    val errorMsg = getString(R.string.err_connection, t.message ?: "")
                    Toast.makeText(this@DetailActivity, errorMsg, Toast.LENGTH_SHORT).show()
                }
            },
        )
    }

    private fun displayMovieDetail(detail: MovieDetail) {
        val notAvailable = getString(R.string.not_available)
        tvTitle.text = detail.title ?: notAvailable
        tvYear.text = getString(R.string.detail_year, detail.year ?: notAvailable)
        tvGenre.text = getString(R.string.detail_genre, detail.genre ?: notAvailable)
        tvRuntime.text = getString(R.string.detail_runtime, detail.runtime ?: notAvailable)
        tvDirector.text = getString(R.string.detail_director, detail.director ?: notAvailable)
        tvCountry.text = getString(R.string.detail_country, detail.country ?: notAvailable)
        tvPlot.text = getString(R.string.detail_plot, detail.plot ?: notAvailable)

        val posterUrl = detail.poster
        if (!posterUrl.isNullOrEmpty() && (posterUrl != "N/A")) {
            Picasso.get()
                .load(posterUrl)
                .into(ivPoster)
        } else {
            ivPoster.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}
