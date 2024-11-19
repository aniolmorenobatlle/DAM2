package dam2.amoreno.moreno_aniol_activitat11.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import dam2.amoreno.moreno_aniol_activitat11.R
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class FragmentImage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)

        val nomImatge = arguments?.getString("nomImatge")

        if (nomImatge != null) {
            val imageView = view.findViewById<ImageView>(R.id.imageViewProduct)

            val imageUri = getImageUriFromName(nomImatge)

            if (imageUri != null) {
                val bitmap = BitmapFactory.decodeStream(requireActivity().contentResolver.openInputStream(imageUri))
                imageView.setImageBitmap(bitmap)
            }
        }

        return view
    }

    private fun getImageUriFromName(imageName: String): Uri? {
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(imageName)

        val cursor = requireActivity().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val imageId = it.getLong(it.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val imageUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageId.toString())
                return imageUri
            }
        }

        return null
    }
}
