package com.arifsyncjava.restfulapi.upload;

import com.arifsyncjava.restfulapi.Command;
import com.arifsyncjava.restfulapi.response.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static java.nio.file.Paths.get;

@Service
public class FileUploadService implements Command<MultipartFile, SimpleResponse> {

    private String fileSavedPath = "/Desktop/file";

    private  final String fullFilePath  = System.getProperty("user.home")+fileSavedPath;


    @Override
    public ResponseEntity<SimpleResponse> execute(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path fileStorage = get(fullFilePath,fileName).toAbsolutePath().normalize();

        try(
                ReadableByteChannel readSource = Channels.newChannel(file.getInputStream());
                FileChannel writeSource = FileChannel.open(fileStorage,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE)) {

            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (readSource.read(buffer) != -1 ) {
                buffer.flip();
                writeSource.write(buffer);
                buffer.clear();
            }
        } catch (IOException exception) {
            throw new FileUploadException();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SimpleResponse("File upload successful"));
    }



}
