package com.example.whowon.ui.freeParticipation

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
import com.example.whowon.databinding.FragmentFreeParticipationBinding
import com.example.whowon.model.Raffle

class FreeParticipationFragment : Fragment(), RaffleAdapter.OnItemClickListener {

    private var adapter: RaffleAdapter? = null
    private var _binding: FragmentFreeParticipationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFreeParticipationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val freeParticipationViewModel = ViewModelProvider(this)[FreeParticipationViewModel::class.java]

        binding.freeParticipaitonRecyclerView.setHasFixedSize(true)
        binding.freeParticipaitonRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RaffleAdapter(arrayListOf(),this)

        freeParticipationViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->
            adapter=RaffleAdapter(raffles,this)
            binding.freeParticipaitonRecyclerView.adapter=adapter
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