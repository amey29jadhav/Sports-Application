package com.amey.sports_android.view.callback;

import java.util.List;

public interface ResultInterface<T> {

     void onSuccess(List<T> list);

     void onError();


}
