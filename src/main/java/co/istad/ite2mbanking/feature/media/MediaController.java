package co.istad.ite2mbanking.feature.media;

import co.istad.ite2mbanking.feature.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medias")
public class MediaController {
    private  final  MediaService mediaService;
    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestPart MultipartFile file){
        return mediaService.upLoadSingle(file,"IMAGE");
    }
    @PostMapping("/upload-multiple")
    List<MediaResponse> uploadMultiple (@RequestPart List<MultipartFile> files){
        return mediaService.uploadMultiple(files,"IMAGE");

    }
    @GetMapping("/{mediaName}")
    MediaResponse loadMediaByName(@PathVariable String mediaName){
        return  mediaService.loadMediaByName(mediaName,"IMAGE");


    }
    @DeleteMapping("/{mediaName}")
    MediaResponse deleteMediaByName(@PathVariable  String mediaName){
        return  mediaService.deleteMediaByName(mediaName,"IMAGE");

    }

    @GetMapping("/load-all-media")
    List<MediaResponse> loadAllMedia (){
        return mediaService.loadAllMedia("IMAGE");
    }

    // code by teacher
    // produces = Accept
    // consumes = Content-Type
    @GetMapping(path = "/{mediaName}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<?> downloadMediaByName(@PathVariable String mediaName) {
        System.out.println("Start download");
        Resource resource = mediaService.downloadMediaByName(mediaName, "IMAGE");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + mediaName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }



    // produces = acccept
    // consumes = content-Type
    // code by me
//    @GetMapping(value = "/download/{fileName}")
//    public ResponseEntity<Resource> downloadMediaByName(String folderName, @PathVariable String fileName) {
//        return mediaService.downloadMediaByName("IMAGE", fileName);
//    }





}
