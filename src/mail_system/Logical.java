package mail_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logical {
    String userCreation(UserInfo userInfo){
        if(userInfo!=null) {
            Map<String, UserInfo> userMap = getUserMap();
            if(userMap==null)
                userMap = new HashMap<>();
            userMap.put(userInfo.getUserName(), userInfo);
            Cache.OBJECT.setUserMap(userMap);
            return "User created";
        }
        else
            return "No user Data or user data Missing";
    }
    String groupCreation(GroupInfo groupInfo){
        if(groupInfo!=null){
            HashMap<String ,GroupInfo>groupMap = new HashMap<>();
            groupMap.put(groupInfo.getGroupName(),groupInfo);
            Cache.OBJECT.setGroupMap(groupMap);
        }
        return "Group Created";
    }
    boolean nameValidation(String userName){
        if(userName!=null) {
            String regex = "[a-zA-Z0-9_]*[^a-zA-Z0-9]*";
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(userName);
            if (match.find())
                return true;
            else
                return false;
        }
        return false;
    }
    boolean emailChecker(String email){
        if(email!=null) {
            String regex = "[a-z0-9_]*[@]+[a-z]+[.][a-z]{2,3}";
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(email);
            if (match.find()) {
                return true;
            } else
                return false;
        }
        else
            return false;
    }

    boolean mailExistanceCheck(String groupMail){
        Map<String,UserInfo>userMap = getUserMap();
        Map<String,GroupInfo>groupMap = getGroupMap();
        for(Map.Entry<String ,UserInfo>entry:userMap.entrySet()){
            UserInfo info = entry.getValue();
            if(info.getEmail().equals(groupMail))
                return false;
        }
        for(Map.Entry<String ,GroupInfo>entry:groupMap.entrySet()){
            GroupInfo info = entry.getValue();
            if(info.getGroupEmail().equals(groupMail))
                return false;
        }
        return true;
    }

    boolean groupExistance(String groupName){
        if(getGroupMap().containsKey(groupName))
            return true;
        else
            return false;
    }
    boolean userExitance(String userName){
        if(getUserMap().containsKey(userName))
            return true;
        else
            return false;
    }

    String groupAssignment(String userName,String groupName,int type){
        Map<String,GroupInfo> groupMap = getGroupMap();
        String status=null;
        if(groupMap!=null) {
                GroupInfo info = groupMap.get(groupName);
                List<String> memberList = info.getUserlist();
                if (memberList == null)
                    memberList = new ArrayList<>();
                if(type==1) {
                    if(!(memberList.contains(userName)))
                        memberList.add(userName);
                }
                else if(type==2) {
                    if(memberList.contains(userName))
                        memberList.remove(userName);
                }
                info.setUserlist(memberList);
                groupMap.put(groupName,info);
                Cache.OBJECT.setGroupMap(groupMap);

            Map<String,UserInfo>userMap = getUserMap();
            if(userMap!=null){
                    UserInfo userInfo = userMap.get(userName);
                    List<String> groupList = userInfo.getGroupList();
                    if(groupList==null)
                        groupList = new ArrayList<>();
                    if(type==1) {
                        if (!(groupList.contains(groupName))) {
                            groupList.add(groupName);
                            status =  "user Added";
                        }
                        else{
                            status =  "user Already Exist in the group";
                        }
                    }
                    else if(type==2) {
                        if (groupList.contains(userName)) {
                            groupList.remove(groupName);
                            status = "user removed";
                        }
                        else {
                            status = "user doesn't exist in the group";
                        }
                    }
                    userInfo.setGroupList(groupList);
                    userMap.put(userName,userInfo);
                    Cache.OBJECT.setUserMap(userMap);

            }
        }
    return status;
    }
static int serialNo =1;
    String composeMail(MailInfo mailInfo){
        String fromuser = mailInfo.getFromUser();
        Map<String,HashMap<Integer,MailInfo>>sentMap = getSentMap();
        HashMap<Integer,MailInfo> inner = sentMap.getOrDefault(fromuser,new HashMap<>());
        int sNo = serialNo++;
        mailInfo.setSerialNo(sNo);
        inner.put(sNo,mailInfo);
        sentMap.put(fromuser,inner);
        Cache.OBJECT.setSentMap(sentMap);

        String toUser = mailInfo.getToUser();
        Map<String,HashMap<Integer,MailInfo>>inboxMap = getInboxMap();
        HashMap<Integer,MailInfo> sentInner =inboxMap.getOrDefault(toUser,new HashMap<>());
        sentInner.put(sNo,mailInfo);
        inboxMap.put(toUser,sentInner);
        Cache.OBJECT.setInboxMap(inboxMap);
        return "Mail sent";
    }

    String deleteMail(String userName,int type,int serialNo){
        String status;
        HashMap<Integer,MailInfo> map=null;
        if(type==1)
            map = getInboxMap().get(userName);
        else if(type==2)
            map = getSentMap().get(userName);
            if(map!=null){
                if(map.containsKey(serialNo)){
                    map.remove(serialNo);
                    status = "Mail Deleted";
                }
                else
                    status = "Invalid serial Number";
            }
            else
                status ="There is No mail to delete";
        return status;
    }

    HashMap<Integer,MailInfo> displayInbox(String userName){
        return getInboxMap().get(userName);
    }
    HashMap<Integer,MailInfo>displaySentMail(String userName){
        return getSentMap().get(userName);
    }
    Map<String,UserInfo> getUserMap(){
        return Cache.OBJECT.getUserMap();
    }
    Map<String,GroupInfo>getGroupMap(){
        return Cache.OBJECT.getGroupMap();
    }
    Map<String,HashMap<Integer,MailInfo>> getInboxMap(){
        return Cache.OBJECT.getInboxMap();
    }
    Map<String,HashMap<Integer,MailInfo>>getSentMap(){
        return Cache.OBJECT.getSentMap();
    }
}
