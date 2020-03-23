package com.java.toworkservice.controller;

import com.alibaba.fastjson.JSON;
import com.java.toworkservice.config.ServerConfig;
import com.java.toworkservice.entity.UserInfo;
import com.java.toworkservice.service.UserInfoService;
import com.java.toworkservice.utils.FileUtil;

import config.Constants;
import entity.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.JwtUtil;
import utils.ResultGenerator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassNameUploadContrellor
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/22 0022 19:44
 * @Version V1.0
 **/
@RestController
@RequestMapping("api/upload")
public class UploadController {
    @Autowired
    ServerConfig serverConfig;
    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        String header = request.getHeader(Constants.getRequestHeaderToken());
        System.out.println(Constants.getRequestHeaderToken()+"\t"+header);
        Claims claims = JwtUtil.parseJWT(header);
        String subject = claims.getSubject();
        UserInfo userInfo = JSON.parseObject(subject, UserInfo.class);

        String fileName = FileUtil.storeFile(file);
        String fileDownloadUri = serverConfig.getUrl()+"/api/upload/downloadFile/"+fileName;
        //文件文件上传
        userInfo.setAvatar(fileDownloadUri);
        UserInfo userInfo1 = userInfoService.changeUserInfo(userInfo);

        return ResultGenerator.genSuccessResult(userInfo1);
    }
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = FileUtil.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
