package com.daclink.drew.sp22.cst438_project01_starter.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM user")
    List<UserEntity> getAllUsers();



}
