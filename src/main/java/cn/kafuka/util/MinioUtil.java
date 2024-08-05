package cn.kafuka.util;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MinioUtil {

    /**
     * 桶占位符
     */
    private static final String BUCKET_PARAM = "${bucket}";
    /**
     * bucket权限-只读
     */
    private static final String READ_ONLY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "/*\"]}]}";
    /**
     * bucket权限-只读
     */
    private static final String WRITE_ONLY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "/*\"]}]}";
    /**
     * bucket权限-读写
     */
    private static final String READ_WRITE = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\",\"s3:AbortMultipartUpload\"],\"Resource\":[\"arn:aws:s3:::" + BUCKET_PARAM + "/*\"]}]}";


    @Value("${minio.endpoint}")
    private String minioEndpoint;

    @Value("${minio.access}")
    private String minioAccess;


    @Autowired
    private MinioClient minioClient;


    /*
     * @Author: zhangyong
     * description: 查看存储桶bucket是否存在
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    public Boolean existsBucket(String bucketName) {
        Boolean found;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return found;
    }

    /*
     * @Author: zhangyong
     * description: 创建存储桶bucket
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    public Boolean createBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
            //默认设置桶的权限为读写权限，需要更新成其他权限，执行setBucketPolicy方法
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(READ_WRITE.replace(BUCKET_PARAM, bucketName)).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新桶权限策略
     *
     * @param bucketName 桶
     * @param policy 权限
     */
    public  void setBucketPolicy(String bucketName, String policy) throws Exception {
        switch (policy) {
            case "read-only":
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(READ_ONLY.replace(BUCKET_PARAM, bucketName)).build());
                break;
            case "write-only":
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(WRITE_ONLY.replace(BUCKET_PARAM, bucketName)).build());
                break;
            case "read-write":
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(READ_WRITE.replace(BUCKET_PARAM, bucketName)).build());
                break;
            case "none":
            default:
                break;
        }
    }


    /*
     * @Author: zhangyong
     * description: 删除存储桶bucket
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /*
     * @Author: zhangyong
     * description: 上传文件
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    public Boolean upload(String bucketName,MultipartFile file) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(file.getOriginalFilename())
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getEndpointUrl() {
        return minioEndpoint;
    }

    public String getAccessUrl() {
        return minioAccess;
    }

    public String getDefaultPicUrl() {
        return minioEndpoint + "/default/kafuka_picture.jpg";
    }

    public String getDefaultFileUrl() {
        return minioEndpoint + "/default/example.file";
    }


    /*
     * @Author: zhangyong
     * description: 上传文件
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    @SneakyThrows
    public Boolean upload(String bucketName,MultipartFile file, String fileName) {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        return true;
    }


    /*
     * @Author: zhangyong
     * description: 文件下载
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    public void download(String bucketName, String fileName, HttpServletResponse res) {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucketName)
                .object(fileName).build();
        try (GetObjectResponse response = minioClient.getObject(objectArgs)) {
            byte[] buf = new byte[1024];
            int len;
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
                while ((len = response.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.flush();
                byte[] bytes = os.toByteArray();
                res.setCharacterEncoding("utf-8");
                //设置强制下载不打开
                res.setContentType("application/force-download");
                res.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                try (ServletOutputStream stream = res.getOutputStream()) {
                    stream.write(bytes);
                    stream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @Author: zhangyong
     * description: 查看文件对象
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    public List<Map<String,Object>> listObjects(String bucketName) {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).build());
        List<Map<String,Object>> objectItems = new ArrayList<>();
        try {
            for (Result<Item> result : results) {
                Item item = result.get();
                Map<String,Object> objectMap = new HashMap<>();
                objectMap.put("name",item.objectName());
                objectMap.put("size",item.size());
                objectItems.add(objectMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return objectItems;
    }

    /*
     * @Author: zhangyong
     * description: 批量删除文件对象
     * @Date: 2021-03-22 16:45
     * @Param:
     * @Return:
     */
    public Iterable<Result<DeleteError>> removeObjects(String bucketName, List<String> objects) {
        List<DeleteObject> dos = objects.stream().map(e -> new DeleteObject(e)).collect(Collectors.toList());
        Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(dos).build());
        return results;
    }

    /**
     * 获取文件
     * @param bucket 桶名称
     * @param filename 文件名
     */
    public File getFIle(String bucket, String filename) {
        //获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filename).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", ".tmp");
            try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = tempFile;
        tempFile.delete();

        return file;
    }

    /**
     * 保存文件到指定位置
     *
     * @param bucket 桶名称
     * @param filename 文件名
     * @param targetPath 存储的路径
     */
    public void saveToFile(String bucket, String filename, String targetPath) {
        InputStream stream = null;
        try {
            stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filename).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        File targetFile = new File(targetPath);
        try {
            FileUtils.copyInputStreamToFile(stream, targetFile);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getBytes(String bucket, String filename) {
        //获取文件输入流
        InputStream inputStream;
        byte[] bytes;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filename).build());
            bytes = IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bytes;
    }
}
