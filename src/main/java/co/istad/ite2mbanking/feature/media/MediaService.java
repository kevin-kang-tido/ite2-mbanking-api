package co.istad.ite2mbanking.feature.media;

import co.istad.ite2mbanking.feature.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    // upload single
    MediaResponse upLoadSingle(MultipartFile file,String fileName);

}
