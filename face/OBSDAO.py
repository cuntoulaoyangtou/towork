import sys
import OBSUtils
import os
import numpy as np
from PIL import Image
from io import BytesIO
sys.path.append("./")
"""
主要功能：
注册时完成保存图片
对比时返回图片

"""
conn = OBSUtils.get_OSCONN()

# 引入模块
from obs import ObsClient

# 创建ObsClient实例
obsClient = ObsClient(
	access_key_id='GIU8GSPODXJOLHSG509Y',
	secret_access_key='NqTcPiqV0jzNlbbJb0Imxtzp8uDt5SDbMfBjQhQS',
	server='https://obs.cn-south-1.myhuaweicloud.com',
)


# 保存图片
def savePic(uuid,pic_encoding,pic,filepath="./"):
    with open('encoding.txt','a+',encoding='utf-8') as f:
        temp = uuid+':'+str(pic_encoding).replace("\n",' ').strip()
        f.write(temp)
        f.write("\n")
    print("pic type========================")
    print(type(pic))
    im = Image.open(pic)
    im.convert('RGB')
    t1 = os.path.join(filepath, uuid)
    savename = t1 + '.jpg'
    im.save(savename)

    resp = obsClient.putFile('xxl', t1, file_path=savename)  # localfile为上传的本地文件路径，需要指定到具体的文件名

    if resp.status < 300:
        print('requestId:', resp.requestId)
    else:
        print('errorCode:', resp.errorCode)
        print('errorMessage:', resp.errorMessage)


    # pic.save(savename)
    # with open(savename,'wb') as p:
    #     p.write(pic)


# 保存图片编码和图片唯一id
def savePicEncondingAndUUID(uuid,picEnconding):
    pass

# 根据图片唯一id获取图片编码
def getPicEncondingByUUID(uuid):
    with open('encoding.txt','r',encoding='utf-8') as f:
        dic = []
        for line in f.readlines():
            line = line.strip('\n')  # 去掉换行符\n
            b = line.split(':')  # 将每一行以空格为分隔符转换成列表
            dic.append(b)
        dic = dict(dic)
        pic_encoding = dic[uuid]
        print(pic_encoding)
        nd = np.fromstring(pic_encoding,sep=" ")
        print("***********************************")
        print(type(nd))
        print(nd.shape)
        print(nd)
        print("***********************************")
        return nd
