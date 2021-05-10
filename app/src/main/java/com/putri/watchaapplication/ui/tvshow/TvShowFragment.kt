package com.putri.watchaapplication.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.putri.watchaapplication.databinding.FragmentTvShowBinding
import com.putri.watchaapplication.viewmodel.ViewModelFactory

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

            binding.progressBar.visibility = View.VISIBLE
            showViewModel.getShows().observe(viewLifecycleOwner, { shows ->
                binding.progressBar.visibility = View.GONE
                tvShowAdapter.setShows(shows)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(binding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

}