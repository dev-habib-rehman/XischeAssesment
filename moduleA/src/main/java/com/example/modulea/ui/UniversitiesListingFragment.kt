package com.example.modulea.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.common.utils.showToast
import com.example.modulea.R
import com.example.modulea.databinding.FragmentUniversitiesListingBinding
import com.example.modulea.viewmodel.UniversitiesListVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversitiesListingFragment : Fragment() {

    private lateinit var binding: FragmentUniversitiesListingBinding
    private val viewModel: UniversitiesListVM by viewModels()
    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentUniversitiesListingBinding?>(
            inflater, R.layout.fragment_universities_listing, container, false
        ).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkDeepLinkAndRefresh()
        setObservers()
    }

    private fun checkDeepLinkAndRefresh() {
        if (id.isNullOrEmpty().not() && id.equals("1")) {
            viewModel.getUniversities(isRefresh = true)
        } else viewModel.getUniversities()
    }


    private fun setObservers() {
        viewModel.apply {
            onUniversityItemClick.observe(viewLifecycleOwner) {
                navToDetails(it.id)
            }
            showMsg.observe(viewLifecycleOwner) {
                showToast(it)
            }
        }
    }

    private fun navToDetails(id: Int?) {
        val request =
            NavDeepLinkRequest.Builder.fromUri("android-app://com.example.moduleb/details/${id}".toUri())
                .build()
        findNavController().navigate(request)
    }
}