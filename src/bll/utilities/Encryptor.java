package bll.utilities;

import bll.utilities.BCrypt.BCrypt;

import java.io.IOException;

public class Encryptor {

    public String encrypt(String string) throws IOException {
        return BCrypt.hashpw(string, BCrypt.gensalt(6));
    }

    public boolean check(String string, String hashed) throws IOException {
        return BCrypt.checkpw(string, hashed);
    }
}
