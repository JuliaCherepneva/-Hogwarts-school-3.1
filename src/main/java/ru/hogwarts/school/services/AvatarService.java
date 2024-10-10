package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    // Запись данных на локальный диск и в БД
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.getById(studentId);

        // Класс Path (интерфейс). В нем будем хранить путь до директории с загружаемыми файлами.
        //avatarsDir — это переменная, которая содержит значение из свойств.
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));

        // Создаем нужную нам директорию для хранения данных и удаляем из нее файл, если он уже присутствует там.
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                // Открываем входной поток командой avatarFile.getInputStream() и начинаем считывать данные.
                InputStream is = avatarFile.getInputStream();
                // Но нет смысла создавать поток, если данные из него некуда передавать. Для этого создадим выходной поток.
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                // Создали буферизированные потоки, чтобы передавать пачками байтов (по 1024 байт) из входного потока в выходной.
                // Мы определили все переменные и обозначили, откуда будем читать,
                // куда будем записывать и по сколько байт будем передавать данные.
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            // Запускаем сам процесс передачи данных методом transferTo
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.getByStudentId(studentId);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}
