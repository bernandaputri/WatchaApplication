package com.putri.watchaapplication.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.putri.watchaapplication.R
import com.putri.watchaapplication.data.entity.DetailMediaEntity
import com.putri.watchaapplication.data.entity.MediaEntity
import com.putri.watchaapplication.databinding.ActivityDetailBinding
import com.putri.watchaapplication.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityDetailBinding
//    private lateinit var result: DetailMediaEntity
    private var isAdd = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val mediaId = extras.getInt("id", 0)
            if (mediaId != null) {
                binding.progressBar.visibility = View.VISIBLE
                when (intent.getStringExtra(EXTRA_TYPE)) {
                    "movie" -> {
                        viewModel.selectedMovie(mediaId)
//                        result = viewModel.getDetailMovie()
                        viewModel.getDetailMovie().observe(this, { detail ->
                            binding.progressBar.visibility = View.GONE
                            populateDetail(detail) })
                    }
                    "tvShow" -> {
                        viewModel.selectedTvShow(mediaId)
//                        result = viewModel.getDetailTvShow()
                        viewModel.getDetailTvShow().observe(this, { detail ->
                            binding.progressBar.visibility = View.GONE
                            populateDetail(detail) })
                    }
                }
            }
        }

//        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
//
//        val viewModelFactory = ViewModelFactory.getInstance(this)
//        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
//
//        val mediaId = intent.getStringExtra(EXTRA_DATA)
//
//        when (intent.getStringExtra(EXTRA_TYPE)) {
//            "movie" -> {
//                mediaId?.let { viewModel.selectedMovie(mediaId) }
//                result = viewModel.getDetailMovie()
//            }
//            "tvShow" -> {
//                mediaId?.let { viewModel.selectedTvShow(it) }
//                result = viewModel.getDetailTvShow()
//            }
//        }
//
//        with(binding) {
//            collapseToolbar.title = result.mediaTitle
//
//            Glide.with(this@DetailActivity)
//                .load(result.mediaPoster)
//                .into(imgMedia)
//
//            tvOverview.text = result.mediaDesc
//            tvGenre.text = result.mediaGenres
//            tvDate.text = result.mediaRelease
//        }

        binding.btnSave.setOnClickListener(this)
        binding.btnShareMedia.setOnClickListener(this)
    }

    private fun populateDetail(result: DetailMediaEntity) {
        with(binding) {
            collapseToolbar.title = result.mediaTitle

            Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500" + result.mediaPoster)
                    .into(imgMedia)

            tvOverview.text = result.mediaDesc
            tvGenre.text = result.mediaGenres
            tvDate.text = result.mediaRelease
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                if (isAdd) {
                    binding.btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add,0,0,0)
                    binding.btnSave.setText(R.string.add_to_watchlist)
                    Toast.makeText(this, getString(R.string.btn_added), Toast.LENGTH_SHORT).show()
                    isAdd = false
                } else {
                    binding.btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check,0,0,0)
                    binding.btnSave.setText(R.string.added_to_watchlist)
                    Toast.makeText(this, getString(R.string.btn_add), Toast.LENGTH_SHORT).show()
                    isAdd = true
                }
            }
            R.id.btn_share_media -> {
                Toast.makeText(this, getString(R.string.btn_share), Toast.LENGTH_SHORT).show()
            }
        }
    }
}