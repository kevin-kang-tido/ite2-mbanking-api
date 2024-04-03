package co.istad.ite2mbanking.feature.media;

import co.istad.ite2mbanking.feature.media.dto.MediaResponse;
import co.istad.ite2mbanking.util.MediaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
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

       newName +="." + MediaUtil.extractExtension(file.getName());


//        log.info("extension: {}",extension);
//        newName = newName +"." + extension;
//        log.info("New name: ",newName);

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
                .extension(MediaUtil.extractExtension(file.getName()))
                .size(file.getSize())
                .uri(String.format("%s%s/%s",baseUri,fileName,newName))
                .build();

    }

    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {

        // create empty list wm wait  for adding uploaded file
        List<MediaResponse> mediaResponses = new ArrayList<>();
        // use loop to upload  each file
        files.forEach(file->{
            MediaResponse mediaResponse = this.upLoadSingle(file, folderName);
            mediaResponses.add(mediaResponse);
        });
        return mediaResponses;
    }
    @Override
    public MediaResponse loadMediaByName(String mediaName,String fileName) {
        Path path = Paths.get(serverPath+fileName +"\\"+  mediaName);
        // create absobute path of media

        try{
            Resource resource = new UrlResource(path.toUri());
            log.info("Load File: {}",mediaName);
            if (!resource.exists()){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media has not been Found!"
                );
            }

            return MediaResponse.builder()
                    .name(mediaName)
                    .contentType(Files.probeContentType(path))
                    .extension(MediaUtil.extractExtension(mediaName))
                    .size(resource.contentLength())
                    .uri(String.format("%s%s/%s",baseUri,fileName,mediaName))
                    .build();

        }catch(MalformedURLException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Media has not been found"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName) {
        Path path = Paths.get(serverPath+folderName +"\\"+  mediaName);
        try{
            long size = Files.size(path);
            if (Files.deleteIfExists(path)){
                return MediaResponse.builder()
                        .name(mediaName)
                        .contentType(Files.probeContentType(path))
                        .extension(MediaUtil.extractExtension(mediaName))
                        .size(size)
                        .uri(String.format("%s%s/%s",baseUri,folderName,mediaName))
                        .build();
            }
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Media has not been found!"
            );
        }catch(IOException e){
            throw  new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Media Path:%s can not be deleted!",e.getLocalizedMessage())
            );
        }
    }
}
