package com.example.sixth_home_task.domain.models

import com.squareup.moshi.Json

data class BankATM(
    @Json(name = "install_place") override val installPlace: String,
    @Json(name = "address_type") override val addressType: String,
    override val address: String,
    override val house: String,
    @Json(name = "gps_x") override val gpsX: String,
    @Json(name = "gps_y") override val gpsY: String,
    @Json(name = "work_time") override val workTime: String,
) : Bank() {
    override fun getTitle(): String {
        return "ATM $installPlace"
    }
}