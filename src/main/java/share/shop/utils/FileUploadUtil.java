package share.shop.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class FileUploadUtil {



    public static String saveFile(String fileExtension, MultipartFile multipartFile, String subFolder)
            throws IOException {
        Path uploadPath = Paths.get("Files-Upload/"+subFolder) ;

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        Path filePath ;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            filePath = uploadPath.resolve(fileCode + "."+fileExtension);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileExtension, ioe);
        }

        return filePath.toString();
    }

    public static String resizeStart(String subFolder,MultipartFile imageFile,Integer imageSize){
        Path uploadPath = Paths.get("Files-Upload/"+subFolder) ;

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String imageFolder=uploadPath.toString();

        Path pathFile = Paths.get(imageFolder, imageFile.getOriginalFilename());
        try {
            Files.write(pathFile, imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File sourceFile = pathFile.toFile();
        try {
            BufferedImage bufferedImage = ImageIO.read(sourceFile);
            BufferedImage outputImage = Scalr.resize(bufferedImage, imageSize);

            String fileCode = RandomStringUtils.randomAlphanumeric(8);

            String newFileName = FilenameUtils.getBaseName(fileCode)
                    + "_" + imageSize.toString()
                    + "." + FilenameUtils.getExtension(sourceFile.getName());

            Path path = Paths.get(imageFolder,newFileName);
            File newImageFile = path.toFile();
            ImageIO.write(outputImage,FilenameUtils.getExtension(sourceFile.getName()), newImageFile);
            outputImage.flush();
            return newImageFile.toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
//
//    public File upload(MultipartFile imageFile) {
//        try {
//            Path path = Paths.get(imageFolder, imageFile.getOriginalFilename());
//            Files.write(path, imageFile.getBytes());
//            return path.toFile();
//        } catch (IOException e) {
//            return null;
//        }
//    }


}
