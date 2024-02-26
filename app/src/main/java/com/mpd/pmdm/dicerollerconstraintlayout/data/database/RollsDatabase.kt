package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [DiceRolls::class] )
abstract class RollsDatabase: RoomDatabase() {
    abstract fun dao(): DiceRollsDao

    companion object{

        //Esta varaible contendrá la instancia única de base de datos para toda la
        //aplicación. O si está vacía, se generará
        @Volatile
        private var INSTANCE: RollsDatabase? = null

        fun getDatabase(context: Context): RollsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    RollsDatabase::class.java,
                    "dice_rolls_history"
                ).build()
                INSTANCE = instance

                instance

            }
        }
    }
}