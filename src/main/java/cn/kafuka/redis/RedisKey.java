package cn.kafuka.redis;


public interface RedisKey {

    //1.用户相关
    //用户短信验证码
    String USER_MOBILE_SMSCODE = "userMobileSmsCode:";

    //用户token
    String USER_TOKEN_KEY = "userTokenKey:";

    //用户token过期时间
    String USER_EXPIRE_KEY = "userExpireKey:";



    //2.系统相关

    //(1).系统设置
    String SYS_SETTING_KEY = "sys_setting";
    String FILE_UPLOAD_PREFIX = "fileUploadPrefix";
    String FILE_ACCESS_PREFIX = "fileAccessPrefix";

    //(2).参数设置
    String PARAM_SETTING_KEY = "paramSettingKey";

    //系统数据(使用hash类型存储)
    String SYS_DATA = "sys_data";
    String DATA_DICT = "dataDict";
    String REGION = "region";
    String SYS_PARK_KEY = "sys_park";
    String SYS_PARK_INFO_KEY = "sys_park_info";
    String SYS_DATADICT_KEY = "sys_dataDict";
    String SYS_DATADICT_NAME = "sys_dataDict_name";

}