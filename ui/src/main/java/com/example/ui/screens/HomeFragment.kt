package com.example.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.adapter.UserAdapter
import com.example.ui.databinding.FragmentHomeBinding
import com.example.ui.model.UserModel
import com.example.ui.utils.MarginItemDecoration
import com.example.ui.utils.observe
import com.example.ui.utils.show
import com.example.ui.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var recipeAdapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.getUsersList()

        observe(viewModel.usersResult, ::observeUsersList)

        recipeAdapter.userCallback = {
            val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(it.id)
            findNavController().navigate(action)
        }

    }

    private fun setupRecyclerView() {
        binding.rvUsers.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )
        binding.rvUsers.addItemDecoration(MarginItemDecoration(16))
        binding.rvUsers.adapter = recipeAdapter
    }

    private fun observeUsersList(users: LatestUiState<List<UserModel>>?) {
        users?.let {
            when (it) {

                is LatestUiState.Loading -> {
                    binding.shimmerRecycler.startShimmer()
                }
                is LatestUiState.Success -> {
                    binding.shimmerRecycler.stopShimmer()
                    binding.shimmerRecycler.show(false)
                    recipeAdapter.setUserData(it.users)
                }
                is LatestUiState.Error -> {
                    binding.shimmerRecycler.stopShimmer()
                    binding.shimmerRecycler.show(false)
                    showToast(it.exception)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}