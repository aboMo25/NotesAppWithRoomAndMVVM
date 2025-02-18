package com.example.notesappwithroommvvm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesappwithroommvvm.MainActivity
import com.example.notesappwithroommvvm.R
import com.example.notesappwithroommvvm.adapter.NoteAdapter
import com.example.notesappwithroommvvm.databinding.FragmentHome2Binding
import com.example.notesappwithroommvvm.model.Note
import com.example.notesappwithroommvvm.viewmodel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home2),SearchView.OnQueryTextListener,MenuProvider {

    private var homeBinding:FragmentHome2Binding ?= null
    private val binding get() = homeBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         homeBinding=FragmentHome2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val menuHost:MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        notesViewModel=(activity as MainActivity).noteViewModel

        setupHomeRecyclerView()

        binding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_addFragment)
        }

    }

    private fun updateUI(note: List<Note>?){
        if (note != null){
            if (note.isNotEmpty()){
                binding.emptyNotesImage.visibility=View.GONE
                binding.homeRecyclerView.visibility=View.VISIBLE
            } else {
                binding.emptyNotesImage.visibility=View.VISIBLE
                binding.homeRecyclerView.visibility=View.GONE
            }
        }
    }

    private fun setupHomeRecyclerView(){
        notesAdapter= NoteAdapter()
        binding.homeRecyclerView.apply {
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter=notesAdapter
        }
        activity?.let {
            notesViewModel.getAllData().observe(viewLifecycleOwner){note ->
                notesAdapter.differ.submitList(note)
                updateUI(note)

            }
        }
    }

    private fun searchNote(query: String?){
        val searchQuery= "%$query"

        notesViewModel.searchNote(searchQuery).observe(this){ list ->
            notesAdapter.differ.submitList(list)
        }


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchNote(newText)
        }
        return true
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu,menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled=false
        menuSearch.setOnQueryTextListener(this)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return  false
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding=null
    }
}