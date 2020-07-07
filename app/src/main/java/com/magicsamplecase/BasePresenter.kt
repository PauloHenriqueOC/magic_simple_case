package com.magicsamplecase

abstract class BasePresenter : DisposeView by DisposeDelegate() {
    abstract fun bindView()
}