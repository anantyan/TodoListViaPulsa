package id.anantyan.todolistviapulsa.presentation.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.todolistviapulsa.R
import id.anantyan.todolistviapulsa.common.State
import id.anantyan.todolistviapulsa.common.formatDateTime
import id.anantyan.todolistviapulsa.common.messageListDialog
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.databinding.FragmentListBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Arya Rezza Anantya on 09/06/24.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by activityViewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObserver()
        bindView()
    }

    private fun bindView() {
        binding.rvList.setHasFixedSize(false)
        binding.rvList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvList.itemAnimator = DefaultItemAnimator()
        binding.rvList.isNestedScrollingEnabled = false
        binding.rvList.adapter = adapter

        binding.toolbar.menu.findItem(R.id.action_add).setOnMenuItemClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToPostFragment(null))
            false
        }

        adapter.setOnItemClickListener(object : ListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: ResponseModel) {
                requireContext().messageListDialog(
                    "Pilih Menu",
                    listOf("View Detail", "Update", "Delete"),
                    onItemClick = { dialog, which ->
                        when (which) {
                            0 -> {
                                viewModel.fetch(item.id ?: "-1")
                            }
                            1 -> {
                                findNavController().navigate(
                                    ListFragmentDirections.actionListFragmentToPostFragment(
                                        item.id ?: "-1"
                                    )
                                )
                            }
                            2 -> {
                                viewModel.delete(item.id ?: "-1")
                            }
                        }
                    }
                )
            }
        })
    }

    private fun bindObserver() {
        viewModel.fetchAll.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.progressBarRv.isVisible = true
                    binding.rvList.isVisible = false
                }
                is State.Success -> {
                    binding.progressBarRv.isVisible = false
                    binding.rvList.isVisible = true
                    adapter.submitList(state.data ?: emptyList())
                }
                is State.Error -> {
                    binding.progressBarRv.isVisible = false
                    binding.rvList.isVisible = true
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.fetch.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.content.isVisible = false
                }
                is State.Success -> {
                    binding.progressBar.isVisible = false
                    binding.content.isVisible = true
                    binding.apply {
                        imgView.load(R.drawable.ic_account_circle) { size(ViewSizeResolver(imgView)) }
                        tvPersonName.text = state.data?.personName
                        tvJobTitle.text = state.data?.title
                        tvDescription.text = state.data?.description
                        tvDateTime.text = formatDateTime(state.data?.createdAt ?: "")
                    }
                }
                is State.Error -> {
                    binding.progressBar.isVisible = false
                    binding.content.isVisible = false
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.delete.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    Toast.makeText(requireContext(), "Success deleted!", Toast.LENGTH_LONG).show()
                    viewModel.fetchAll()
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.fetchAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}