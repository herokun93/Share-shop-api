package share.shop.utils;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ResizeImage {
    private File file;

    private String imageFolder="C:\\Users\\jsys04\\Documents\\GitHub\\Share-shop-api\\Files-Upload\\shops\\1";

    private Integer imageHSize =300;
    private Integer imageWSize =300;

    public boolean  resizeStart(File sourceFile){
        try {
            BufferedImage bufferedImage = ImageIO.read(sourceFile);
            BufferedImage outputImage = Scalr.resize(bufferedImage, imageHSize,imageWSize);
            String newFileName = FilenameUtils.getBaseName(sourceFile.getName())
                    + "_" + imageHSize.toString()
                    + "_" + imageWSize.toString()
                    + "." + FilenameUtils.getExtension(sourceFile.getName());
            Path path = Paths.get(imageFolder,newFileName);
            File newImageFile = path.toFile();
            ImageIO.write(outputImage, "jpg", newImageFile);
            outputImage.flush();
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public File upload(MultipartFile imageFile) {
        try {
            Path path = Paths.get(imageFolder, imageFile.getOriginalFilename());
            Files.write(path, imageFile.getBytes());
            return path.toFile();
        } catch (IOException e) {
            return null;
        }
    }
}
