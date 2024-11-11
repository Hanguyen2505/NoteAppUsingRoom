package com.example.noteappusingroomdatabase

//*** listen to the user's activity in bottom sheet fragment to change the view color
interface BottomSheetListener {
    fun onAddImageButtonClicked()

    fun onDeleteButtonClicked()

    fun onGrayButtonClicked()

    fun onYellowButtonClicked()

    fun onRedButtonClicked()

    fun onBlueButtonClicked()

    fun onBlackButtonClicked()

    fun onWhiteButtonClicked()
}