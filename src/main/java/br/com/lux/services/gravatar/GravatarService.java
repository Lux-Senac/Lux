package br.com.lux.services.gravatar;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class GravatarService {

    public String getGravatarUrl(String email)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            return "https://www.gravatar.com/avatar/" + hex(md.digest(email.getBytes("UTF-8")));
        }
        catch (NoSuchAlgorithmException e)
        {
            return "https://www.gravatar.com/avatar/pmJ/n7umDWOlthLrafpdnw==";
        }
        catch (UnsupportedEncodingException e)
        {
            return "https://www.gravatar.com/avatar/pmJ/n7umDWOlthLrafpdnw==";
        }
    }

    private String hex(byte[] array)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i)
        {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

}
