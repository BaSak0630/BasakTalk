package user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    int _userNum;
    String _userID;
    String _userPW;
    String _userEmail;
    String _userName;
    String _ip;
    boolean _server;

    public User(){
    }
    public User(int num, String ID, String PW, String email, String name){
        _userNum = num;
        _userID = ID;
        _userPW = PW;
        _userEmail = email;
        _userName = name;
    }
    public User(int num, String ID, String PW, String email){
        _userNum = num;
        _userID = ID;
        _userPW = PW;
        _userEmail = email;
        _userName = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "_userNum=" + _userNum +
                ", _userID='" + _userID + '\'' +
                ", _userPW='" + _userPW + '\'' +
                ", _userEmail='" + _userEmail + '\'' +
                ", _userName='" + _userName + '\'' +
                ", _ip='" + _ip + '\'' +
                ", _server=" + _server +
                '}';
    }
}
