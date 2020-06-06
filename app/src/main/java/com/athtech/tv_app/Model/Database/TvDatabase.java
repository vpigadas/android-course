package com.athtech.tv_app.Model.Database;

        import androidx.room.Database;
        import androidx.room.RoomDatabase;
        import androidx.room.TypeConverters;

        import com.athtech.tv_app.Model.Communication.ServerResponse;

@Database(entities = {ServerResponse.class}, version = 2,exportSchema = false)
@TypeConverters({ListTypeConverters.class})
public abstract class TvDatabase extends RoomDatabase {
    public abstract TvResponseDao tvchannelDao();
}