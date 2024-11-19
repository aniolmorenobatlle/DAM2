package dam2.amoreno.moreno_aniol_activitat11

import android.Manifest
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore.Images.Media
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import dam2.amoreno.moreno_aniol_activitat11.CameraActivity.Companion
import dam2.amoreno.moreno_aniol_activitat11.classes.Products

class MainActivity : AppCompatActivity() {

    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var cameraPreview: PreviewView
    private lateinit var imageCapture: ImageCapture


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marcaProducte = findViewById<Spinner>(R.id.marcaProducte)
        val modelProducte = findViewById<EditText>(R.id.modelProducte)
        val quantitatProducte = findViewById<Spinner>(R.id.quantitatProducte)
        val botoImatge = findViewById<Button>(R.id.botoImatge)
        val imatgeProducte = findViewById<ImageView>(R.id.imatgeProducte)
        val guardarProducte = findViewById<Button>(R.id.guardarProducte)

        val add = findViewById<ImageView>(R.id.add)
        val list = findViewById<ImageView>(R.id.list)

        cameraPreview = findViewById(R.id.cameraPreview)


        // Comprova si hi ha una URI d'imatge passada
        val sharedPref = getSharedPreferences("product_image", MODE_PRIVATE)
        val imageUriString = sharedPref.getString("imageUri", null)
        val imageName = sharedPref.getString("imageName", null)

        if (imageUriString != null && imageName != null) {
            val imageUri = imageUriString.toUri()
            imatgeProducte.setImageURI(imageUri)
        }

        marcaProducte.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                guardarProducte.isEnabled = position != 0
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                guardarProducte.isEnabled = false
            }
        }

        guardarProducte.setOnClickListener {
            val marcaSeleccionada = marcaProducte.selectedItem.toString().lowercase()
            val modelSeleccionat = modelProducte.toString()
            val imageNameSeleccionat = sharedPref.getString("imageName", null) ?: "default_image_name"

            if (marcaSeleccionada != "marca" && modelSeleccionat != null && imageNameSeleccionat != null) {
                val sharedPref: SharedPreferences = getSharedPreferences("shared_$marcaSeleccionada", MODE_PRIVATE)
                val editor = sharedPref.edit()

                val newProduct = Products(
                    marca = marcaSeleccionada,
                    model = modelProducte.text.toString(),
                    quantitat = quantitatProducte.selectedItem.toString(),
                    nomImatge = imageNameSeleccionat
                )

                editor.putString("marca", newProduct.marca)
                editor.putString("model", newProduct.model)
                editor.putString("quantitat", newProduct.quantitat)
                editor.putString("nomImatge", newProduct.nomImatge)
                editor.apply()

                val imatgeProducte = findViewById<ImageView>(R.id.imatgeProducte)
                imatgeProducte.setImageDrawable(null)

                val sharedPrefImage = getSharedPreferences("product_image", MODE_PRIVATE)
                val editorImage = sharedPrefImage.edit()
                editorImage.remove("imageUri")
                editorImage.remove("imageName")
                editorImage.apply()

                val intent = Intent(this, InvertaryActivity::class.java)
                startActivity(intent)
            }
        }

        botoImatge.setOnClickListener {
            val cameraLayout = findViewById<ConstraintLayout>(R.id.cameraLayout)

            cameraLayout.visibility = View.VISIBLE


            val tancarCamera = findViewById<ImageView>(R.id.tancarCamera)
            val ferFoto = findViewById<ImageView>(R.id.ferFoto)

            tancarCamera.setOnClickListener {
                stopCamera()
                cameraLayout.visibility = View.GONE
            }

            ferFoto.setOnClickListener {
                takePhoto()
                cameraLayout.visibility = View.GONE
            }

            startcamera()
        }

        add.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        list.setOnClickListener {
            val intent = Intent(this, InvertaryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startcamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(cameraPreview.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                Log.e(TAG, "Error en vincular la càmera", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }


    private fun stopCamera() {
        cameraProvider.unbindAll()
    }


    private fun takePhoto() {
        val imageName = "${System.currentTimeMillis()}.jpg"

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            contentResolver,
            Media.EXTERNAL_CONTENT_URI,
            ContentValues().apply {
                put(Media.DISPLAY_NAME, imageName)
                put(Media.MIME_TYPE, "image/jpeg")
                put(Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
        ).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri

                    val sharedPref = getSharedPreferences("product_image", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("imageUri", savedUri.toString())
                    editor.putString("imageName", imageName)
                    editor.apply()

                    val imatgeProducte = findViewById<ImageView>(R.id.imatgeProducte)
                    imatgeProducte.setImageURI(savedUri)

                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(this@MainActivity, "Error al salvar la foto", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }


    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

}