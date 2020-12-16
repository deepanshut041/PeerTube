package com.squrlabs.peertube.mobile.ui.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squrlabs.peertube.mobile.R
import com.squrlabs.peertube.mobile.ui.video.bottom.VideoBottomFragment
import com.squrlabs.peertube.mobile.ui.video.top.VideoTopFragment
import com.tuanhav95.drag.DragView
import com.tuanhav95.drag.utils.toPx
import kotlinx.android.synthetic.main.video_activity.*
import kotlinx.android.synthetic.main.video_layout_bottom.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import kotlin.math.max
import kotlin.math.min

class VideoActivity: AppCompatActivity(), DragView.DragListener {

    // Loading HomeModule Module
    private val loadFeatures by lazy { loadKoinModules(videoModule) }

    private fun injectFeatures() = loadFeatures

    private val viewModel: VideoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_activity)
        injectFeatures()
        setupDragLayout()
    }

    private fun setupDragLayout() {
        dragView.setDragListener(this)

        supportFragmentManager.beginTransaction().add(R.id.frameTop, VideoTopFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.frameBottom, VideoBottomFragment()).commit()

        btnMax.setOnClickListener { dragView.maximize() }
        btnMin.setOnClickListener { dragView.minimize() }
        btnClose.setOnClickListener { dragView.close() }

        btnSetHeightMax.setOnClickListener {
            var heightMax = 0
            if (etHeightMax.text.isNotEmpty()) {
                heightMax = etHeightMax.text.toString().toInt()
            }

            heightMax = max(heightMax, 200)
            heightMax = min(heightMax, 400)

            dragView.setHeightMax(heightMax.toPx(), true)
        }
    }

    override fun onChangeState(state: DragView.State) {
    }

    override fun onChangePercent(percent: Float) {
        alpha.alpha = 1 - percent
        shadow.alpha = percent
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(videoModule)
    }
}