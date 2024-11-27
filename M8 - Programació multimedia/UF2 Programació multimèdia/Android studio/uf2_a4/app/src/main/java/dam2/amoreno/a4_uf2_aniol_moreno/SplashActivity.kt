package dam2.amoreno.a4_uf2_aniol_moreno

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // TODO : generar tot el procés de connexió al firebase de manera externa la main activity
        // Delay de 5 segons
        Handler(Looper.getMainLooper()).postDelayed({
            // TODO : generar una progress bar mentre s'executa l'spalsh activity
            startActivity(Intent(this, MainActivity::class.java))
            // tanquem l'spalsh de manera que no pogume tornar endarrera
            extracted()
        }, 5000)
    }

    private fun extracted() {
        finish()
    }
}
