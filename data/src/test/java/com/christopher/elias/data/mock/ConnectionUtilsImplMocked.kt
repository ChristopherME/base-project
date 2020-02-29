package com.christopher.elias.data.mock

import com.christopher.elias.data.network.utils.ConnectionUtils

/**
 * Created by Christopher Elias on 26/10/2019.
 * christopher.mike.96@gmail.com
 *
 * Peru Apps
 * Lima, Peru.
 **/
/**
 * This class is just for "MOCK" the result of wifi connection and
 * return always true.
 * This class is just for UnitTest of the use cases.
 */
class ConnectionUtilsImplMocked : ConnectionUtils {
    override fun isNetworkAvailable() = true
}