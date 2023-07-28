package com.eitu.viewmodelexample

import com.google.gson.annotations.SerializedName

data class DaumSearchResponse(
    @SerializedName("meta"     ) val meta      : DaumSearchMeta,
    @SerializedName("documents") val documents : List<DaumSearchDocument>
)

data class DaumSearchMeta(
    @SerializedName("total_count"   ) val totalCount    : Int,
    @SerializedName("pageable_count") val pageableCount : Int,
    @SerializedName("is_end"        ) val isEnd         : Boolean,
)

data class DaumSearchDocument(
    //비디오 검색 결과
    @SerializedName("thumbnail") val thumbnail : String? = null,
    @SerializedName("url"      ) val url       : String? = null,
    @SerializedName("author"   ) val author    : String? = null,

    //이미지 검색 결과
    @SerializedName("thumbnail_url"   ) val thumbnailUrl    : String? = null,
    @SerializedName("image_url"       ) val imageUrl        : String? = null,
    @SerializedName("display_sitename") val displaySiteName : String? = null,
    @SerializedName("doc_url"         ) val docUrl          : String? = null,

    //공통
    @SerializedName("datetime" ) val datetime  : String = "",
) {

    //author 필드가 null이 아니면 비디오 검색 결과
    fun isVideo() : Boolean {
        return author != null
    }

    fun getSearchItem() : SearchItem {
        return if (isVideo()) {
            SearchItem(thumbnail!!,author!!,datetime)
        }
        else {
            SearchItem(thumbnailUrl!!,displaySiteName!!,datetime)
        }
    }

}

data class SearchItem(
    val thumbnail : String,
    val author : String,
    val dateTime : String
)