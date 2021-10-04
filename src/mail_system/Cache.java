package mail_system;

import java.util.HashMap;
import java.util.Map;

public enum Cache {
    OBJECT;
    Map<String ,UserInfo> userMap = new HashMap<>();
    Map<String,GroupInfo>groupMap = new HashMap<>();
    Map<String,HashMap<Integer,MailInfo>> inboxMap= new HashMap();
    Map<String,HashMap<Integer,MailInfo>> sentMap= new HashMap();

    public Map<String, HashMap<Integer, MailInfo>> getSentMap() {
        return sentMap;
    }

    public void setSentMap(Map<String, HashMap<Integer, MailInfo>> sentMap) {
        this.sentMap = sentMap;
    }

    public Map<String, HashMap<Integer, MailInfo>> getInboxMap() {
        return inboxMap;
    }

    public void setInboxMap(Map<String, HashMap<Integer, MailInfo>> inboxMap) {
        this.inboxMap = inboxMap;
    }

    public Map<String, GroupInfo> getGroupMap() {
        return groupMap;
    }

    public void setGroupMap(Map<String, GroupInfo> groupMap) {
        this.groupMap = groupMap;
    }

    public Map<String, UserInfo> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, UserInfo> userMap) {
        this.userMap = userMap;
    }
}
