package dam2.amoreno.moreno_aniol_activitat11

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaDataSource
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy

class CameraActivity : AppCompatActivity() {
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var cameraPreview: PreviewView
    private lateinit var imageCapture: ImageCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)

        cameraPreview = findViewById(R.id.cameraPreview)
        val tancarCamera = findViewById<ImageView>(R.id.tancarCamera)
        val ferFoto = findViewById<ImageView>(R.id.ferFoto)

        tancarCamera.setOnClickListener {
            stopCamera()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ferFoto.setOnClickListener {
            takePhoto()
        }

        startCamera()
    }

    private fun startCamera() {
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
                    Toast.makeText(this@CameraActivity, "Foto salvada", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@CameraActivity, MainActivity::class.java).apply {
                        putExtra("imageUri", savedUri.toString())
                        putExtra("imageName", imageName)
                    }
                    startActivity(intent)
                    finish()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(this@CameraActivity, "Error al salvar la foto", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error en capturar la foto: ${exception.message}", exception)
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
