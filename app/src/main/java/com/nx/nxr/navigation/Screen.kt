package com.nx.nxr.navigation

import com.nx.nxr.core.Constants

object Screen {

    private const val SCREEN_CREATE_ROUTE = "screen create"
    private const val SCREEN_LIST_ROUTE = "screen list"
    private const val SCREEN_DETAIL_ROUTE = "screen detail"
    private const val SCREEN_EDIT_ROUTE = "screen edit"

    fun screenCreateRoute() = SCREEN_CREATE_ROUTE
    fun screenListRoute() = SCREEN_LIST_ROUTE

    fun screenDetailRoute() = "$SCREEN_DETAIL_ROUTE/{${Constants.ID}}"
    fun screenDetailRouteCall(id: Int) = "$SCREEN_DETAIL_ROUTE/$id"

    fun screenEditRoute() = "$SCREEN_EDIT_ROUTE/{${Constants.ID}}"
    fun screenEditRouteCall(id: Int) = "$SCREEN_EDIT_ROUTE/$id"

}