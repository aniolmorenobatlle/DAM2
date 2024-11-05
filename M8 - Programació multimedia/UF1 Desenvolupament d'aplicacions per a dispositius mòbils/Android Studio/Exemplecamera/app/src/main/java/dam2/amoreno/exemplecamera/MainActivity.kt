package dam2.amoreno.exemplecamera

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private lateinit var captureImage: ImageCapture
    private lateinit var picturesArray: ArrayList<String>
    private lateinit var imageNotSaved : ImageView
    private lateinit var info : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cameraPreview: PreviewView = findViewById(R.id.previewView)
        val openCamera: Button = findViewById(R.id.openCamera)
        val showImage: Button = findViewById(R.id.showImage)
        info = findViewById(R.id.textInfo)
        imageNotSaved = findViewById(R.id.imageView)

        info.visibility = View.GONE

        val sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        val control: Boolean = sharedPreferences.getBoolean("image?", false)
        val editor = sharedPreferences.edit()

        if (control) {
            deleteImage()
        }
        else {

            val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED)

            val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
            val selectionArgs = arrayOf("%${Environment.DIRECTORY_PICTURES}%")

            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )

            cursor?.use {
                if (it.moveToFirst()) {
                    val id = it.getLong(it.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    imageNotSaved.setImageURI(uri)
                    editor.putBoolean("image?", true)
                } else {
                    editor.putBoolean("image?", false)
                    editor.apply()
                    info.visibility = View.VISIBLE
                }
            }
        }

        openCamera.setOnClickListener {
            cameraPreview.visibility = PreviewView.VISIBLE
            info.visibility = View.GONE
            openCamera.visibility = View.GONE
            imageNotSaved.visibility = View.GONE

            val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                cameraProvider.unbindAll()

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(cameraPreview.surfaceProvider)
                }
                captureImage = ImageCapture.Builder().build()

                cameraProvider.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA, preview, captureImage)
            }, ContextCompat.getMainExecutor(this))
            showImage.visibility = View.VISIBLE
        }

        showImage.setOnClickListener {
            captureImage.takePicture(ContextCompat.getMainExecutor(this),
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(imageProxy: ImageProxy) {
                        val buffer = imageProxy.planes[0].buffer
                        val bytes = ByteArray(buffer.remaining())
                        buffer.get(bytes)
                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        imageNotSaved.setImageBitmap(bitmap)
                        imageProxy.close()
                        cameraPreview.visibility = View.GONE
                        openCamera.visibility = View.VISIBLE
                        imageNotSaved.visibility = View.VISIBLE
                        showImage.visibility = View.GONE
                    }})
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        picturesArray = arrayListOf()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                saveImage()
                true
            }
            R.id.delete -> {
                if (picturesArray.isEmpty()) {
                    imageNotSaved.visibility = View.GONE
                    info.visibility = View.VISIBLE
                }
                if (picturesArray.isNotEmpty()) {
                    deleteImage()
                }
                true
            }
            R.id.image_1 -> {
                if (picturesArray.size >= 1) {
                    getImage(picturesArray[picturesArray.size - 1])
                } else {
                    Toast.makeText(this, "Not enough images in the folder", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.image_2 -> {
                if (picturesArray.size >= 2) {
                    getImage(picturesArray[picturesArray.size - 2])
                } else {
                    Toast.makeText(this, "Not enough images in the folder", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.image_3 -> {
                if (picturesArray.size >= 3) {
                    getImage(picturesArray[picturesArray.size - 3])
                } else {
                    Toast.makeText(this, "Not enough images in the folder", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.image_4 -> {
                if (picturesArray.size >= 4) {
                    getImage(picturesArray[picturesArray.size - 4])
                } else {
                    Toast.makeText(this, "Not enough images in the folder", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getImage(filename: String) {

        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(filename)
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                val id = it.getLong(it.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                val imageView = findViewById<ImageView>(R.id.imageView)
                imageView.setImageURI(uri)
            }
        }
    }

    private fun deleteImage() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Delete image")
        builder.setMessage("Are you sure you want to delete the last image?")

        builder.setPositiveButton("Yes") { dialog, which ->

            imageNotSaved.visibility = View.GONE
            info.visibility = View.VISIBLE
            val lastFilename = picturesArray.removeLast()
            val projection = arrayOf(MediaStore.Images.Media._ID)
            val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
            val selectionArgs = arrayOf(lastFilename)

            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null
            )
            cursor?.use {
                if (it.moveToFirst()) {
                    val id = it.getLong(it.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    contentResolver.delete(uri, null, null)
                }
            }
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun saveImage() {
        val bitmap = (findViewById<ImageView>(R.id.imageView).drawable as BitmapDrawable).bitmap
        val timestamp = System.currentTimeMillis()
        val filename = "$timestamp.jpg"
        picturesArray.add(filename)
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME,filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
        val resolver = contentResolver
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        val fos = imageUri?.let {resolver.openOutputStream(it)}
        if (fos != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        }
        fos?.flush()
        fos?.close()
        Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show()
    }
}
