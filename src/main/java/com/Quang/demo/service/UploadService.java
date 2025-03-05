package com.Quang.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {

  private final ServletContext servletContext; // setup để upload image

  public UploadService(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
    if (file.isEmpty()) { // kiểm tra file có rỗng không, nếu rỗng thì return "" ==> ko lưu file
      return "";
    }

    String rootPath = this.servletContext.getRealPath("/resources/images"); // lấy ra đường dẫn absolute
    // path của path
    // vòng try catch để set up lưu trữ file trên server
    String finalName = "";
    try {
      byte[] bytes = file.getBytes(); // lấy byte trong request

      File dir = new File(rootPath + File.separator + targetFolder); // nơi lưu file, lấy rootpath trên + /avatar

      if (!dir.exists()) // kiểm tra thư mục tồn tại chưa, nếu chưa thì tạo
        dir.mkdirs();

      // Create the file on server
      finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename(); // rename file ảnh
      File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName); // vị trí lưu

      BufferedOutputStream stream = new BufferedOutputStream(
          new FileOutputStream(serverFile)); // lưu file

      stream.write(bytes);
      stream.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return finalName;
  }
}
