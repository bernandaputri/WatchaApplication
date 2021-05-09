package com.putri.watchaapplication.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.putri.watchaapplication.R
import com.putri.watchaapplication.databinding.FragmentMovieBinding
import com.putri.watchaapplication.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val viewModelFactory = ViewModelFactory.getInstance(requireContext())
            val movieViewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            movieViewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}