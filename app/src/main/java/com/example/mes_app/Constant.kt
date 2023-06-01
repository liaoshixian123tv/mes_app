package com.example.mes_app

object Constant {
    const val DATE_FORMAT = "yyyy-MM-dd"

// ------------------------------------------------------------------

    const val MAX_CATEGORY_ROW_SIZE = 2
    const val MAX_AMOUNT_LEN = 12

    const val USER_OPER_TIMEOUT = 60    // USER TIMEOUT (SEC)
    const val USER_OPER_TIMEOUT_1 = 1     // SEC
    const val USER_OPER_TIMEOUT_SHORT = 3     // SEC
    const val USER_OPER_TIMEOUT_5 = 5     // SEC
    const val USER_OPER_TIMEOUT_10 = 10
    const val USER_OPER_TIMEOUT_30 = 30

// ------------------------------------------------------------------

    // Category type
    const val PAGE_FUNCTION = "PageFunc"
    const val PAGE_SETTING = "PageSetting"

    // PageFunction sub category
    const val PRODUCT_FLOW = "$PAGE_FUNCTION.ProductFlow"

    // Product Flow sub category
    const val PRODUCT_FLOW_TYPE = "PRODUCT_FLOW_TYPE"
    const val PRODUCT_FLOW_ADD = "PRODUCT_FLOW_ADD"
    const val PRODUCT_FLOW_SHOW = "PRODUCT_FLOW_SHOW"
    const val PRODUCT_BLA_BLA = "PRODUCT_BLA_BLA"
    const val PRODUCT_SCAN = "PRODUCT_SCAN"
// -------------------------------------------------------------------

    // Action Type
    const val ACTION_INIT = 1
    const val ACTION_FINISH = 2
    const val ACTION_BACK = 3
    const val ACTION_DISPLAY_SCHEDULE = 4
    const val ACTION_DISPLAY_QR_SCAN = 5

    // view action
    const val USER_PRESS_OK_BUTTON = 1001
    const val USER_PRESS_CANCEL_BUTTON = 1002
    const val USER_PRESS_TIMEOUT = 1004
    const val USER_QR_SCAN_COMPLETE = 1005
}