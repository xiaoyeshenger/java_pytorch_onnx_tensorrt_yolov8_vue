from minio import Minio
from minio.error import S3Error
import logging
import json


logging.basicConfig(filename='logs/my_minio_log.log',
                    level=logging.INFO,
                    format=' %(asctime)s - %(levelname)s- %(message)s')


class Bucket:
    def __init__(self, file, file_name, bucket_name):
        # minio的地址，账户，密码
        self.url = "192.168.2.241:9001"
        self.access = "admin"
        self.secret = "adminjskj2017"
        # 桶名称
        self.bucketName = bucket_name
        # 当前被操作的文件
        self.file = file
        # 文件名
        self.file_name = file_name
        # 连接 minio_util
        self.client = Minio(self.url, self.access, self.secret, secure=False)

    # 不存在则创建桶.
    def createBucket(self):
        found = self.client.bucket_exists(self.bucketName)
        if not found:
            self.client.make_bucket(self.bucketName)
            self.setBucketPublic()
            logging.info(f'创建桶:{self.bucketName}')
        else:
            logging.info(f'桶已存在:{self.bucketName}')

    # 上传文件(文件名) 桶名称 ，上传后的文件名， 当前传输的文件
    def uploadFlie(self):
        self.client.fput_object(
            self.bucketName, self.file_name, self.file,
        )
        logging.info(f"{self.file} 上传至 {self.bucketName} ")

    # 创建桶并上传文件() 桶名称 ，上传后的文件名， 当前传输的文件
    def createBucketAndUploadFlie(self):
        self.createBucket()
        self.client.fput_object(
            self.bucketName, self.file_name, self.file,
        )
        logging.info(f"{self.file} 上传至 {self.bucketName} ")

    # 上传文件流() 桶名称 ，上传后的文件名， 当前传输的文件
    def uploadFlieStream(self):
        self.client.put_object(
            self.bucketName, self.file_name, self.file, self.file.getbuffer().nbytes
        )
        logging.info(f"{self.file} 上传至 {self.bucketName} ")

    # 创建桶并上传文件流() 桶名称 ，上传后的文件名， 当前传输的文件
    def createBucketAndUploadFlieStreamAnd(self):
        self.createBucket()
        self.client.put_object(
            self.bucketName, self.file_name, self.file, self.file.getbuffer().nbytes
        )
        logging.info(f"{self.file} 上传至 {self.bucketName} ")

    # 下载文件 桶名称 ， 要下载的文件名称， 下载后的文件名称
    def downloadFile(self):
        self.client.fget_object(
            self.bucketName, self.goods, self.goods,
        )
        print("successfully")

    # 删除桶内的文件 桶名称 ，文件名称
    def deleteFlie(self):
        self.client.remove_object(self.bucketName, self.goods)

    # 设置桶权限
    def setBucketPublic(self):
        policy = {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Principal": {"AWS": "*"},
                    "Action": [
                        "s3:GetBucketLocation",
                        "s3:ListBucket",
                        "s3:ListBucketMultipartUploads",
                    ],
                    "Resource": f"arn:aws:s3:::{self.bucketName}",
                },
                {
                    "Effect": "Allow",
                    "Principal": {"AWS": "*"},
                    "Action": [
                        "s3:GetObject",
                        "s3:PutObject",
                        "s3:DeleteObject",
                        "s3:ListMultipartUploadParts",
                        "s3:AbortMultipartUpload",
                    ],
                    "Resource": f"arn:aws:s3:::{self.bucketName}/*",
                },
            ],
        }
        self.client.set_bucket_policy(self.bucketName, json.dumps(policy))


    # 主入口
    def main(self):
        # 创建桶
        self.createBucket()
        # 上传文件
        self.uploadFlie()
        # 下载文件
        self.downloadFile()
        # 删除文件
        self.deleteFlie()


if __name__ == "__main__":
    try:
        Bucket("logs/my_minio_log.log", '893.txt', "algcenter-abc116").createBucketAndUploadFlie()
    except S3Error as exc:
        print("error occurred.", exc)