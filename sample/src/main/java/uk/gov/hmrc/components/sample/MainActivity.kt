/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.components.sample

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import uk.gov.hmrc.components.sample.base.ToolbarState
import uk.gov.hmrc.components.sample.databinding.ActivityMainBinding
import uk.gov.hmrc.components.sample.organisms.OrganismsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.apply {
            menu.clear()
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        }

        val items = provideBottomNavigationItems()

        for (index in items.indices) {
            val item = items[index]
            binding.bottomNavigation.menu.add(Menu.NONE, index, Menu.NONE, item.title).setIcon(item.icon)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            val selectedItem = items.getOrNull(it.itemId)
            if (selectedItem != null) {
                selectedItem.selectedListener.invoke()
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }

        if (items.isNotEmpty()) {
            binding.bottomNavigation.selectedItemId = 0
        }
    }

    fun setToolbar(toolbarState: ToolbarState) {
        if (toolbarState.enabled) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
        supportActionBar?.title = toolbarState.getTitle(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(toolbarState.homeAsUpEnabled)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    private fun provideBottomNavigationItems(): List<BottomNavigationItemModel> {
        return listOf(
            BottomNavigationItemModel(getString(R.string.title_organisms), R.drawable.ic_organisms) { showFragment(OrganismsFragment()) },
        )
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    fun setBottomBarVisibility(visible: Boolean) {
        binding.bottomNavigation.visibility = if (visible) View.VISIBLE else View.GONE
    }
}

data class BottomNavigationItemModel(val title: String, @DrawableRes val icon: Int, val selectedListener: () -> Unit = {})
