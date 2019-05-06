package com.ycs.work.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycs.work.wechat.itf.client.WxIClient;

public class WxHiClientManager {
    private static Map<String, WxIClient> clients = new HashMap<String, WxIClient>();

    public Map<String, WxIClient> getClients() {
        return clients;
    }

    public void setClients(List<WxIClient> clients) {
        for (WxIClient client : clients) {
            WxHiClientManager.clients.put(client.getClientName(), client);
        }
    }

    public static WxIClient getClient(String clientName) {
        return clients.get(clientName);
    }
}
