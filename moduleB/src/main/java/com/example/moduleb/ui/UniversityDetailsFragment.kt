package com.example.moduleb.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.common.utils.showToast
import com.example.moduleb.R
import com.example.moduleb.databinding.FragmentUniversityDetailsBinding
import com.example.moduleb.viewmodel.UniversityDetailsVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversityDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUniversityDetailsBinding
    private val viewModel: UniversityDetailsVM by viewModels()
    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
        } ?: run { errorId() }
        if (id.isNullOrEmpty()) errorId()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentUniversityDetailsBinding?>(
            inflater, R.layout.fragment_university_details, container, false
        ).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUniversities(id!!.toInt())
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            btnRefreshUniversityDetails.setOnClickListener {
                navToListing()
            }
        }
    }

    private fun initObservers() {
        viewModel.apply {
            showMsg.observe(viewLifecycleOwner) {
                showToast(it)
            }
        }
    }

    private fun navToListing() {
        val request =
            NavDeepLinkRequest.Builder
                .fromUri("android-app://com.example.modulea/refresh/1".toUri())
                .build()
        findNavController().popBackStack(R.id.details_nav_graph,false)
        findNavController().navigate(request)
    }

    private fun errorId() {
        showToast("Invalid Id ")
        findNavController().popBackStack()
    }
}