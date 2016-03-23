package com.xiangtai.framework.core.util;

import com.xiangtai.framework.core.entity.UserFormMap;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public void encryptPassword(UserFormMap userFormMap) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        userFormMap.put("credentialsSalt", salt);
        String algorithmName = "md5";
        int hashIterations = 2;
        String newPassword = new SimpleHash(algorithmName, userFormMap.get("password"), ByteSource.Util.bytes(userFormMap.get("accountName") + salt), hashIterations).toHex();
        userFormMap.put("password", newPassword);
    }

    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        UserFormMap userFormMap = new UserFormMap();
        userFormMap.put("password", "123456");
        userFormMap.put("accountName", "admin");
        passwordHelper.encryptPassword(userFormMap);
        System.out.println(userFormMap);
    }
}
