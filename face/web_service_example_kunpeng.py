import face_recognition
from flask import Flask, jsonify, request, redirect
import uuid
import OBSDAO
import sys
import os
from PIL import Image
sys.path.append("./")
# You can change this to any folder on your system
ALLOWED_EXTENSIONS = { 'jpg', 'jpeg', 'gif'}

app = Flask(__name__)


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app.route('/face/register/', methods=['GET', 'POST'])
def upload_image1():
    # 检测图片是否上传成功
    if request.method == 'POST':
        if 'file' not in request.files:
            # file_avalible文件是否可用
            # file_uuid 0为未生成UUID
            # face_found_in_image 是否发现人脸 0表示无人脸 1表示有人脸
            # is_picture_of_self 是否是本人 0表示不是 1表示是 2表示无意义
            result1 = {
                "file_avalible": False,
                "file_uuid": '0',
                "face_found_in_image": '0',
                "is_picture_of_self": '0'
            }
            return jsonify(result1)

        file = request.files.get('file')

        if file.filename == '':
            result2 = {
                "file_avalible": False,
                "file_uuid": '0',
                "face_found_in_image": '0',
                "is_picture_of_self": '0'
            }
            return jsonify(result2)

        if file and allowed_file(file.filename):
            people_uuid = str(uuid.uuid1()).replace("-","")
            # 图片上传成功，检测图片中的人脸
            return register_pic(people_uuid,file)

    # 图片上传失败，输出以下html代码
    result0 = {
        "file_avalible": False,
        "file_uuid": '0',
        "face_found_in_image": '0',
        "is_picture_of_self": '0'
    }
    return jsonify(result0)

@app.route('/face/check/', methods=['GET', 'POST'])
def upload_image2():
    # 检测图片是否上传成功
    if request.method == 'POST':
        if 'file' not in request.files:
            # file_avalible文件是否可用
            # file_uuid 0为未生成UUID
            # face_found_in_image 是否发现人脸 0表示无人脸 1表示有人脸
            # is_picture_of_self 是否是本人 0表示不是 1表示是 2表示无意义
            result1 = {
                "file_avalible":False,
                "file_uuid": '0',
                "face_found_in_image": '0',
                "is_picture_of_self": '0'
            }
            return jsonify(result1)

        file = request.files['file']

        if file.filename == '':
            result2 = {
                "file_avalible":False,
                "file_uuid": '0',
                "face_found_in_image": '0',
                "is_picture_of_self": '0'
            }
            return jsonify(result2)
        try:
            request.form.get("uuid")
            if file and allowed_file(file.filename):
                people_uuid = request.form.get("uuid", type=str, default=None)
                # 图片上传成功，检测图片中的人脸
                return detect_faces_in_image(people_uuid, file)

        except:
            result1 = {
                "file_avalible": False,
                "file_uuid": '0',
                "face_found_in_image": '0',
                "is_picture_of_self": '0'
            }
            return jsonify(result1)


    # 图片上传失败，输出以下html代码
    # return '''
    # <!doctype html>
    # <title>Is this a picture of Obama?</title>
    # <h1>Upload a picture and see if it's a picture of Obama!</h1>
    # <form method="POST" enctype="multipart/form-data">
    #   <input type="text" name="uuid" value="ac1e369c6d2211ea87c9000c290ad379">
    #   <input type="file" name="file">
    #   <input type="submit" value="Upload">
    # </form>
    # '''

# 返回图片的编码
def get_faces_encoding(pic):
    pic_encoding = face_recognition.face_encodings(pic)[0]
    return pic_encoding

# 注册时提交图片，判断图片是否为人脸连
def register_pic(people_uuid, file_stream):
    # 用face_recognition.face_encodings(img)接口提前把奥巴马人脸的编码录入


    # 载入用户上传的图片
    img = face_recognition.load_image_file(file_stream)
    # 为用户上传的图片中的人脸编码
    known_face_encodings = face_recognition.face_encodings(img)

    face_found = '0'

    if len(known_face_encodings) > 0:
        face_found = '1'

    # 讲识别结果以json键值对的数据结构输出
    result = {
        "file_avalible":True,
        "file_uuid": people_uuid,
        "face_found_in_image": '1',
        "is_picture_of_self": '2'
    }
    people_pic_encoding =  known_face_encodings[0]
    OBSDAO.savePic(people_uuid, people_pic_encoding,file_stream)
    return jsonify(result)


# 后期验证时，判断是否为本人
def detect_faces_in_image(people_uuid,file_stream):

    # 根据uuid return encoding
    # known_face_encoding = OBSDAO.getPicEncondingByUUID(people_uuid)
    pic_path = people_uuid+".jpg"
    # known_pic = Image.open(pic_path)
    if(os.path.exists(pic_path) != True):
        print("file not cunzaima?=======================")
        result2 = {
            "file_avalible": False,
            "file_uuid": '0',
            "face_found_in_image": '0',
            "is_picture_of_self": '0'
        }
        return jsonify(result2)
    print("file cunzai******************************")
    # 载入用户上传的图片
    known_pic_face = face_recognition.load_image_file(pic_path)
    # 为用户上传的图片中的人脸编码
    known_face_encoding = face_recognition.face_encodings(known_pic_face)[0]
    print("==========known_pic_face=================")
    print(known_face_encoding)
    print("===========known_pic_face============")

    # 载入用户上传的图片
    img = face_recognition.load_image_file(file_stream)
    # 为用户上传的图片中的人脸编码
    unknown_face_encodings = face_recognition.face_encodings(img)

    print("============unknown_face_encodings===============")
    print(unknown_face_encodings)
    print("============unknown_face_encodings==============")
    face_found = '0'
    is_self = '0'

    if len(unknown_face_encodings) > 0:
        face_found = '1'
        # 看看图片中的第一张脸是不是self
        match_results = face_recognition.compare_faces([known_face_encoding], unknown_face_encodings[0])
        if match_results[0]:
            is_self = '1'

        # 讲识别结果以json键值对的数据结构输出
    result = {
        "file_avalible": True,
        "file_uuid": people_uuid,
        "face_found_in_image": face_found,
        "is_picture_of_self": is_self
    }
    return jsonify(result)


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5001, debug=True)
