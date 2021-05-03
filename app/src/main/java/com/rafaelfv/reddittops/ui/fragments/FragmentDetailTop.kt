package com.rafaelfv.reddittops.ui.fragments

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rafaelfv.reddittops.R
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.utils.*
import com.rafaelfv.reddittops.viewModel.ViewModelDetailTop
import kotlinx.android.synthetic.main.fragment_detail_top.*
import java.io.File
import java.io.File.separator
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class FragmentDetailTop : Fragment() {

    private var viewModel: ViewModelDetailTop? = null
    private var children: Children? = null
    private lateinit var callback: DetailTopCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelDetailTop::class.java)

        if (arguments?.getParcelable<Children>(KEY_CHILDREN) != null) {
            children = arguments?.getParcelable<Children>(KEY_CHILDREN) as Children
        }
        val a = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        children?.let { setupViews(it) }
        download_top_detail.setOnClickListener {
            if (Manifest.permission.WRITE_EXTERNAL_STORAGE.isPermissionOk(requireContext())) {
                saveImageOnDevice()
            } else {
                askForPermission(
                    activity = activity as AppCompatActivity,
                    permission = Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    codeRequest = REQUEST_CODE_PERMISSION_WRITE_EXTERNAL
                )
            }
        }
    }

    fun setupViews(children: Children) {
        image_top_detail.setImageFromUrl(children.data.thumbnail)
        title_top_detail.text = children.data.name
        content_top_detail.text = children.data.title
    }

    fun saveImageOnDevice() {
        if (!children?.data?.thumbnail.isNullOrEmpty()) {
            val bitmap = image_top_detail.getBitmapFromImageView()
            saveImage(bitmap, requireContext(), children?.data?.name ?: "")
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_image_available),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveImage(bitmap: Bitmap, context: Context, folderName: String) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            val values = contentValues()
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/RedditTops/$folderName")
            values.put(MediaStore.Images.Media.IS_PENDING, true)

            val uri: Uri? =
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) {
                saveImageToStream(bitmap, context.contentResolver.openOutputStream(uri))
                values.put(MediaStore.Images.Media.IS_PENDING, false)
                context.contentResolver.update(uri, values, null, null)
            }
        } else {
            val directory =
                File(Environment.getExternalStorageDirectory().toString() + separator + folderName)

            if (!directory.exists()) {
                directory.mkdirs()
            }
            val fileName = System.currentTimeMillis().toString() + ".png"
            val file = File(directory, fileName)
            saveImageToStream(bitmap, FileOutputStream(file))
            if (file.absolutePath != null) {
                val values = contentValues()
                values.put(MediaStore.Images.Media.DATA, file.absolutePath)
                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            }
        }
    }

    private fun contentValues(): ContentValues {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        return values
    }

    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.download_successfully),
                    Toast.LENGTH_SHORT
                ).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.download_not_successfully),
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as DetailTopCallback
    }

    override fun onDetach() {
        super.onDetach()
        callback.onDetachFragmentDetail()
    }
}