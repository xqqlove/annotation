package myfirstannotation;

import java.util.List;

public class PasswordUtils {
    @UseCase(id=47,description = "Password mudt contain at least one numeric")
    public boolean validatePassWord(String password){
        return (password.matches("\\w*\\d\\w*"));
    }
    @UseCase(id = 48)
    public String encrypPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }
    @UseCase(id = 49,description = "New Password can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPassword,String password){
        return !prevPassword.contains(password);
    }
}
