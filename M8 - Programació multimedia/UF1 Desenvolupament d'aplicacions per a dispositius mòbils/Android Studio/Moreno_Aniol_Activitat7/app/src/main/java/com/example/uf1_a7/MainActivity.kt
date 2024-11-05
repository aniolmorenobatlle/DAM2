package com.example.uf1_a7

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.uf1_a7.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val preview = viewBinding.viewFinder
        val imagePreview = viewBinding.imageViewPreview
        val buttonCloseCamera = viewBinding.buttonCloseCamera
        val buttonTakePhoto = viewBinding.buttonTakePhoto
        val buttonOpenCamera = viewBinding.buttonOpenCamera


        // Obrir camera
        buttonOpenCamera.setOnClickListener {
            startCamera()

            imagePreview.setImageBitmap(null)

            buttonOpenCamera.visibility = View.GONE

            preview.visibility = View.VISIBLE
            buttonCloseCamera.visibility = View.VISIBLE
            buttonTakePhoto.visibility = View.VISIBLE
        }

        // Tancar camera
        buttonCloseCamera.setOnClickListener {
            stopCamera()

            buttonOpenCamera.visibility = View.VISIBLE

            preview.visibility = View.GONE
            buttonCloseCamera.visibility = View.GONE
            buttonTakePhoto.visibility = View.GONE
        }

        // Capturar foto
        buttonTakePhoto.setOnClickListener {
            addPreview()

            buttonOpenCamera.visibility = View.VISIBLE

            preview.visibility = View.GONE
            buttonCloseCamera.visibility = View.GONE
            buttonTakePhoto.visibility = View.GONE
        }
    }


    // Afegir la imatge al imagePreview
    private fun addPreview() {
        val imageCapture = imageCapture ?: return

        // Convertir la imatge a Bitmap per poder-la mostrar al imagePreview
        imageCapture.takePicture(
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageCapturedCallback() {

                // Convertir de ImageProxy a Bitmap
                override fun onCaptureSuccess(image: ImageProxy) { // ImatgeProxy es un contenidor que guarda la informacio de la imatge

                    // Converteir ImageProxy a Bitmap perque es un format que Android pot mostrar
                    val bitmap = imageProxyToBitmap(image)

                    image.close()

                    // Assigna el Bitmap al ImageView
                    viewBinding.imageViewPreview.setImageBitmap(bitmap)

                    // Parar camera
                    stopCamera()
                }

                override fun onError(exc: ImageCaptureException) {
                    val msg = "Error taking photo"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    // Converteix l'ImageProxy a Bitmap per mostrar-la en un ImageView
    private fun imageProxyToBitmap(image: ImageProxy): Bitmap? {
        // Obtenir la informacio de la imatge en bytes
        val buffer = image.planes[0].buffer

        // Llegir els bytes i guardar-les a una array
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)

        // Convertir els bytes a Bitmap per mostrar la imatge
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }


    // Fer foto
    private fun savePhoto() {
        val bitmap = (findViewById<ImageView>(R.id.imageViewPreview).drawable as BitmapDrawable).bitmap
        val timestamp = System.currentTimeMillis()
        val filename = "$timestamp.jpg"
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

    // Dialeg guardar imatge
    private fun showDialogSavePhoto() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("Guardar imatge")
            .setTitle("Estàs segur que vols guardar la imatge?")
            .setPositiveButton("Guardar") { dialog, which ->
                savePhoto()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Dialeg eliminar imatge
    private fun showDialogDeletePhoto(imageUri: Uri) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("Eliminar imatge")
            .setTitle("Estàs segur que vols eliminar la imatge?")
            .setPositiveButton("Eliminar") { dialog, which ->
                deletePhoto(imageUri)
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    // Eliminar imatge
    private fun deletePhoto(imageUri: Uri) {
        val deletedRows = contentResolver.delete(imageUri, null, null)
        if (deletedRows != null && deletedRows > 0) {
            Toast.makeText(this, "Imatge eliminada", Toast.LENGTH_SHORT).show()
            viewBinding.imageViewPreview.setImageBitmap(null)
        } else {
            Toast.makeText(this, "No s'ha pogut eliminar la imatge", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageUriFromSelection(): Uri {
        val imageUris = getLastSavedPhotos(4) // Obtenir les ultimes 4 fotos

        return imageUris[0]
    }


    // Engegar camera
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Obtenir l'objecte de CameraProvider
            val cameraProvider : ProcessCameraProvider = cameraProviderFuture.get()

            // Configuerar el preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewBinding.viewFinder.surfaceProvider)
                }

            // Configurar la captura ImatgeCapture
            imageCapture = ImageCapture.Builder().build()

            // Seleccionar la camera posterior per defecte
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {

                // Desvincular les altres cameres abans de posar de noves
                cameraProvider.unbindAll()

                // Vincular el preview i la captura d'imatge a la camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }


        }, ContextCompat.getMainExecutor(this))
    }


    // Parar camera
    private fun stopCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
        }, ContextCompat.getMainExecutor(this))
    }


    // Mostrar imatges
    private fun showRecentImages(position: Int) {
        val imageUris = getLastSavedPhotos(4) // Obtenir les 4 últimes imatges

        if (position in 1..imageUris.size) {
            val selectedImageUri = imageUris[position - 1] // Obtenir l'URI segons la posició

            // Carregar la imatge a l'ImageView corresponent
            val imageView = when (position) {
                1 -> findViewById<ImageView>(R.id.imageViewPreview)
                2 -> findViewById<ImageView>(R.id.imageViewPreview)
                3 -> findViewById<ImageView>(R.id.imageViewPreview)
                4 -> findViewById<ImageView>(R.id.imageViewPreview)
                else -> null
            }

            // Carregar la imatge a l'ImageView
            imageView?.setImageURI(selectedImageUri)
        } else {
            Toast.makeText(this, "No hi ha prou imatges disponibles", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getLastSavedPhotos(limit: Int): List<Uri> {
        val imageUris = mutableListOf<Uri>()

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATE_ADDED
        )

        // Ordenar per data en ordre descendent per obtenir les més recents primer
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val query = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,  // No necessites un filtre en aquest cas
            null,
            sortOrder
        )

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            var count = 0
            while (cursor.moveToNext() && count < limit) {
                val id = cursor.getLong(idColumn)
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                imageUris.add(uri)
                count++
            }
        }

        return imageUris
    }


    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }


    // Menu opcions
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                showDialogSavePhoto()
                true
            }
            R.id.bin -> {
                val imageUri = getImageUriFromSelection()
                showDialogDeletePhoto(imageUri)
                true
            }
            R.id.first_image -> {
                showRecentImages(1)
                true
            }
            R.id.second_image -> {
                showRecentImages(2)
                true
            }
            R.id.third_image -> {
                showRecentImages(3)
                true
            }
            R.id.fourth_image -> {
                showRecentImages(4)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}