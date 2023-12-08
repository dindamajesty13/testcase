package com.majesty.testcase.person

import com.google.gson.annotations.SerializedName

data class PersonModel (
    @SerializedName("id") val id: String,
    @SerializedName("id_prop") val idProp: String,
    @SerializedName("nik") val nik: String,
    @SerializedName("nama") val nama: String,
    @SerializedName("nama_prop") val namaProp: String,
    @SerializedName("jumlah_penduduk") val jumlahPenduduk: String,
)