package id.anantyan.todolistviapulsa.presentation.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.todolistviapulsa.common.State
import id.anantyan.todolistviapulsa.common.ValidationType
import id.anantyan.todolistviapulsa.common.onValidation
import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.databinding.FragmentPostBinding
import id.anantyan.todolistviapulsa.presentation.ui.list.ListViewModel

/**
 * Created by Arya Rezza Anantya on 09/06/24.
 */
@AndroidEntryPoint
class PostFragment : BottomSheetDialogFragment() {

    private val viewModel: PostViewModel by viewModels()
    private val listViewModel: ListViewModel by activityViewModels()
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val args: PostFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObserver()
        bindView()
    }

    private fun bindView() {
        binding.btnSave.setOnClickListener {
            requireContext().onValidation(
                validationParams = arrayOf(
                    binding.tilTitleJob to ValidationType.GENERAL,
                    binding.tilDescription to ValidationType.GENERAL,
                    binding.tilPersonName to ValidationType.GENERAL
                )
            ) {
                val titleJob = binding.inputTitleJob.text.toString()
                val description = binding.inputDescriptionJob.text.toString()
                val personName = binding.inputPersonName.text.toString()
                val value = RequestModel(description, personName, titleJob)
                viewModel.postput(args.id, value)
            }
        }
    }

    private fun bindObserver() {
        viewModel.fetch.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    binding.apply {
                        inputTitleJob.setText(state.data?.title)
                        inputDescriptionJob.setText(state.data?.description)
                        inputPersonName.setText(state.data?.personName)
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.post.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), "Success saved!", Toast.LENGTH_LONG).show()
                    listViewModel.fetchAll()
                    dialog?.dismiss()
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.put.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), "Success saved!", Toast.LENGTH_LONG).show()
                    listViewModel.fetchAll()
                    dialog?.dismiss()
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.fetch(args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}