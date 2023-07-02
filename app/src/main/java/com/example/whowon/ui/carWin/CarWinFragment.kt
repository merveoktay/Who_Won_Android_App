package com.example.whowon.ui.carWin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whowon.ui.raffleDetail.DetailActivity
import com.example.whowon.utils.Util.Companion._raffle
import com.example.whowon.adapter.RaffleAdapter
import com.example.whowon.databinding.FragmentCarWinBinding
import com.example.whowon.model.Raffle


class CarWinFragment : Fragment(),RaffleAdapter.OnItemClickListener {
    private var adapter: RaffleAdapter? = null
    private var _binding: FragmentCarWinBinding? = null
    private lateinit var _navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarWinBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carWinViewModel = ViewModelProvider(this)[CarWinViewModel::class.java]

        binding.carWinRecyclerView.setHasFixedSize(true)
        binding.carWinRecyclerView.layoutManager = LinearLayoutManager(context)
        _navController = Navigation.findNavController(view)
        adapter = RaffleAdapter(arrayListOf(),this)

        carWinViewModel.raffleList.observe(viewLifecycleOwner) { raffles ->
            adapter=RaffleAdapter(raffles,this)
            binding.carWinRecyclerView.adapter=adapter
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