package com.example.movieapp.core.common.base

abstract class BaseUseCase<in P, R> {
    abstract suspend operator fun invoke(parameters: P): R
}