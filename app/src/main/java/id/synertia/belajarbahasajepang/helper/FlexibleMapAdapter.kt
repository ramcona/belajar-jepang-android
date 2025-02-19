package id.synertia.belajarbahasajepang.helper

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class FlexibleMapAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Any {
        return when (reader.peek()) {
            JsonReader.Token.BEGIN_OBJECT -> reader.readJsonValue() as Map<*, *>
            JsonReader.Token.BEGIN_ARRAY -> reader.readJsonValue() as List<*>
            else -> throw JsonDataException("Unexpected token")
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: Any?) {
        Moshi.Builder().build().adapter(Any::class.java).toJson(writer, value)
    }
}
