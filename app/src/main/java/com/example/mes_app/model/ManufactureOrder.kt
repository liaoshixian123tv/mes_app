package com.example.mes_app.model

data class ManufactureOrder(
    var id: String,
    var number: String,
    var Amount: Int
)

data class WALAWALA(
    var scheduleType: String = "",
    var TaskType: String = "",
    var InspectorNumber: Int = 0,
    var qrResult: String = ""
)