package mail_system;

import java.util.List;

public class GroupInfo {
    private String groupName;
    private String groupEmail;
    private String groupPassword;
    private String description;
    private List<String>userlist;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupEmail() {
        return groupEmail;
    }

    public void setGroupEmail(String groupEmail) {
        this.groupEmail = groupEmail;
    }

    public String getGroupPassword() {
        return groupPassword;
    }

    public void setGroupPassword(String groupPassword) {
        this.groupPassword = groupPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<String> userlist) {
        this.userlist = userlist;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "groupName='" + groupName + '\'' +
                ", groupEmail='" + groupEmail + '\'' +
                ", groupPassword='" + groupPassword + '\'' +
                ", description='" + description + '\'' +
                ", userlist=" + userlist +
                '}';
    }
}
