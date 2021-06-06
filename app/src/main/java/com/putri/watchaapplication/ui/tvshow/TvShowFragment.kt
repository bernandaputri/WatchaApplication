package com.putri.watchaapplication.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.putri.watchaapplication.databinding.FragmentTvShowBinding
import com.putri.watchaapplication.viewmodel.ViewModelFactory
import com.putri.watchaapplication.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val viewModelFactory = ViewModelFactory.getInstance(requireContext())
            val showViewModel = ViewModelProvider(this, viewModelFactory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            showViewModel.getShows().observe(viewLifecycleOwner, { shows ->
                if (shows != null){
                    when (shows.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            tvShowAdapter.submitList(shows.data)
                            binding.progressBar.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error to load data.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

}