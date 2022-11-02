package com.nx.nxr.room

object CONSTANTs {

    const val NAVIGATION_NXRS_LIST = "nxrList"
    const val NAVIGATION_NXRS_CREATE = "nxrCreated"
    const val NAVIGATION_NXR_DETAIL = "nxrDetail/{nxrId}"
    const val NAVIGATION_NXR_EDIT= "nxrEdit/{nxrId}"
    const val NAVIGATION_NXR_ID_Argument = "nxrId"
    const val TABLE_NAME = "NXRtable"
    const val DATABASE_NAME = "NxrsDatabase"

    fun nxrDetailNavigation( nxrId : Int ) = "nxrDetail/$nxrId"
    fun nxrEditNavigation( nxrId : Int ) = "nxrEdit/$nxrId"


    fun List<ENTITYs>?.orPlaceHolderList(): List<ENTITYs> {
        fun placeHolderList(): List<ENTITYs> {
            return listOf(
                ENTITYs(
                    id = 0,
                    title = "No NXR Found",
                    nxr = "Please create a NXR.",
                    dateUpdated = ""
                )
            )
        }
        return if (this != null && this.isNotEmpty()){
            this
        } else placeHolderList()
    }

    val nxrDetailPlaceHolder = ENTITYs(
        nxr = "Cannot find NXR details",
        id = 0,
        title = "Cannot find NXR details"
    )

}