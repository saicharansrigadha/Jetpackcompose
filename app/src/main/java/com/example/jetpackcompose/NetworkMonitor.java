/*
 * @ Rights Reserved-T Fiber
 */

package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

public class NetworkMonitor {

    public interface NetworkListener {
        void onNetworkChange(boolean isConnected);
    }

    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;

    public NetworkMonitor(Context context, NetworkListener listener) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                listener.onNetworkChange(true);
            }

            @Override
            public void onLost(Network network) {
                listener.onNetworkChange(false);
            }
        };
    }

    public void register() {
        NetworkRequest request = new NetworkRequest.Builder().build();
        connectivityManager.registerNetworkCallback(request, networkCallback);
    }

    public void unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }
}