package com.formacion.buscadordepeliculas

import android.os.Bundle
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
            Toast.makeText(this, "Identificador de película no válido", Toast.LENGTH_SHORT).show()
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
                        if (movieDetail != null && (movieDetail.response == "True")) {
                            displayMovieDetail(movieDetail)
                        } else {
                            val errorMsg = movieDetail?.error ?: "No se pudo obtener el detalle de la película"
                            Toast.makeText(this@DetailActivity, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@DetailActivity, "Error al obtener respuesta del servidor", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            },
        )
    }

    private fun displayMovieDetail(detail: MovieDetail) {
        tvTitle.text = detail.title ?: "N/A"
        tvYear.text = "Año: ${detail.year ?: "N/A"}"
        tvGenre.text = "Género: ${detail.genre ?: "N/A"}"
        tvRuntime.text = "Duración: ${detail.runtime ?: "N/A"}"
        tvDirector.text = "Director: ${detail.director ?: "N/A"}"
        tvCountry.text = "País: ${detail.country ?: "N/A"}"
        tvPlot.text = "Sinopsis: ${detail.plot ?: "N/A"}"

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
