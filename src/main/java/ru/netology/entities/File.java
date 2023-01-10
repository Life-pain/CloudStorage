package ru.netology.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String ContentType;
    private long size;
    @Lob
    private byte[] content;
    private String owner;

    public File(String fileName, String ContentType, long size, byte[] content, String owner) {
        this.fileName = fileName;
        this.ContentType = ContentType;
        this.size = size;
        this.content = content;
        this.owner = owner;
    }
}