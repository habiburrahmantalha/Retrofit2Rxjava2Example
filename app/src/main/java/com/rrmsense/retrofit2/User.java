package com.rrmsense.retrofit2;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Talha on 5/8/2017.
 */

@AutoValue
public abstract class User {

    @SerializedName("username")
    abstract String userName();
    abstract String password();

    public static User create(String userName, String password) {
        return builder()
                .userName(userName)
                .password(password)
                .build();
    }


    public static Builder builder() {
        return new AutoValue_User.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder userName(String userName);

        public abstract Builder password(String password);

        public abstract User build();
    }
}
