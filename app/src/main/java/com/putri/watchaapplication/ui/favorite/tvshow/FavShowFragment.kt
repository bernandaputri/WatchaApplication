package com.putri.watchaapplication.ui.favorite.tvshow

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
import com.putri.watchaapplication.databinding.FragmentFavShowBinding
import com.putri.watchaapplication.viewmodel.ViewModelFactory

class FavShowFragment : Fragment() {

    private lateinit var binding: FragmentFavShowBinding
    private lateinit var favTvShowAdapter: FavShowAdapter
    private lateinit var favTvShowViewModel: FavShowViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvTvShow)

        if (activity != null) {
            val viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            favTvShowViewModel = ViewModelProvider(this, viewModelFactory)[FavShowViewModel::class.java]

            favTvShowAdapter = FavShowAdapter()

            binding.progressBar.visibility = View.VISIBLE
            favTvShowViewModel.getFavShow().observe(viewLifecycleOwner, { favShow ->
                binding.progressBar.visibility = View.GONE
                if (favShow != null) {
                    favTvShowAdapter.submitList(favShow)
                }
            })

//            binding.rvTvShow.adapter = favTvShowAdapter
//            binding.rvTvShow.layoutManager = LinearLayoutManager(requireContext())
//            binding.rvTvShow.setHasFixedSize(true)

            with(binding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favTvShowAdapter
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
                val showEntity = favTvShowAdapter.getSwipedData(swipedPosition)
                showEntity?.let { favTvShowViewModel.setFavShow(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) {
                    showEntity?.let { favTvShowViewModel.setFavShow(it) }
                }
                snackbar.show()
            }
        }
    })

}