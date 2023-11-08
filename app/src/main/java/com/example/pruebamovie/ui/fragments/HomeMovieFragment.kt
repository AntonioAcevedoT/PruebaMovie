package com.example.pruebamovie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pruebamovie.databinding.FragmentHomeMovieBinding
import com.example.pruebamovie.ui.adapter.AdapterMovies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMovieFragment : Fragment() {

/*    private var _binding:FragmentHomeMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AdapterMovies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager( requireContext(), 3)
        binding.rvPeliculas.layoutManager = layoutManager
        adapter = AdapterMovies(requireContext(), arrayListOf())
        binding.rvPeliculas.adapter= adapter
    }*/
}