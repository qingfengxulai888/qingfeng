package com.chat.app.service;

import com.google.common.base.Preconditions;
import com.us.base.redis.RedisCommon;
import com.us.base.util.MD5Encryption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by xulin on 18/11/10.
 */

@Service(value = "loginService")
public class LoginService {
    //vip用户的标志
    public final static String VIP = "vip";
    //保存登陆信息
    public final static String ADD_TOKEN = ""
            + " local token = redis.call('get', KEYS[1]); "
            + " if token then "
            + "     redis.call('del',token); "
            + " end "
            + " redis.call('set',KEYS[1],KEYS[2]); "
            + " redis.call('set',KEYS[2],KEYS[1]); "
            + " return 1 ";

    //注销用户
    public final static String DEL_TOKEN = ""
            + " local token = redis.call('get',KEYS[1]); "
            + " redis.call('del',token); "
            + " redis.call('del',KEYS[1]); "
            + " return 1 ";

    //添加邀请码
    public final static String ADD_INVITE_CODE = ""
            + " local token = redis.call('get', KEYS[1]); "
            + " if token then "
            + "     redis.call('del',token); "
            + " end "
            + " redis.call('set',KEYS[1],KEYS[2]); "
            + " return 1 ";

    //注销邀请码
    public final static String DEL_INVITE = ""
            + " local token = redis.call('get',KEYS[1]); "
            + " redis.call('del',KEYS[1]); "
            + " return 1 ";

    //验证码存活时间(5分钟)
    private final static int life_time = 5*60;
    /**
     * 存放短信验证码,并设置过期时间为5分钟
     * @param telephone
     * @param code
     * @param prefix
     * @return
     */
    public static void saveCode(String telephone,String code,String prefix) {
        String key = prefix+"-"+telephone;
        RedisCommon.set(key,code);
        RedisCommon.expire(key,500);
    }


    /**
     * 校验短信验证码
     * @param telephone
     * @param code
     * @param prefix
     * @return
     */
    public static boolean verifyCode(String telephone,String code,String prefix){
        String key = prefix+"-"+telephone;
        String codeStr = RedisCommon.get(key);
        if(code.equals(codeStr))
            return true;
        return false;
    }

    /**
     * 保存用户登陆信息,并返回token
     * @param user
     * @return
     * @throws Exception
     */
    public static String login(Long user) throws Exception {
        String token = createToken(user);
        List<String> keys = new ArrayList<String>();
        keys.add(String.valueOf(user));
        keys.add(token);
        Preconditions.checkState(Integer.valueOf(String.valueOf(RedisCommon.eval(ADD_TOKEN,keys,new ArrayList<String>()))) == 1,"保存登陆信息失败.");

        return token;
    }

    /**
     * 添加邀请码
     * @param inviteCode
     * @return
     * @throws Exception
     */
    public static void addInvite(String inviteCode) throws Exception {

        List<String> keys = new ArrayList<String>();
        keys.add(inviteCode);
        keys.add(VIP);
        Preconditions.checkState(Integer.valueOf(String.valueOf(RedisCommon.eval(ADD_INVITE_CODE,keys,new ArrayList<String>()))) == 1,"保存登陆信息失败.");
    }

    /**
     * 注销用户
     * @param user
     * @throws Exception
     */
    public static void logout(String user) throws Exception {
        List<String> keys = new ArrayList<String>();
        keys.add(user);
        Preconditions.checkState(Integer.valueOf(String.valueOf(RedisCommon.eval(DEL_TOKEN,keys,new ArrayList<String>()))) == 1,"注销用户登陆信息失败.");
    }



    /**
     * 创建token值
     * @param user
     * @return
     * @throws Exception
     */
    private static String createToken(Long user) throws Exception {
        Random r = new Random((new Date().getTime()));
        return MD5Encryption.getMD5(user + (new Date()).getTime() + String.valueOf(r.nextInt(100000000)));
    }


    /**
     * 判断邀请码是否符合
     * @param inviteCode
     * @return
     */
    public boolean checkInviteCode(String inviteCode) {
        String codeStr = RedisCommon.get(inviteCode);
        if(!VIP.equals(codeStr))
            return false;
        return true;
    }

    /**
     * 删除邀请码
     * @param inviteCode
     * @throws Exception
     */
    public static void deleteInviteCode(String inviteCode) throws Exception{
        List<String> keys = new ArrayList<String>();
        keys.add(inviteCode);
        Preconditions.checkState(Integer.valueOf(String.valueOf(RedisCommon.eval(DEL_INVITE,keys,new ArrayList<String>()))) == 1,"邀请码删除失败");
    }
}
