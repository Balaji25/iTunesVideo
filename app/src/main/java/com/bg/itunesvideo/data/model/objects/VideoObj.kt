package com.bg.itunesvideo.data.model.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Balaji Gaikwad on 30/1/21.
 */

@Entity(tableName = "VideoObj")
data class VideoObj(val wrapperType : String,
                    val kind : String,
                    val artistId : Int,
                    @PrimaryKey
                    val trackId : Int,
                    val artistName : String,
                    val trackName : String,
                    val trackCensoredName : String,
                    val artistViewUrl : String,
                    val trackViewUrl : String,
                    val previewUrl : String,
                    val artworkUrl30 : String,
                    val artworkUrl60 : String,
                    val artworkUrl100 : String,
                    val collectionPrice : Double,
                    val trackPrice : Double,
                    val releaseDate : String,
                    val collectionExplicitness : String,
                    val trackExplicitness : String,
                    val trackTimeMillis : Int,
                    val country : String,
                    val currency : String,
                    val primaryGenreName : String) : Serializable
