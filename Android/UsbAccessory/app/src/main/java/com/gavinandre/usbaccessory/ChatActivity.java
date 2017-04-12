package com.gavinandre.usbaccessory;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by gavinandre on 17-4-7.
 */

public class ChatActivity extends BaseChatActivity {

    private static final String TAG = ChatActivity.class.getSimpleName();
    private AccessoryCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        communicator = new AccessoryCommunicator(this) {

            @Override
            public void onReceive(byte[] payload, int length) {
                Log.i(TAG, "onReceive: " + new String(payload, 0, length));
                printLineToUI("host> " + new String(payload, 0, length));
            }

            @Override
            public void onError(String msg) {
                Log.i(TAG, "onError: "+msg);
                printLineToUI("notify" + msg);
            }

            @Override
            public void onConnected() {
                Log.i(TAG, "onConnected: ");
                printLineToUI("connected");
            }

            @Override
            public void onDisconnected() {
                Log.i(TAG, "onDisconnected: ");
                printLineToUI("disconnected");
            }
        };

        /*final String inputString = "fjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlk" +
                "fjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafs" +
                "fjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafs" +
                "fjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafs" +
                "fjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafs" +
                "fjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafs" +
                "sajflkdsjaflkjsafsfjsdlfjdlksajflkdsjaflkjsafs";

        sendString(inputString);
        printLineToUI(getString(R.string.local_prompt) + inputString);*/
    }


    @Override
    protected void sendString(String string) {
        communicator.send(string.getBytes());
    }
}
