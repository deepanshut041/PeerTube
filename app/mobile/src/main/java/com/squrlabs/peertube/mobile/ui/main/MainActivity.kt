/*
 *  Copyright (c) 2020 Squrlabs @ http://squrlabs.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *              http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.squrlabs.peertube.mobile.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mikepenz.iconics.utils.IconicsMenuInflaterUtil
import com.squrlabs.peertube.mobile.R
import com.squrlabs.peertube.mobile.ui.main.global.MainGlobalFragment
import com.squrlabs.peertube.mobile.ui.main.library.MainLibraryFragment
import com.squrlabs.peertube.mobile.ui.main.local.MainLocalFragment
import com.squrlabs.peertube.mobile.ui.main.subscription.MainSubscriptionFragment
import com.squrlabs.peertube.mobile.ui.main.trending.MainTrendingFragment
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : AppCompatActivity() {

    // Loading HomeModule Module
    private val loadFeatures by lazy { loadKoinModules(mainModule) }

    private fun injectFeatures() = loadFeatures

    private val viewModel: MainViewModel by viewModel()
    private val mainGlobalFragment = MainGlobalFragment()
    private val mainTrendingFragment = MainTrendingFragment()
    private val mainLocalFragment = MainLocalFragment()
    private val mainSubscriptionFragment = MainSubscriptionFragment()
    private val mainLibraryFragment = MainLibraryFragment()

    private var activeFragment: Fragment = mainGlobalFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        injectFeatures()
        setNavigation()
        setUpFragmentManager()
        bnHome.selectedItemId = R.id.mainMenuGlobal
    }

    private fun setUpFragmentManager() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.flContainer, mainGlobalFragment, "Main Global").hide(mainGlobalFragment)
            add(R.id.flContainer, mainTrendingFragment, "Main Trending").hide(mainTrendingFragment)
            add(R.id.flContainer, mainLocalFragment, "Main Local").hide(mainLocalFragment)
            add(R.id.flContainer, mainLibraryFragment, "Main Library").hide(mainLibraryFragment)
            add(R.id.flContainer, mainSubscriptionFragment, "Main Subscription").hide(mainSubscriptionFragment)
        }.commit()
    }

    private fun setNavigation() {

        IconicsMenuInflaterUtil.parseXmlAndSetIconicsDrawables(this, R.menu.main_bottom_menu, bnHome.menu);

        bnHome.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.mainMenuGlobal -> mainGlobalFragment
                R.id.mainMenuTrending -> mainTrendingFragment
                R.id.mainMenuHome -> mainLocalFragment
                R.id.mainMenuLibrary -> mainLibraryFragment
                R.id.mainMenuSubscription -> mainSubscriptionFragment
                else -> activeFragment
            }
            supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
            activeFragment = fragment
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(mainModule)
    }

}