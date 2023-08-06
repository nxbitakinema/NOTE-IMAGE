package com.nx.nxr.core

object Constants {

    const val TABLE_NAME = "NXRtable"
    const val DATABASE_NAME = "nxr database"
    const val ID = "id"

    private const val SCREEN_CREATE_ROUTE = "screen create"
    private const val SCREEN_LIST_ROUTE = "screen list"
    private const val SCREEN_DETAIL_ROUTE = "screen detail"
    private const val SCREEN_EDIT_ROUTE = "screen edit"

    fun screenCreateRoute() = SCREEN_CREATE_ROUTE
    fun screenListRoute() = SCREEN_LIST_ROUTE

    fun screenDetailRoute() = "$SCREEN_DETAIL_ROUTE/{$ID}"
    fun screenDetailRouteCall(id: Int) = "$SCREEN_DETAIL_ROUTE/$id"

    fun screenEditRoute() = "$SCREEN_EDIT_ROUTE/{$ID}"
    fun screenEditRouteCall(id: Int) = "$SCREEN_EDIT_ROUTE/$id"

}