package com.af.ebtikartaskaf.ui.fragments.personinfo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.af.ebtikartaskaf.R
import com.af.ebtikartaskaf.databinding.PersonInfoFragmentBinding
import com.af.ebtikartaskaf.model.dto.PersonDto
import com.af.ebtikartaskaf.utils.GlobalData
import com.af.ebtikartaskaf.utils.HelperMethods

class PersonInfoFragment : Fragment() {

    private var personDto: PersonDto? = null
    private lateinit var personInfoFragmentBinding: PersonInfoFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) { // get person dto //
            personDto = requireArguments().getSerializable(GlobalData.PERSON_DTO) as PersonDto?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        personInfoFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.person_info_fragment, container, false)
        return personInfoFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel
        val personInfoViewModel = ViewModelProvider(this).get(PersonInfoViewModel::class.java)
        personDto?.let {
            personInfoViewModel.getPersonDtoViewModel(it).observe(requireActivity(),
                    { personDto: PersonDto ->
                        HelperMethods.loadPhoto(requireContext(), personDto.personImagePath, personInfoFragmentBinding.imvPerson)
                        personInfoFragmentBinding.btnSaveImg.setOnClickListener {
                            if (checkWriteFilePermission()) {
                                HelperMethods.startDownloadManger(requireActivity(), personDto)
                                savedInstanceState?.putSerializable(GlobalData.PERSON_DTO, personDto)
                            }
                        }
                        personInfoFragmentBinding.btnBack.setOnClickListener { requireActivity().onBackPressed() }
                    })
        }
    }


    // check required permissions //
    private fun checkWriteFilePermission() : Boolean {
        return if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkWriteFilePermission: " + " Permission Granted.!")
            true
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted. Continue the action or workflow
            } else {
                HelperMethods.showToast(requireContext(), resources.getString(R.string.write_storage_permission_request_message))
            }
        }
    }

    companion object {
        private const val TAG = "PersonInfoFragment"
    }

}