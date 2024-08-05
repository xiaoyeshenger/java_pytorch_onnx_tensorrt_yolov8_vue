import time
import json
from rocketmq.client import Producer, Message, SendStatus
from rocketmq.client import PushConsumer, ConsumeStatus


def send_msg(server_url, group_name, topic, msg_body):
    # 初始化生产者
    producer = Producer(group_name)
    # 设置NameServer地址
    producer.set_name_server_address(server_url)
    # 启动生产者
    producer.start()

    # 构建消息(需要将消息发送到指定Topic)
    message = Message(topic)
    # 设置消息内容(注意消息只能是字符串)
    message.set_body(json.dumps(msg_body).encode('utf-8'))
    # 可以设置其他属性，如Tags、Keys等
    # message.set_tags('your_tag')
    # message.set_keys('your_key')

    try:
        # 发送消息(此处就会发送消息到RocketMQ服务器)
        ret = producer.send_sync(message)
        #print(ret.status, ret.msg_id, ret.offset)
        # 打印发送结果
        if ret.status == SendStatus.OK:
            print(f"step2 ---> 发送成功, 消息状态: {ret.status, ret.msg_id, ret.offset}")
        else:
            print(f"step2 ---> 发送失败, 消息状态: {ret.status}")
    except Exception as e:
        # 处理发送过程中可能出现的异常
        print(f"Send message failed, exception: {e}")
    finally:
        #停止生产者
        producer.shutdown()


def consume_msg(server_url, group_name, topic):
    def callback(msg):
        print(msg.id, msg.body)
        return ConsumeStatus.CONSUME_SUCCESS

    consumer = PushConsumer(group_name)
    consumer.set_name_server_address(server_url)
    consumer.subscribe(topic, callback)
    consumer.start()
    while True:
        time.sleep(3600)
    consumer.shutdown()
    return consumer


if __name__ == '__main__':
    msg_body = {"id": "zyyy", "nameee": "fff", "messageee": "是哈哈rrr"}
    send_msg('192.168.2.6:9876', '1_gsis', 'yolov8_sl_fh', msg_body)