package com.putri.watchaapplication.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.putri.watchaapplication.R
import com.putri.watchaapplication.data.local.entity.MovieEntity
import com.putri.watchaapplication.data.local.entity.ShowEntity
import com.putri.watchaapplication.databinding.ActivityDetailBinding
import com.putri.watchaapplication.viewmodel.ViewModelFactory
import com.putri.watchaapplication.vo.Status

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var mediaType: String
    private var isAdded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        binding.btnSave.setOnClickListener(this)
        binding.btnShareMedia.setOnClickListener(this)

        val extras = intent.extras
        mediaType = intent.getStringExtra(EXTRA_TYPE).toString()
        if (extras != null) {
            val mediaId = extras.getInt("id", 0)
            if (mediaId != null) {
                when (mediaType) {
                    "movie" -> {
                        viewModel.selectedMovie(mediaId)
                        viewModel.getDetailMovie().observe(this, { detailMovie ->
                            when (detailMovie.status) {
                                Status.LOADING -> {
                                    binding.progressBar.visibility = View.VISIBLE
                                }
                                Status.SUCCESS -> if (detailMovie.data != null) {
                                    binding.progressBar.visibility = View.GONE
                                    populateDetailMovie(detailMovie.data)
                                    setupState()
                                }
                                Status.ERROR -> {
                                    binding.progressBar.visibility = View.INVISIBLE
                                    binding.scrollView.visibility = View.INVISIBLE
                                    Toast.makeText(applicationContext, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }
                    "tvShow" -> {
                        viewModel.selectedTvShow(mediaId)
                        viewModel.getDetailShow().observe(this, { detailShow ->
                            when (detailShow.status) {
                                Status.LOADING -> {
                                    binding.progressBar.visibility = View.VISIBLE
                                }
                                Status.SUCCESS -> if (detailShow.data != null) {
                                    binding.progressBar.visibility = View.GONE
                                    populateDetailShow(detailShow.data)
                                    setupState()
                                }
                                Status.ERROR -> {
                                    binding.progressBar.visibility = View.INVISIBLE
                                    binding.scrollView.visibility = View.INVISIBLE
                                    Toast.makeText(applicationContext, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }
                }
            }
        }
    }

    private fun setupState() {
        if (mediaType == "movie") {
            viewModel.getDetailMovie().observe(this, { favMovie ->
                when (favMovie.status) {
                    Status.SUCCESS -> {
                        if (favMovie.data != null) {
                            val movieState = favMovie.data.movieAdd
                            setFavoriteState(movieState)
                            isAdded = movieState
                        }
                    }
                }
            })
        } else if (mediaType == "tvShow") {
            viewModel.getDetailShow().observe(this, { favShow ->
                when (favShow.status) {
                    Status.SUCCESS -> {
                        if (favShow.data != null) {
                            val showState = favShow.data.showAdd
                            setFavoriteState(showState)
                            isAdded = showState
                        }
                    }
                }
            })
        }
    }

    private fun populateDetailShow(result: ShowEntity) {
        with(binding) {
            collapseToolbar.title = result.showTitle

            Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500" + result.showPoster)
                    .into(imgMedia)

            tvOverview.text = result.showDesc
            tvDate.text = result.showRelease
        }
    }

    private fun populateDetailMovie(result: MovieEntity) {
        with(binding) {
            collapseToolbar.title = result.movieTitle

            Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500" + result.moviePoster)
                    .into(imgMedia)

            tvOverview.text = result.movieDesc
            tvDate.text = result.movieRelease
        }

    }

    private fun setFavoriteState(state: Boolean) {
        val extras = intent.extras
        if (extras != null) {
            if (state) {
                binding.btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check,0,0,0)
                binding.btnSave.setText(R.string.added_to_watchlist)
            } else {
                binding.btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add,0,0,0)
                binding.btnSave.setText(R.string.add_to_watchlist)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                val extras = intent.extras
                if (extras != null) {
                    if (mediaType == "movie") {
                        if (isAdded) {
                            Toast.makeText(this, getString(R.string.btn_added), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, getString(R.string.btn_add), Toast.LENGTH_SHORT).show()
                        }
                        viewModel.setFavMovie()
                    } else if (mediaType == "tvShow") {
                        if (isAdded) {
                            Toast.makeText(this, getString(R.string.btn_added), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, getString(R.string.btn_add), Toast.LENGTH_SHORT).show()
                        }
                        viewModel.setFavShow()
                    }
                }
            }
            R.id.btn_share_media -> {
                Toast.makeText(this, getString(R.string.btn_share), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}