package com.breshy.tracktrail.navigation

const val ROUT_TRAIN = "train"
const val ROUT_ABOUT = "about"
const val ROUT_REGISTER = "register"
const val ROUT_LOGIN = "login"
const val ROUT_BUS = "bus"
const val ROUT_BOOKINGS = "bookings"
const val ROUT_PAY = "pay"
const val ROUT_DASHBOARD = "dashboard"
const val ROUT_SPLASH = "splash"

//Products

const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"