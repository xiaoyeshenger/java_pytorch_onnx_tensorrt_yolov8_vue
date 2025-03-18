package cn.kafuka.codeGen;

import cn.kafuka.bo.po.*;
import cn.kafuka.bo.po.Customer;
import com.google.common.base.CaseFormat;
import cn.kafuka.bo.vo.BeanVo;
import cn.kafuka.util.ReflectUtil;
import freemarker.template.TemplateExceptionHandler;
import lombok.SneakyThrows;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;


/*
 * 代码生成步骤:
 * 1.使用 CodeGenerator 自动生成dto,service,controller,mapper
 * 2.将pojo放入codeGen下面的po中
 * */

/*
 * @Author: zhangyong
 * description: 代码生成器，根据数据表名称生成对应的dao、Service、Controller简化开发
 * @Date: 2020/3/23 22:26
 * @Param:
 * @Return:
 */
public class CodeGenerator {

    //1.包路径
    // 项目基础包名称
    public static final String BASE_PACKAGE = "cn.kafuka";

    // pojo所在包
    public static final String POJO_PACKAGE = BASE_PACKAGE + ".bo.po";

    //dto所在包
    public static final String DTO_PACKAGE = BASE_PACKAGE + ".bo.dto";

    // dao所在包
    public static final String DAO_PACKAGE = BASE_PACKAGE + ".bo.dao";

    // daoImpl所在包
    public static final String DAO_IMPL_PACKAGE = DAO_PACKAGE + ".impl";

    // Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    // ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    // Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    // Vue及js所在包
    public static final String VUE_PACKAGE = BASE_PACKAGE + ".vue";


    //2.文件路径
    //模板位置
    private static final String TEMPLATE_FILE_PATH_MYSQL = "src/main/resources/templates/genCodeMysql";

    private static final String TEMPLATE_FILE_PATH_MONGO= "src/main/resources/templates/genCodeMongo";

    //java文件路径
    private static final String JAVA_PATH = "src/main/java";

    //资源文件路径
    private static final String RESOURCES_PATH = "src/main/resources";

    // 生成的Dao存放路径
    private static final String PACKAGE_PATH_DAO = packageConvertPath(DAO_PACKAGE);

    // 生成的DaoImpl实现存放路径
    private static final String PACKAGE_PATH_DAO_IMPL = packageConvertPath(DAO_IMPL_PACKAGE);

    // 生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);

    // 生成的ServiceImpl实现存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);

    // 生成的Controller存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);

    //生成的dto存放路径
    private static final String PACKAGE_PATH_DTO = packageConvertPath(DTO_PACKAGE);

    //生成的vue存放路径
    private static final String PACKAGE_PATH_VUE = packageConvertPath(VUE_PACKAGE);

    //生成的代码存放路径
    private static final String PACKAGE_PATH_BASE = "src/main/java/cn/kafuka/";

    // @author
    private static final String AUTHOR = "zhangyong";

    // @date
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());


    //mysql
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    //public static final String MYSQL_URL = "jdbc:mysql://192.168.2.241:3306/algorithm_center?serverTimezone=UTC&characterEncoding=utf-8&nullCatalogMeansCurrent=true&useSSL=false";
    public static final String MYSQL_URL = "jdbc:mysql://192.168.20.206:3306/algorithm_center?serverTimezone=UTC&characterEncoding=utf-8&nullCatalogMeansCurrent=true&useSSL=false";
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "jskjmy666999";

    //gen mapper 配置文件路径
    public static final String Mybatis_GenCfgFilePath ="/mybatis/generatorConfig.xml";

    /**
     * @Author: zhangyong
     * description: 输入pojo名字，自动生成 dao daoImpl service serviceImpl controller
     * @Date: 2020/3/24 9:11
     * @Param:object--->pojo的类型，pojoName---> pojo名字，pojoIncludeMultipartFile--->pojo是否包含MultipartFile文件
     * @Return:
     */
/*
    public static void main(String[] args) {

        genCodeByPoJoName(
              new RoleModel(),
              "RoleModel",
                      "role_model",
                      "角色模型关系",
                      "algorithmCenter",
                      false,
                      "mysql",
                      "cn.kafuka.mapper",
                      "src/main/java");
    }
*/

    /**
     * @Author: zhangyong
     * description: ******通过pojo名称生成代码 如输入表名称 "sys"******
     * @Date: 2020-06-24 13:00
     * @Param:object:JavaBean(如new Person())
     *        pojoName:javaBean名称(如Person)
     *        includeMultipartFile：JavaBean是否包含文件属性(如false)
     *        dbType:使用的数据库类型mysql/mongo(如mysql)
     *        moduleName:模块名称(代码生成到指定的位置(如sys模块))
     *        targetPackage:mapper生成到指定的包路径
     *        targetProject:mapper生成到指定的程序路径
     * @Return:
     */
    public static void genCodeByPoJoName(Object object,String pojoName,String tableName,String poJoCnName,String moduleName, Boolean includeMultipartFile,String dbType,String targetPackage,String targetProject) {
        //1.生成后端代码
        //genReqDto(pojoName,object,includeMultipartFile,dbType,moduleName);

        //genExcelVoAndListener(pojoName,object,dbType,moduleName);

        if("mysql".equals(dbType)){
            genMapper(object.getClass(),tableName,targetPackage,targetProject);
        }
        if("mongo".equals(dbType)){
            genDao(pojoName,dbType,moduleName);
        }
        //genService(pojoName,poJoCnName,object,includeMultipartFile,dbType,moduleName);
        //genController(pojoName,poJoCnName,includeMultipartFile,dbType,moduleName);
        //2.生成前端vue和api.js代码
        //genVueAndApiJs(pojoName,poJoCnName,object,includeMultipartFile,dbType);
    }

    //1.各种页面生成
    //(1).生成requestDto
    public static void genReqDto(String tableName,Object object,Boolean includeMultipartFile,String dbType,String moduleName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration(dbType);
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = toUpperCaseInitial(tableName);
            data.put("baseRequestMapping", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("pojoNameUpperCamel", modelNameUpperCamel);
            data.put("pojoNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("basePackageController", CONTROLLER_PACKAGE);
            data.put("basePackageService", SERVICE_PACKAGE);
            data.put("basePackagePoJo", POJO_PACKAGE);
            data.put("basePackageDto", DTO_PACKAGE);

            List<BeanVo> fileNameAndTypeList = ReflectUtil.getFileNameAndTypeList(object);
            data.put("fileNameAndTypeList", fileNameAndTypeList);

            File file = new File(JAVA_PATH + PACKAGE_PATH_DTO + modelNameUpperCamel + "ReqDto.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            if(includeMultipartFile){
                cfg.getTemplate("fileDto.ftl").process(data, writer);
            }else {
                cfg.getTemplate("dto.ftl").process(data,writer);
            }
            System.out.println(modelNameUpperCamel + "ReqDto.java 生成成功");


            File file1 = new File(JAVA_PATH + PACKAGE_PATH_DTO + modelNameUpperCamel + "PageReqDto.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
            cfg.getTemplate("pageDto.ftl").process(data, writer1);
            System.out.println(modelNameUpperCamel + "PageReqDto.java 生成成功");

        } catch (Exception e) {
            throw new RuntimeException("生成Dto失败", e);
        }
    }

    //(2).生成dao及daoImpl
    public static void genDao(String tableName,String dbType,String moduleName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration(dbType);
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = toUpperCaseInitial(tableName);
            data.put("pojoNameUpperCamel", modelNameUpperCamel);
            data.put("pojoNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("basePackagePoJo", POJO_PACKAGE);
            data.put("basePackageDao", DAO_PACKAGE);
            data.put("basePackageDaoImpl", DAO_IMPL_PACKAGE);

            File file = new File(JAVA_PATH + PACKAGE_PATH_DAO + modelNameUpperCamel + "Dao.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            cfg.getTemplate("dao.ftl").process(data, writer);
            System.out.println(modelNameUpperCamel + "Dao.java 生成成功");

            File file1 = new File(JAVA_PATH + PACKAGE_PATH_DAO_IMPL + modelNameUpperCamel + "DaoImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
            cfg.getTemplate("daoImpl.ftl").process(data, writer1);
            System.out.println(modelNameUpperCamel + "DaoImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Dao失败", e);
        }
    }

    //(3).生成service及serviceImpl
    public static void genService(String tableName,String poJoCnName,Object object,Boolean includeMultipartFile,String dbType,String moduleName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration(dbType);
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = toUpperCaseInitial(tableName);
            data.put("pojoNameUpperCamel", modelNameUpperCamel);
            data.put("pojoNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("basePackageService", SERVICE_PACKAGE);
            data.put("basePackageServiceImpl", SERVICE_IMPL_PACKAGE);
            data.put("basePackagePoJo", POJO_PACKAGE);
            data.put("basePackageDto", DTO_PACKAGE);
            data.put("basePackageDao", DAO_PACKAGE);
            data.put("pojoCnName", poJoCnName);

            List<BeanVo> fileNameAndTypeList = ReflectUtil.getFileNameAndTypeList(object);
            data.put("fileNameAndTypeList", fileNameAndTypeList);

            File file = new File(JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            if(includeMultipartFile){
                cfg.getTemplate("fileService.ftl").process(data, writer);
            }else {
                cfg.getTemplate("service.ftl").process(data,writer);
            }
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
            if(includeMultipartFile){
                cfg.getTemplate("fileServiceImpl.ftl").process(data, writer1);
            }else {
                cfg.getTemplate("serviceImpl.ftl").process(data, writer1);
            }
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    //(4).生成controller
    public static void genController(String tableName,String poJoCnName,Boolean includeMultipartFile,String dbType,String moduleName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration(dbType);
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = toUpperCaseInitial(tableName);
            data.put("baseRequestMapping", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("pojoNameUpperCamel", modelNameUpperCamel);
            data.put("pojoNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("basePackageController", CONTROLLER_PACKAGE);
            data.put("basePackageService", SERVICE_PACKAGE);
            data.put("basePackagePoJo", POJO_PACKAGE);
            data.put("basePackageDto", DTO_PACKAGE);
            data.put("pojoCnName", poJoCnName);

            File file = new File(JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            if(includeMultipartFile){
                cfg.getTemplate("fileController.ftl","UTF-8").process(data, writer);
            }else {
                cfg.getTemplate("controller.ftl","UTF-8").process(data, writer);
            }
            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }
    }

    //(6).生成excel上传请求对象excelVo和监听器Listener
    public static void genExcelVoAndListener(String tableName,Object object,String dbType,String moduleName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration(dbType);
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = toUpperCaseInitial(tableName);
            data.put("baseRequestMapping", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("pojoNameUpperCamel", modelNameUpperCamel);
            data.put("pojoNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("basePackageController", CONTROLLER_PACKAGE);
            data.put("basePackageService", SERVICE_PACKAGE);
            data.put("basePackagePoJo", POJO_PACKAGE);
            data.put("basePackageDto", DTO_PACKAGE);
            data.put("basePackageExcel", BASE_PACKAGE + ".excel");
            data.put("moduleName", moduleName);

            List<BeanVo> fileNameAndTypeList = ReflectUtil.getFileNameAndTypeList(object);
            data.put("fileNameAndTypeList", fileNameAndTypeList);

            File file = new File(PACKAGE_PATH_BASE + "excel/" + modelNameUpperCamel + "ExcelVo.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            cfg.getTemplate("excelVo.ftl").process(data, writer);

            System.out.println(modelNameUpperCamel + "ExcelVo.java 生成成功");


            File file1 = new File(PACKAGE_PATH_BASE + "excel/" + modelNameUpperCamel + "ExcelListener.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
            cfg.getTemplate("excelListener.ftl").process(data, writer1);
            System.out.println(modelNameUpperCamel + "ExcelListener.java 生成成功");

        } catch (Exception e) {
            throw new RuntimeException("生成excelVo/excelListener/excelExportDto失败", e);
        }
    }

    //(7).生成mapper
    public static void genMapper(Class obj,String tableName,String targetPackage,String targetProject){
        //1.生成建表sql
        String sql = generateSql(obj, tableName);

        //2.向mysql写入建表sql语句(是否删除已存在的表)
        execSql(sql.toString(),tableName,true);

        //3.通过Mybatis逆向工程生成mapper
        genMapperByConfig(Mybatis_GenCfgFilePath,tableName,targetPackage,targetProject);
    }


    //(8).通过实体类生成建表sql
    public static String generateSql(Class obj,String tableName){

        //1.将javabean转为建表sql字符串
        Field[] fields = obj.getDeclaredFields();
        String param;
        String cameCaseColumn;
        String underScoreCaseColumn;
        StringBuilder sql = new StringBuilder();
        if(tableName==null||tableName.equals("")){
            // 未传表明默认用类名
            tableName = obj.getName().substring(obj.getName().lastIndexOf(".")+1);
        }
        /**
         * 以下部分生成建表Sql
         */
        sql.append("create table if not exists ").append(tableName).append("( \r\n");
        boolean firstId = true;
        for(Field f : fields) {
            cameCaseColumn = f.getName();
            underScoreCaseColumn = cameCaseColumn;
            for (int i = 0; i < cameCaseColumn.length(); i++) {
                if (Character.isUpperCase(cameCaseColumn.charAt(i))) {
                    // 将javabean中小驼峰命名变量的“大写字母”转换为“_小写字母”
                    underScoreCaseColumn = cameCaseColumn.substring(0, i) + '_' + cameCaseColumn.substring(i, i + 1).toLowerCase() + cameCaseColumn.substring(i + 1, cameCaseColumn.length());
                }
            }
            sql.append(underScoreCaseColumn).append(" ");
            param = f.getType().getTypeName();
            if (param.equals("java.lang.Integer")) {
                sql.append("INTEGER");
            } else if (param.equals("java.lang.Long")) {
                sql.append("BIGINT(20)");
            } else if (param.equals("java.lang.Boolean")) {
                sql.append("tinyint");
            } else if (param.equals("java.lang.Byte")) {
                sql.append("tinyint");
            } else if (param.equals("java.lang.Double")) {
                sql.append("DOUBLE");
            } else {
                // 根据需要自行修改
                sql.append("VARCHAR(20)");
            }
            if (firstId) {
                // 默认第一个字段为ID主键
                sql.append(" PRIMARY KEY AUTO_INCREMENT");
                firstId = false;
            }

            sql.append(",\n");
        }
        sql.delete(sql.lastIndexOf(","), sql.length()).append("\n)ENGINE=INNODB DEFAULT CHARSET=UTF8 AUTO_INCREMENT=1;\r\n");
        //System.out.println("建表sql");
        //System.out.println(sql);
        return sql.toString();
    }

    //(9).向数据库执行sql
    public static void execSql(String createSql,String tableName,Boolean dropOldTable){

        try {
            String driver = MYSQL_DRIVER;
            String url = MYSQL_URL;
            String user = MYSQL_USERNAME;
            String password = MYSQL_PASSWORD;
            Class.forName (driver);
            Connection conn = DriverManager.getConnection(url,user,password);
            if (!conn.isClosed ()) {
                Statement statement = conn.createStatement();
                if(dropOldTable){
                    statement.addBatch("drop table if exists "+tableName+";");
                }
                statement.addBatch(createSql);
                statement.executeBatch();
                statement.close ();
                conn.close ();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    //(10).通过表名和包路径生成mapper到指定位置
    @SneakyThrows
    public static void genMapperByConfig(String cfgPath,String tableName,String targetPackage,String targetProject){
        //MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        //读取我们的 MBG 配置文件
        ConfigurationParser cp = new ConfigurationParser(warnings);
        InputStream is = CodeGenerator.class.getResourceAsStream(cfgPath);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        //1.配置配置文件中的表名和代码生成的地址
        List<Context> contexts = config.getContexts();
        Context context = contexts.get(0);

        //(1).设置表名
        List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
        TableConfiguration tableConfiguration = tableConfigurations.get(0);
        tableConfiguration.setTableName(tableName);

        //(2).设置targetPackage(mapper生成到指定的包路径)
        context.getJavaClientGeneratorConfiguration().setTargetPackage(targetPackage);

        //(3).设置targetProject(mapper生成到指定的程序路径)
        context.getJavaClientGeneratorConfiguration().setTargetProject(targetProject);

        //当生成的代码重复时，不要覆盖原代码
        DefaultShellCallback callback = new DefaultShellCallback(false);
        //创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        //输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }


    //(11).生成前端vue和api.js
    public static void genVueAndApiJs(String tableName,String poJoCnName,Object object,Boolean includeMultipartFile,String dbType) {
        try {
            freemarker.template.Configuration cfg = getConfiguration(dbType);
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = toUpperCaseInitial(tableName);
            String modelNameLowerCamel = toLowerCaseInitial(tableName);
            data.put("baseRequestMapping", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("pojoNameUpperCamel", modelNameUpperCamel);
            data.put("pojoNameLowerCamel", modelNameLowerCamel);
            data.put("basePackage", BASE_PACKAGE);
            data.put("pojoCnName", poJoCnName);
            data.put("includeMultipartFile", includeMultipartFile?"1":"0");

            List<BeanVo> fileNameAndTypeList = ReflectUtil.getFileNameAndTypeList(object);
            data.put("fileNameAndTypeList", fileNameAndTypeList);

            File file = new File(JAVA_PATH + PACKAGE_PATH_VUE + modelNameLowerCamel + ".vue");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            cfg.getTemplate("crudVue.ftl").process(data, writer);
            System.out.println(modelNameLowerCamel + ".vue 生成成功");

            File file1 = new File(JAVA_PATH + PACKAGE_PATH_VUE + modelNameLowerCamel + ".js");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
            cfg.getTemplate("crudApi.ftl").process(data, writer1);
            System.out.println(modelNameLowerCamel + ".js 生成成功");

        } catch (Exception e) {
            throw new RuntimeException("生成vue失败", e);
        }
    }

    //2.基本配置
    //(1).freeMarker配置
    private static freemarker.template.Configuration getConfiguration(String dbType) throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        if("mysql".equals(dbType)){
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH_MYSQL));
        }else if ("mongo".equals(dbType)){
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH_MONGO));
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    //(2).包名转换为路径
    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    //(3).首字母大写转换
    public static String toUpperCaseInitial(String string) {
        char[] methodName = string.toCharArray();
        methodName[0] = toUpperCase(methodName[0]);
        return String.valueOf(methodName);
    }

    //(4).首字母小写转换
    public static String toLowerCaseInitial(String string) {
        char[] methodName = string.toCharArray();
        methodName[0] = toLowerCase(methodName[0]);
        return String.valueOf(methodName);
    }

    //(5).pojo首字母转换为小写
    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

}