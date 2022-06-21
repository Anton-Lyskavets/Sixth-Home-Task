package com.example.sixth_home_task.presentation.general

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.sixth_home_task.R
import com.example.sixth_home_task.presentation.app.App
import com.example.sixth_home_task.domain.models.Bank
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.example.sixth_home_task.databinding.ActivityMapsBinding
import com.example.sixth_home_task.presentation.model.MapsViewModel
import com.example.sixth_home_task.presentation.model.MapsViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

class MapsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMapsBinding.inflate(layoutInflater)
    }
    private var circle: Circle? = null
    private val defaultMarker = LatLng(52.425163, 31.015039)
    private lateinit var vm: MapsViewModel

    @Inject
    lateinit var vmFactory: MapsViewModelFactory

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this)
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        vm = ViewModelProvider(this, vmFactory)[MapsViewModel::class.java]
        vm.bankList.observe(this
        ) {
            val bounds = LatLngBounds.builder()
            var sortedListDistance: List<Double>
            val needListMarker = mutableListOf<Bank>()
            val listDistance = mutableListOf<Double>()
            mapFragment?.getMapAsync { googleMap ->
                googleMap.setOnMapLoadedCallback {
                    it.forEach {
                        listDistance.add(distanceTwoPoints(defaultMarker, it.position))
                    }
                    sortedListDistance = listDistance.sorted()
                    it.forEach {
                        for (i in 0..9) {
                            if (distanceTwoPoints(defaultMarker,
                                    it.position) == sortedListDistance[i]
                            ) {
                                needListMarker.add(it)
                                bounds.include(it.position)
                            }
                        }
                    }
                    googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngBounds(
                            bounds.build(),
                            20
                        )
                    )
                    addClusteredMarkers(googleMap, needListMarker)
                }
            }
        }
    }

    private fun distanceTwoPoints(pointOne: LatLng, pointTwo: LatLng): Double {
        return sqrt(
            (pointTwo.latitude - pointOne.latitude).pow(2)
                    + (pointTwo.longitude - pointOne.longitude).pow(2)
        )
    }

    private fun addClusteredMarkers(googleMap: GoogleMap, listObj: List<Bank>) {
        ClusterManager<Bank>(this@MapsActivity, googleMap).apply {
            renderer =
                BankRenderer(
                    this@MapsActivity,
                    googleMap,
                    this
                )
            addItems(listObj)
            cluster()
            setOnClusterItemClickListener { item: Bank ->
                addCircle(googleMap, item)
                return@setOnClusterItemClickListener false
            }
            googleMap.setOnCameraMoveStartedListener {
                markerCollection.markers.forEach { it.alpha = 0.3f }
            }
            googleMap.setOnCameraIdleListener {
                markerCollection.markers.forEach { it.alpha = 1.0f }
                onCameraIdle()
            }
        }

    }

    private fun addCircle(googleMap: GoogleMap, item: Bank) {
        circle?.remove()
        circle = googleMap.addCircle(
            CircleOptions()
                .center(LatLng(item.gpsX.toDouble(), item.gpsY.toDouble()))
                .radius(100.0)
                .fillColor(ContextCompat.getColor(this, R.color.green_200))
                .strokeColor(ContextCompat.getColor(this, R.color.red_600))
        )
    }
}