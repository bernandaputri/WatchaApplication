package com.putri.watchaapplication.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.putri.watchaapplication.R
import com.putri.watchaapplication.databinding.FragmentFavMovieBinding
import com.putri.watchaapplication.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavMovieBinding
    private lateinit var favMovieAdapter: FavMovieAdapter
    private lateinit var favMovieViewModel: FavMovieViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvMovie)

        if (activity != null) {
            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            favMovieViewModel = ViewModelProvider(this, viewModelFactory)[FavMovieViewModel::class.java]

            favMovieAdapter = FavMovieAdapter()

            binding.progressBar.visibility = View.VISIBLE
            favMovieViewModel.getFavMovies().observe(viewLifecycleOwner, { favMovie ->
                binding.progressBar.visibility = View.GONE
                if (favMovie != null) {
                    favMovieAdapter.submitList(favMovie)
                }
            })

//            binding.rvMovie.adapter = favMovieAdapter
//            binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
//            binding.rvMovie.setHasFixedSize(true)

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMovieAdapter
            }

        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favMovieAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { favMovieViewModel.setFavMovie(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) {
                    movieEntity?.let { favMovieViewModel.setFavMovie(it) }
                }
                snackbar.show()
            }
        }
    })

}