package com.squrlabs.peertube.mobile.ui.instance

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.iconics.utils.IconicsMenuInflaterUtil
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.params.GetInstancesParams
import com.squrlabs.peertube.mobile.R
import kotlinx.android.synthetic.main.instance_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class InstanceActivity : AppCompatActivity() {

    // Loading InstanceModule Module
    private val loadFeatures by lazy { loadKoinModules(instancesModule) }
    private fun injectFeatures() = loadFeatures

    private val viewModel: InstanceViewModel by viewModel()
    private lateinit var instanceListAdapter: InstanceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Instances"
        injectFeatures()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        rvInstances.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        instanceListAdapter = InstanceListAdapter(InstanceListDiffUtil(), this)
        rvInstances.adapter = instanceListAdapter

        instanceListAdapter.selectedInstance.observe(this, {
            viewModel.setCurrentHost(it)
        })
        srlLayout.setOnRefreshListener {
            viewModel.startLoading()
        }

        srlLayout.isRefreshing = true
    }

    private fun observeViewModel() {
        with(viewModel) {
            setParams(GetInstancesParams())
            fetchInstances()
            getInstance.observe(this@InstanceActivity, {
                instanceListAdapter.submitList(it.data ?: emptyList())
            })

            fetchInstances.observe(this@InstanceActivity, {
                when(it.state){
                    Resource.LOADING -> { viewModel.fetchInstances() }
                    else -> {
                        setParams(instanceParams.value!!)
                        srlLayout.isRefreshing = false
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        IconicsMenuInflaterUtil.inflate(menuInflater, this, R.menu.menu_instance, menu)
        (menu.findItem(R.id.search).actionView as SearchView).let{
            it.maxWidth = Int.MAX_VALUE;
            it.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.setParams(viewModel.instanceParams.value!!.copy(text = newText))
                    return false
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> true
            R.id.sort -> true
            R.id.filter -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(instancesModule)
    }
}