package com.af.ebtikartaskaf.interfaces
//AF//
/** used with adapter */
interface ItemClickInterface<T> {
    fun onItemClickListener(t: T, position: Int)
}