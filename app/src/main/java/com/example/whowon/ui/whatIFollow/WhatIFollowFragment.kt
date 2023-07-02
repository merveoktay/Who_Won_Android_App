package com.example.whowon.ui.whatIFollow

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whowon.ui.raffleDetail.DetailActivity
import com.example.whowon.utils.Util.Companion._raffle
import com.example.whowon.adapter.RaffleAdapter
import com.example.whowon.databinding.FragmentWhatIFollowBinding
import com.example.whowon.model.Raffle

class WhatIFollowFragment : Fragment(), RaffleAdapter.OnItemClickListener {
    private var adapter: RaffleAdapter? = null
    private var _binding: FragmentWhatIFollowBinding? = null
    private val binding get() = _binding!!
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWhatIFollowBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.whatIFollowRecyclerView.setHasFixedSize(true)
        binding.whatIFollowRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = RaffleAdapter(arrayListOf(),this)
        binding.whatIFollowRecyclerView.adapter=adapter

        val whatIFollowViewModel = ViewModelProvider(this)[WhatIFollowViewModel::class.java]
        whatIFollowViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->

            adapter=RaffleAdapter(raffles,this)
           // adapter!!.notifyDataSetChanged()
            binding.whatIFollowRecyclerView.adapter=adapter
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        adapter!!.notifyDataSetChanged()
        val whatIFollowViewModel = ViewModelProvider(this)[WhatIFollowViewModel::class.java]
        whatIFollowViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->

            adapter=RaffleAdapter(raffles,this)
          //  adapter!!.notifyDataSetChanged()
            binding.whatIFollowRecyclerView.adapter=adapter
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        adapter!!.notifyDataSetChanged()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onItemClick(raffle: Raffle) {
        _raffle =raffle
        val intent = Intent(requireContext(), DetailActivity::class.java)
        startActivity(intent)
    }
}