import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * OkHttpWebSockret封装
 */
public class OkhttpWebSocket{
    private OkHttpClient okHttpClient = new OkHttpClient();
    private WebSocket webSocket;

    private static Request request = null;

    //回调接口
    public interface CallBack {
        void back(WebSocket webSocket, Object[] out);
    }

    public static void setRequest(String url) {
        request = new Request.Builder()
                .url(url)
                .build();
    }
    public void getMessage(final CallBack callBack){
        webSocket = okHttpClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Map<String, Object> map = new HashMap<>();
                callBack.back(webSocket, new Object[]{"onMessage", text});
                super.onMessage(webSocket, text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                callBack.back(webSocket, new Object[]{"onMessage", String.valueOf(bytes)});
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                callBack.back(webSocket, new Object[]{"onClosing", code, reason});
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                callBack.back(webSocket, new Object[]{"onClosed", code, reason});
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                callBack.back(webSocket, new Object[]{"onFailure", t, response});
                super.onFailure(webSocket, t, response);
            }
        });
    }

    public void myWebSockert(String url, final CallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        webSocket = okHttpClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Map<String, Object> map = new HashMap<>();
                callBack.back(webSocket, new Object[]{"onMessage", text});
                super.onMessage(webSocket, text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                callBack.back(webSocket, new Object[]{"onMessage", String.valueOf(bytes)});
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                callBack.back(webSocket, new Object[]{"onClosing", code, reason});
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                callBack.back(webSocket, new Object[]{"onClosed", code, reason});
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                callBack.back(webSocket, new Object[]{"onFailure", t, response});
                super.onFailure(webSocket, t, response);
            }
        });
    }

    //发消息
    public boolean sendMessage(String s) {
        return webSocket.send(s);
    }

    //关闭WebSockert
    public void sockertClose() {
        webSocket.close(1000, "");
        okHttpClient.dispatcher().executorService().shutdown();
    }
}
