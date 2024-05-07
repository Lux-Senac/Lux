package br.com.lux.services.gravatar;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class GravatarService {

    public String getGravatarUrl(String email) {
        String hash = md5Hex(email);
        return "https://www.gravatar.com/avatar/" + hash;
    }

    private String md5Hex(String message) {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(message.getBytes());
            return Base64.getEncoder().encodeToString(digest);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}
