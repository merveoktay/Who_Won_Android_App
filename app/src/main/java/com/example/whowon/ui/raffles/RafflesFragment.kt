package com.example.whowon.ui.raffles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whowon.ui.raffleDetail.DetailActivity
import com.example.whowon.utils.Util.Companion._raffle
import com.example.whowon.adapter.RaffleAdapter
import com.example.whowon.databinding.FragmentRafflesBinding
import com.example.whowon.model.Raffle

class RafflesFragment : Fragment(), RaffleAdapter.OnItemClickListener {
    private var adapter: RaffleAdapter? = null
    private var _binding: FragmentRafflesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRafflesBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rafflesViewModel = ViewModelProvider(this)[RafflesViewModel::class.java]

        binding.rafflesRecyclerView.setHasFixedSize(true)
        binding.rafflesRecyclerView.layoutManager = LinearLayoutManager(context)

        rafflesViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->
            adapter=RaffleAdapter(raffles,this)
            binding.rafflesRecyclerView.adapter=adapter
            adapter!!.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(raffle: Raffle) {
        _raffle=raffle
        val intent = Intent(requireContext(), DetailActivity::class.java)
        startActivity(intent)
    }

}
