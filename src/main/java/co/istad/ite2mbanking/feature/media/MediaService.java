package co.istad.ite2mbanking.feature.media;

import co.istad.ite2mbanking.feature.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    // upload single
    MediaResponse upLoadSingle(MultipartFile file,String fileName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files,String folderName);

    MediaResponse loadMediaByName (String mediaName,String fileName);

    MediaResponse deleteMediaByName(String mediaName, String folderName);

}
