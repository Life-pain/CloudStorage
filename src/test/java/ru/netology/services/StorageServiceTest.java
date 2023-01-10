package ru.netology.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.dto.FileResponse;
import ru.netology.entities.File;
import ru.netology.repositories.FileRepository;
import ru.netology.security.SimpleSecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StorageServiceTest {
    @InjectMocks
    private StorageService storageService;
    @Mock
    FileRepository fileRepository;
    private final File file = new File();
    private final List<File> fileList = new ArrayList<>();
    private final String FILENAME = "test Filename";
    private final String TEST_LOGIN = "test Login";
    private final String TEST_TOKEN = "test Token";

    @Test
    void getFilesTest() {
        file.setFileName(FILENAME);
        fileList.add(file);

        try (MockedStatic<SimpleSecurity> utilities = Mockito.mockStatic(SimpleSecurity.class)) {
            utilities.when(() -> SimpleSecurity.getOwnerByToken(TEST_TOKEN))
                    .thenReturn(TEST_LOGIN);
            given(fileRepository.findAllByOwner(TEST_LOGIN)).willReturn(Optional.of(fileList));
            List<FileResponse> responseList = storageService.getFiles(TEST_TOKEN, 1);

            assertEquals(responseList.get(0).getFilename(), file.getFileName());
        }
    }
}
