package uk.gov.hmrc.sample_compose_fragments.presentation

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uk.gov.hmrc.components.compose.ui.theme.HmrcAlwaysBlack
import uk.gov.hmrc.components.compose.ui.theme.HmrcAlwaysWhite
import uk.gov.hmrc.sample_compose_components.R
import uk.gov.hmrc.sample_compose_components.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.apply {
            setTitleTextColor(HmrcAlwaysWhite.toArgb())
            background = ColorDrawable(HmrcAlwaysBlack.toArgb())
            setSupportActionBar(this)
        }
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.organismsFragment,
                R.id.moleculesFragment,
                R.id.atomsFragment,
                R.id.colorsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.apply {
            bottomNavigationBar.setupWithNavController(navController)
        }
    }
}