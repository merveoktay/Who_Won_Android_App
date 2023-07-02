package com.example.whowon.ui.winPhoneTablet

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
import com.example.whowon.databinding.FragmentWinPhoneTabletBinding
import com.example.whowon.model.Raffle

class WinPhoneTabletFragment : Fragment() , RaffleAdapter.OnItemClickListener {

    private var adapter: RaffleAdapter? = null
    private var _binding: FragmentWinPhoneTabletBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWinPhoneTabletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val winPhoneTabletViewModel = ViewModelProvider(this)[WinPhoneTabletViewModel::class.java]

        binding.winPhoneTabletRecyclerView.setHasFixedSize(true)
        binding.winPhoneTabletRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RaffleAdapter(arrayListOf(),this)

        winPhoneTabletViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->
            adapter=RaffleAdapter(raffles,this)
            binding.winPhoneTabletRecyclerView.adapter=adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(raffle: Raffle) {
        _raffle =raffle
        val intent = Intent(requireContext(), DetailActivity::class.java)
        startActivity(intent)
    }


}