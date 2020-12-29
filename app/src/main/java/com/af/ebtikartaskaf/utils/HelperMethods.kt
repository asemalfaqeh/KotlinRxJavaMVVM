package com.af.ebtikartaskaf.utils

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.af.ebtikartaskaf.R
import com.af.ebtikartaskaf.datasource.ApiConstants
import com.af.ebtikartaskaf.model.dto.PersonDto
import com.bumptech.glide.Glide
import java.util.*

//AF//
object HelperMethods {

    private const val TAG = "MyDownloadManager"

    fun loadPhoto(context: Context?, path: String?, view: ImageView?) {
        if (path != null) {
            Glide.with(context!!).load(ApiConstants.BASE_URL_PHOTO_PATH + path).into(view!!)
        }
    }

    //AF//
    // Download Image //
    fun startDownloadManger(activity: Activity, personDto: PersonDto) {
        val path = ApiConstants.BASE_URL_PHOTO_PATH + personDto.personImagePath
        Toast.makeText(activity, "Download Started.!", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "startDownloadManger: " + personDto.personImagePath)
        val downloadManager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(path)
        val request = DownloadManager.Request(uri)
        //Restrict the types of networks over which this download may proceed.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(personDto.personName)
        //Set a description of this download, to be displayed in notifications (if enabled)
        request.setDescription(personDto.personName + " Download Image")
        request.setNotificationVisibility(1)
        //Set the local destination for the downloaded file to a path within the application's external files directory
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES, "$randomName.jpg")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // to notify when download is complete
        request.allowScanningByMediaScanner() // if you want to be available from media players
        // Enqueue a new download and same the referenceId
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        request.setVisibleInDownloadsUi(true)
        // enqueue download ////////
        val downloadRef = downloadManager.enqueue(request)
        Log.d(TAG, "startDownloadManger: $downloadRef")
    }

    fun showToast(context: Context?, msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    // generate random image file name as a string //
    private val randomName: String
        get() {
            val alphabet = "abcdefghijklmnopqrstuvwxyz"
            val random = Random()
            val stringBuilder = StringBuilder()
            for (i in alphabet.indices) {
                stringBuilder.append(alphabet[random.nextInt(alphabet.length)])
            }
            return stringBuilder.toString()
        }
}