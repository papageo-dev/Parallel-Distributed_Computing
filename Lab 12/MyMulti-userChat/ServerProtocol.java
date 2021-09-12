package MyMulti-userChat;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ServerProtocol {
	

    // Delivers a message from one user to all others (broadcasting)
    void processRequest(String message, ServerThread excludeUser) {
        for (ServerThread aUser : MultithreadedChatServerTCP.userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    //Stores username of the new connected client
    void addUserName(String userName) {
    	MultithreadedChatServerTCP.userNames.add(userName);
    }
 
    // When a client is disconneted, removes the associated username and his/her UserThread
    void removeUser(String userName, ServerThread aUser) {
        boolean removed = MultithreadedChatServerTCP.userNames.remove(userName);
        if (removed) {
        	MultithreadedChatServerTCP.userThreads.remove(aUser);
            System.out.println("*** The user " + userName + " quitted");
        }
    }
 

    // Returns all usernames
    ArrayList<String> getUserNames() {
        return MultithreadedChatServerTCP.userNames;
    }
 

    // Returns true if there are other users connected (not count the currently connected user)
    boolean hasUsers() {
        return !MultithreadedChatServerTCP.userNames.isEmpty();
    }
    
}