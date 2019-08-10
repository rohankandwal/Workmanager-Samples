package com.itcse.workmanagersample.main.periodic_time.utils

/**
 * Contains Constant used throughout the app
 *
 * @author Rohan Kandwal on 2019-08-04.
 */
object Constants {
    // JSON keys
    val BATTERY_STAT = "BATTERY_STAT"
    val NET_STAT = "NET_STAT"

    // Keys of the Data object sent by the parent Worker to its child Worker
    val BATTERY_PERCENTAGE = "BATTERY_PERCENTAGE"
    val NETWORK_USAGE = "NETWORK_USAGE"
}