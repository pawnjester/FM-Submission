package com.example.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ui.databinding.FragmentUserDetailBinding
import com.example.ui.model.UserModel
import com.example.ui.utils.loadUrl
import com.example.ui.utils.observe
import com.example.ui.utils.show
import com.example.ui.utils.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private val userArgs: UserDetailFragmentArgs by navArgs()

    private var _binding: FragmentUserDetailBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userArgs.let {
            viewModel.setUserDetailId(it.id)
        }

        viewModel.getASingleUser()

        observe(viewModel.userDetailResult, ::observeUser)

        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun observeUser(user: LatestUiState<UserModel>?) {
        user?.let {
            when (it) {

                is LatestUiState.Loading -> {
                    binding.shimmerFrameLayout.startShimmer()
                }
                is LatestUiState.Success -> {
                    initializeView(it.users)
                }
                is LatestUiState.Error -> {
                    binding.shimmerFrameLayout.stopShimmer()
                    showToast(it.exception)
                }
            }
        }
    }

    private fun initializeView(user: UserModel) {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.show(false)
        val userDetail = "${user.title.capitalize()}, ${user.lastName} ${user.firstName}"
        binding.userName.text = userDetail
        binding.email.text = "email: ${user.email}"
        binding.imgUser.loadUrl(user.picture, false)
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        binding.shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}