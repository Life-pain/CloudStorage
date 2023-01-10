package ru.netology.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.dto.FileResponse;
import ru.netology.entities.File;
import ru.netology.repositories.FileRepository;
import ru.netology.security.SimpleSecurity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StorageService {
    private final FileRepository fileRepository;

    public StorageService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<FileResponse> getFiles(String authToken, int limit) {
        Optional<List<File>> fileList = fileRepository.findAllByOwner(SimpleSecurity.getOwnerByToken(authToken));
        return fileList.get().stream().map(fr -> new FileResponse(fr.getFileName(), fr.getSize()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void uploadFile(String authToken, String filename, MultipartFile file) throws IOException {
        fileRepository.save(new File(filename, file.getContentType(), file.getSize(), file.getBytes(), SimpleSecurity.getOwnerByToken(authToken)));
    }

    public void deleteFile(String authToken, String filename) {
        fileRepository.removeByFileNameAndOwner(filename, SimpleSecurity.getOwnerByToken(authToken));
    }

    public File downloadFile(String authToken, String filename) {
        return fileRepository.findByFileNameAndOwner(filename, SimpleSecurity.getOwnerByToken(authToken));
    }

    public void renameFile(String authToken, String filename, String newFilename) {
        fileRepository.renameFile(filename, newFilename, SimpleSecurity.getOwnerByToken(authToken));
    }
}