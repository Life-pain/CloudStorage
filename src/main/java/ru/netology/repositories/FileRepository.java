package ru.netology.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.entities.File;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("select f from File f where f.owner = :owner")
    Optional<List<File>> findAllByOwner(@Param("owner") String owner);

    File findByFileNameAndOwner(String filename, String owner);

    void removeByFileNameAndOwner(String filename, String owner);

    @Modifying
    @Query("update File f set f.fileName = :newName where f.fileName = :filename and f.owner = :owner")
    void renameFile(@Param("filename") String filename, @Param("newName") String newFilename, @Param("owner") String owner);
}