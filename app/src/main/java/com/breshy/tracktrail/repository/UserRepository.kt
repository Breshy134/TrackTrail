package com.breshy.tracktrail.repository

import com.breshy.tracktrail.data.UserDao
import com.breshy.tracktrail.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}