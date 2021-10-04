package mail_system;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        execution();
    }

    public static void execution() {
        Scanner scan = new Scanner(System.in);
        Logical logic = new Logical();
        int i=0;
        while(i==0) {
            System.out.println("1.User Creation\n2.group creation\n3.group Assignment\n4.Compose Mail\n5.Inbox\n6.sent Mail\n7.DeleteMail\n0.Exit");
            int option = scan.nextInt();
            if (option >= 0 && option <= 10)
                switch (option) {
                    case 1:
                        Map<String, UserInfo> userMap = logic.getUserMap();
                        UserInfo info = new UserInfo();
                        System.out.println("Enter userName");
                        String userName = scan.next();
                        if (userMap != null) {
                            if (userMap.containsKey(userName)) {
                                System.out.println("UserName already Exist");
                                break;
                            }
                        }
                        if (logic.nameValidation(userName)) {
                            info.setUserName(userName);
                            boolean val = true;
                            while (val) {
                            System.out.println("Enter mail");
                            String email = scan.next();
                                if (logic.emailChecker(email)&&logic.mailExistanceCheck(email)) {
                                    val=false;
                                    info.setEmail(email);
                                    System.out.println("Enter password");
                                    String password = scan.next();
                                    info.setPassword(password);
                                    System.out.println(logic.userCreation(info));
                                } else
                                    System.out.println("Invalid emailId or mail Id Already Exist");
                            }
                            System.out.println(logic.getUserMap());
                        }
                        break;
                    case 2:
                        Map<String,GroupInfo> groupMap = logic.getGroupMap();
                        GroupInfo groupInfo = new GroupInfo();
                        scan.nextLine();
                        System.out.println("Enter Group Name");
                        String groupName = scan.nextLine();
                        if(groupMap!=null) {
                            if(groupMap.containsKey(groupName)) {
                                System.out.println("Group Name alredy Exist");
                                break;
                            }
                        }

                        if(logic.nameValidation(groupName)) {
                            groupInfo.setGroupName(groupName);
                            boolean flag = true;
                            while(flag) {
                                System.out.println("Enter groupMailId");
                                String groupMailId = scan.next();
                                if (logic.emailChecker(groupMailId)&&logic.mailExistanceCheck(groupMailId)) {
                                    flag = false;
                                        if (logic.mailExistanceCheck(groupMailId)) {
                                            groupInfo.setGroupEmail(groupMailId);
                                            System.out.println("Enter group Password");
                                            String groupPassword = scan.next();
                                            groupInfo.setGroupPassword(groupPassword);
                                            scan.nextLine();
                                            System.out.println("Enter group Description");
                                            String description = scan.nextLine();
                                            groupInfo.setDescription(description);
                                            System.out.println(logic.groupCreation(groupInfo));
                                        } else
                                            System.out.println("mailId already Exist");
                                } else
                                    System.out.println("Invalid mail Id or mailId already Exist");
                            }
                        }
                        break;

                    case 3:
                        if(logic.getGroupMap().size()!=0) {
                            System.out.println("Enter groupName");
                            String grpName = scan.next();
                                if (logic.groupExistance(grpName)) {
                                    if (logic.getUserMap().size() != 0) {
                                        System.out.println("Enter userName");
                                        String user = scan.next();
                                        if (logic.userExitance(user)) {
                                            System.out.println("1.Add\n2.Remove");
                                            int choice = scan.nextInt();
                                            System.out.println(logic.groupAssignment(user, grpName, choice));
                                            System.out.println(logic.getGroupMap());
                                        } else
                                            System.out.println("invalid userName");
                                    } else
                                        System.out.println("There will be no user to add/remove");
                                }
                                else
                                    System.out.println("invalid groupName");
                        }
                        else
                            System.out.println("There will be no groups available to add/remove");
                        break;

                    case 4:
                        if(logic.getUserMap().size()!=0) {
                            MailInfo mailInfo = new MailInfo();
                            boolean flag = true;
                            while(flag) {
                                System.out.println("Enter from user");
                                String fromUser = scan.next();
                                if (logic.userExitance(fromUser)) {
                                    flag = false;
                                    mailInfo.setFromUser(fromUser);
                                    boolean val = true;
                                    while (val) {
                                        System.out.println("Enter to User");
                                        String toUser = scan.next();
                                        if (logic.userExitance(toUser)) {
                                            val = false;
                                            mailInfo.setToUser(toUser);
                                            scan.nextLine();
                                            System.out.println("Enter subject");
                                            String subject = scan.nextLine();
                                            mailInfo.setMailSubject(subject);
                                            System.out.println("Enter description");
                                            String content = scan.nextLine();
                                            mailInfo.setContent(content);
                                            System.out.println(logic.composeMail(mailInfo));
                                            System.out.println(logic.getInboxMap());
                                            System.out.println(logic.getSentMap());
                                        } else
                                            System.out.println("Invalid to user");
                                    }
                                } else
                                    System.out.println("Invalid from user");
                            }
                        }
                        else
                            System.out.println("There will be no user to sent mail");
                        break;

                    case 5:
                        if(logic.getUserMap().size()!=0) {
                            boolean flag = true;
                            while (flag) {
                                System.out.println("Enter user Name");
                                String user = scan.next();
                                if (logic.userExitance(user)) {
                                    flag=false;
                                    HashMap<Integer, MailInfo> inboxMap = logic.displayInbox(user);
                                    if (inboxMap != null) {
                                        System.out.println("INBOX");
                                        for (Map.Entry<Integer, MailInfo> entry : inboxMap.entrySet()) {
                                            System.out.println(entry);
                                        }
                                    } else
                                        System.out.println("No inbox mail");
                                } else {
                                    System.out.println("Invalid username");
                                }
                            }
                        }
                        else
                            System.out.println("There is no user");
                        break;

                    case 6:
                        if(logic.getUserMap().size()!=0) {
                            boolean flag = true;
                            while (flag) {
                                System.out.println("Enter user Name");
                                String user = scan.next();
                                if (logic.userExitance(user)) {
                                    flag=false;
                                    HashMap<Integer, MailInfo> sentMap = logic.displaySentMail(user);
                                    if (sentMap != null) {
                                        System.out.println("SENT MAILS");
                                        for (Map.Entry<Integer, MailInfo> entry : sentMap.entrySet()) {
                                            System.out.println(entry);
                                        }
                                    } else
                                        System.out.println("No sent mail");
                                } else {
                                    System.out.println("Invalid username");
                                }
                            }
                        }
                        else
                            System.out.println("There is no user");
                        break;

                    case 7:
                        if(logic.getUserMap().size()!=0) {
                            boolean flag = true;
                            while (flag) {
                                System.out.println("Enter userName");
                                String name = scan.next();
                                if (logic.userExitance(name)) {
                                    flag = false;
                                    System.out.println("1.inbox\n2.sent");
                                    int choice = scan.nextInt();
                                    System.out.println("Enter serialNumber to delete");
                                    int sNo = scan.nextInt();
                                    System.out.println(logic.deleteMail(name, choice, sNo));
                                    System.out.println(logic.getSentMap());
                                    System.out.println(logic.getInboxMap());
                                } else
                                    System.out.println("Invalid userName");
                            }
                        }
                        else
                            System.out.println("There is No user to delete");
                        break;

                    case 0:
                        i++;
                        break;

                }
        }

    }
}
