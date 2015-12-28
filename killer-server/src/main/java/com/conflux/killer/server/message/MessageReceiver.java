package com.conflux.killer.server.message;

import com.conflux.killer.core.message.Message;

public interface MessageReceiver {
    void receiveMessage( Message message );
}
