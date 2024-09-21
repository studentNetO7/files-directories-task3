import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
// запишем экземпляр класса в файл
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void zipFiles(String zipPath, List<File> inputFiles) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {
// получаем все вложенные объекты в каталоге
            for (File file : inputFiles) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    zout.putNextEntry(new ZipEntry(file.getName()));
// считываем содержимое файла в массив byte
                    byte[] buffer = new byte[fis.available()];
                    int count;
                    while ((count = fis.read(buffer)) != -1) {
                        zout.write(buffer, 0, count);
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void openZip(String zipPath, String dirPath){
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();// получим название файла
                // распаковка
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());}

    }
    public static void openProgress(String path){
        GameProgress gameProgress = null;
// откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
// десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);

    }
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        File dir1 = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\src");
        if (!dir1.exists()) {
            dir1.mkdir();
            System.out.println("Каталог src создан");
        }
        sb.append("Каталог src создан успешно!\n");

        File dir2 = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\res");

        if (!dir2.exists()) {
            dir2.mkdir();
            System.out.println("Каталог res создан");
        }
        sb.append("Каталог res создан успешно!\n");

        File dir3 = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames");

        if (!dir3.exists()) {
            dir3.mkdir();
            System.out.println("Каталог savegames создан");
        }
        sb.append("Каталог savegames создан успешно!\n");

        File dir4 = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\temp");

        if (!dir4.exists()) {
            dir4.mkdir();
            System.out.println("Каталог temp создан");
        }
        sb.append("Каталог temp создан успешно!\n");

        // создадим директории main и test в каталоге src
        File dirSrcMain = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\src\\main");

        if (!dirSrcMain.exists()) {
            dirSrcMain.mkdir();
            System.out.println("Каталог main создан");
        }
        sb.append("Подкаталог main каталога src создан успешно!\n");

        File dirSrcTest = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\src\\test");

        if (!dirSrcTest.exists()) {
            dirSrcTest.mkdir();
            System.out.println("Каталог test создан");
        }
        sb.append("Подкаталог test каталога src создан успешно!\n");
        // создадим файл Main.java
        File mainFile = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\src\\main\\Main.java");

        try {
            if (mainFile.createNewFile())
                System.out.println("Файл Main.java был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        sb.append("Файл Main.java подкаталога test каталога src создан успешно!\n");
        // создадим файл Utils.java
        File mainUtils = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\src\\main\\Utils.java");

        try {
            if (mainUtils.createNewFile())
                System.out.println("Файл Utils.java был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        sb.append("Файл Utils.java подкаталога test каталога src создан успешно!\n");
        //  создадим директории drawables, vectors, icons в каталоге res
        File dirResDrawables = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\res\\drawables");

        if (!dirResDrawables.exists()) {
            dirResDrawables.mkdir();
            System.out.println("Каталог drawables создан");
        }
        sb.append("Подкаталог drawables каталога res создан успешно!\n");
        File dirResVectors = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\res\\vectors");

        if (!dirResVectors.exists()) {
            dirResVectors.mkdir();
            System.out.println("Каталог vectors создан");
        }
        sb.append("Подкаталог vectors каталога res создан успешно!\n");
        File dirResIcons = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\res\\icons");

        if (!dirResIcons.exists()) {
            dirResIcons.mkdir();
            System.out.println("Каталог icons создан");
        }
        sb.append("Подкаталог icons каталога res создан успешно!\n");
        // создадим файл temp.txt в каталоге temp
        File tempTemp = new File("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\temp\\temp.txt");

        try {
            if (tempTemp.createNewFile())
                System.out.println("Файл temp.txt был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        sb.append("Файл temp.txt подкаталога test каталога temp создан успешно!\n");
        System.out.println(sb);
        String report = sb.toString();
//     запись в temp.txt информации об успешноном или неуспешном создании файлов и директорий
        try (FileWriter writer = new FileWriter(("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\temp\\temp.txt"))) {

            writer.write(report);
// дозаписываем и очищаем буфер
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
// создадим 3 экземпляра класса сохраненной игры

        GameProgress gameProgress1 = new GameProgress(94, 10, 2, 254.32);
        GameProgress gameProgress2 = new GameProgress(84, 5, 3, 274.52);
        GameProgress gameProgress3 = new GameProgress(54, 1, 5, 354.32);

// откроем выходной поток для записи в файл

        saveGame("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\save1.dat", gameProgress1);
        saveGame("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\save2.dat", gameProgress2);
        saveGame("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\save3.dat", gameProgress3);
        List<File> inputFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(dir3.listFiles())));
        System.out.println(inputFiles);

//
        zipFiles("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\zip.zip", inputFiles);
        if (dir3.isDirectory()) {
// получаем все вложенные объекты в каталоге
            for (File item : dir3.listFiles()) {
// проверим, является ли объект каталогом
                if (item.getName().equals("zip.zip")) {
                    System.out.println(item.getName() + " - Архивированный каталог");
                } else {
                    item.delete();
                    System.out.println("Несохраненный файл " + item.getName() + " - удален ");
                }
            }
        }

//        Производим распаковку архива в папке savegames
        openZip("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\zip.zip", dir3.getName());
//        if (dir3.isDirectory()) {
// получаем все вложенные объекты в каталоге
//            for (File item : dir3.listFiles()) {
// проверим, является ли объект каталогом
//                        if (item.isDirectory()) {
//                            System.out.println(item.getName() + " - каталог");
//                        } else {
//                            System.out.println(item.getName() + " - файл");
//                        }
//                    }
//                }


//        Производим считывание и десериализацию разархивированного файла save1.dat.
        openProgress("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\save1.dat");
        //        Производим считывание и десериализацию разархивированного файла save2.dat.
        openProgress("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\save2.dat");

        //        Производим считывание и десериализацию разархивированного файла save3.dat.
        openProgress("C:\\Users\\Ольга\\IdeaProjects\\java core\\1.3files-directories\\Games\\savegames\\save3.dat");


    }
}