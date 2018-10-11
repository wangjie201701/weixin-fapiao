package com.wangjie.bill.base.http.callback;

import java.io.IOException;


public abstract class CallbackAsBody extends HttpClientCallback {

    public CallbackType getType() {
        return CallbackType.BODY;
    }

    public abstract void exec(final String body) throws IOException;
}
