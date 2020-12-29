package com.af.ebtikartaskaf.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.af.ebtikartaskaf.R
import com.af.ebtikartaskaf.databinding.HomeFragmentBinding
import com.af.ebtikartaskaf.interfaces.ItemClickInterface
import com.af.ebtikartaskaf.model.dto.PersonDto
import com.af.ebtikartaskaf.ui.adapter.HomePopularPersonAdapter
import com.af.ebtikartaskaf.utils.GlobalData
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/*
 Asem Alfaqeh Developer //
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {
    /*
    (..)
    /|\
     /\
     */
    private var mViewModel: HomeViewModel? = null
    private var homeFragmentBinding: HomeFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        homeFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.home_fragment, container, false)
        return homeFragmentBinding?.root
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d(TAG, "onViewCreated202: ")
        // if global data personDtos list equal null get it from api ////
        if (GlobalData.personDtos == null) {
            mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
            mViewModel!!.popularPersonsViewModel.observe(requireActivity(),
                    { personDtos: List<PersonDto?>? -> initRvPersonsPopularData(personDtos as ArrayList<PersonDto>?) })
        } else {
            /// global data have personsDtos List ///////
            initRvPersonsPopularData(GlobalData.personDtos)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        ///clear disposable /////////////
        mViewModel!!.clearDisposables()
        GlobalData.personDtos = null
    }

    // load data //
    private fun initRvPersonsPopularData(personDtos: ArrayList<PersonDto>?) {

        GlobalData.personDtos = personDtos
        Log.d(TAG, "initRvPersonsPopularData: ${personDtos?.size}")

        val homePopularPersonAdapter = HomePopularPersonAdapter(personDtos, object : ItemClickInterface<PersonDto> {

            override fun onItemClickListener(t: PersonDto, position: Int) {
                val bundle = Bundle()
                bundle.putSerializable(GlobalData.PERSON_DTO, t)
                // Navigate PersonInfoFragment and put person dto arg ///////
                Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_navigation_person_info, bundle)
            }

        })

        // span count and vertical gird layout //
        this.homeFragmentBinding!!.rvPopularPerson.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        this.homeFragmentBinding!!.rvPopularPerson.adapter = homePopularPersonAdapter
        this.homeFragmentBinding!!.rvPopularPerson.setHasFixedSize(true)

    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}