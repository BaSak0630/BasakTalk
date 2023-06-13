public class UserDTO {
    int _userNum;
    String _userID;
    String _userPW;
    String _userEmail;
    String _userName;

    public UserDTO(int num, String ID, String PW, String email, String name){
        _userNum = num;
        _userID = ID;
        _userPW = PW;
        _userEmail = email;
        _userName = name;
    }
    public UserDTO(int num, String ID, String PW, String email){
        _userNum = num;
        _userID = ID;
        _userPW = PW;
        _userEmail = email;
        _userName = null;
    }
    public int getNum(){
        return _userNum;
    }
    public String getID(){
        return _userID;
    }
    public String getPW(){
        return _userPW;
    }
    public String getEmail(){
        return _userEmail;
    }
    public String getName(){
        return _userName;
    }
    public void setNum(int num){
        _userNum = num;
    }
    public void setID(String ID){
        _userID = ID;
    }
    public void setPW(String PW){
        _userPW = PW;
    }
    public void setEmail(String email){
        _userEmail = email;
    }
    public void setName(String Name){
        _userName = Name;
    }
}
