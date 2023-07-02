package com.example.whowon.ui.beginners

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whowon.ui.raffleDetail.DetailActivity
import com.example.whowon.utils.Util.Companion._raffle
import com.example.whowon.adapter.RaffleAdapter
import com.example.whowon.databinding.FragmentBeginnersBinding
import com.example.whowon.model.Raffle


class BeginnersFragment : Fragment(), RaffleAdapter.OnItemClickListener {

    private var adapter: RaffleAdapter? = null
    private var _binding: FragmentBeginnersBinding? = null
    private val binding get() = _binding!!
    private lateinit var _navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBeginnersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val beginnersViewModel = ViewModelProvider(this)[BeginnersViewModel::class.java]

        binding.beginnersRecyclerView.setHasFixedSize(true)
        binding.beginnersRecyclerView.layoutManager = LinearLayoutManager(context)
        _navController = Navigation.findNavController(view)
        adapter = RaffleAdapter(arrayListOf(),this)

        beginnersViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->
            adapter=RaffleAdapter(raffles,this)
            binding.beginnersRecyclerView.adapter=adapter
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