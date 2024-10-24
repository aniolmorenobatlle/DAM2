package com.example.uf1_a7

import android.Manifest
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
        val imageCapture = imageCapture ?: return

        // Ruta i format de la imatge
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Opcions de sortida
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()

        // Guardar la imatge
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    val msg = "Error en guardar la foto"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults){
                    val msg = "Foto guardada a: ${output.savedUri}"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }
            }
        )
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


    // Eliminar imatge
    private fun deletePhoto() {
        viewBinding.imageViewPreview.setImageBitmap(null)
    }

    // Dialeg eliminar imatge
    private fun showDialogDeletePhoto() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("Eliminar imatge")
            .setTitle("Estàs segur que vols eliminar la imatge?")
            .setPositiveButton("Eliminar") { dialog, which ->
                deletePhoto()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
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
            imageCapture = ImageCapture.Builder()
                .build()

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
        val images = getRecentImages()  // Obtenir la llista d'imatges recents
        if (position < images.size) {
            val imageUri = images[position]  // Obtenir la URI de la imatge en la posició especificada
            viewBinding.imageViewPreview.setImageURI(imageUri)  // Mostrar la imatge en l'ImageView
        } else {
            Toast.makeText(this, "No hi ha prou imatges disponibles", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRecentImages(): List<Uri> {
        val imageUris = mutableListOf<Uri>()

        // Defineix la ruta de la carpeta Pictures/CameraX-Image
        val imageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraX-Image")

        // Obtenim tots els arxius de la carpeta
        val files = imageDir.listFiles { file -> file.isFile && file.extension in listOf("jpg", "jpeg", "png") }

        files?.let {
            // Ordenem per data de creació (més recent primer)
            val sortedFiles = it.sortedByDescending { file -> file.lastModified() }

            // Obtenim la URI de cada arxiu d'imatge
            for (file in sortedFiles) {
                val imageUri = Uri.fromFile(file)
                imageUris.add(imageUri)
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
                showDialogDeletePhoto()
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