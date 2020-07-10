package com.magicsamplecase.domain.errors

open class CustomExcpetion : Exception()

class IncorrectEmailException : CustomExcpetion()
class IncorrectPasswordException : CustomExcpetion()
class NoLoggedUserException : CustomExcpetion()