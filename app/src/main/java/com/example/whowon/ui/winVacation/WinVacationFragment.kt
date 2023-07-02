package com.example.whowon.ui.winVacation

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
import com.example.whowon.databinding.FragmentWinVacationBinding
import com.example.whowon.model.Raffle

class WinVacationFragment : Fragment() , RaffleAdapter.OnItemClickListener {

    private var adapter: RaffleAdapter? = null
    private var _binding: FragmentWinVacationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWinVacationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val winVacationViewModel = ViewModelProvider(this)[WinVacationViewModel::class.java]

        binding.winVacationRecyclerView.setHasFixedSize(true)
        binding.winVacationRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RaffleAdapter(arrayListOf(),this)

        winVacationViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->
            adapter=RaffleAdapter(raffles,this)
            binding.winVacationRecyclerView.adapter=adapter
        }
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