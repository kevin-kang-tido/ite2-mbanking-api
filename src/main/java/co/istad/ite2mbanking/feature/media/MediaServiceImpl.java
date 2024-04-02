package co.istad.ite2mbanking.feature.media;

import co.istad.ite2mbanking.feature.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService{

    @Value("${media.server-path}")
    private  String serverPath;
    @Value("${media.base-uri}")
    private  String baseUri;

    @Override
    public MediaResponse upLoadSingle(MultipartFile file,String fileName) {
//        log.info(file.getContentType());
        String newName= UUID.randomUUID().toString();
        // extract extension form file upload
        // assume profile.png
        int lastDotIndex = file.getOriginalFilename()
                .lastIndexOf(".");
        String extension = file.getOriginalFilename()
                .substring(lastDotIndex+1);

        log.info("extension: {}",extension);
        newName = newName +"." + extension;
        log.info("New name: ",newName);

        // copy file
        Path path = Paths.get(serverPath+fileName +"\\"+  newName);

        try{
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus
                    .INTERNAL_SERVER_ERROR,e.getLocalizedMessage());
        }


        return  MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(String.format("%s%s/%s",baseUri,fileName,newName))
                .build();

    }
}
