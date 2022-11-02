package com.nx.nxr.room

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getDateCreated(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))  }

fun ENTITYs.getDay(): String{
    if (this.dateUpdated.isEmpty()) return ""
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(this.dateUpdated,formatter ).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))  }