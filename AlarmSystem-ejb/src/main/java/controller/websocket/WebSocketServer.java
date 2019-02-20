package controller.websocket;


import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

@ApplicationScoped
@ServerEndpoint("/alarmNotify")
public class WebSocketServer {

    private static Vector<Session> connectedSessions = new Vector<>();;
    private static Vector<Session> toBeRemovedSessions =  new Vector<>();


    @OnOpen
    public void open(Session session) {
        connectedSessions.add(session);
        System.out.println(connectedSessions.size());
    }

    @OnClose
    public void close(Session session) {
        connectedSessions.remove(session);
    }

    public void sendMessageToSessions(Object o) {
        Session currentSendingSession = null;
        for (Session session : connectedSessions) {
            try {
                currentSendingSession = session;
                String jsonObject = new Gson().toJson(o);
                session.getBasicRemote().sendText(jsonObject);
            } catch (IOException e) {
                toBeRemovedSessions.add(currentSendingSession);
            }
        }
        this.clearSessionsToBeDeleted();
    }

    private void clearSessionsToBeDeleted() {
        for(Session session : toBeRemovedSessions){
            connectedSessions.remove(session);
        }

        toBeRemovedSessions.clear();
    }
}
