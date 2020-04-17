package de.pierreschwang.bedwars.i18n;

import de.pierreschwang.bedwars.BedwarsPlugin;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class LanguageHandler {

    private final Map<String, Language> languages = new HashMap<>();
    private final BedwarsPlugin plugin;

    public LanguageHandler(BedwarsPlugin plugin) {
        this.plugin = plugin;
        final File languageFolder = new File(plugin.getDataFolder() + "/lang");
        languageFolder.mkdirs();
        copyFromResources(languageFolder.toPath());
    }

    private void copyFromResources(Path target) {
        try {
            final Path path = FileSystems.newFileSystem(getClass().getResource("").toURI(), Collections.emptyMap()).getPath("/lang");
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (!file.toString().endsWith(".properties"))
                        return FileVisitResult.CONTINUE;
                    Path dest = target.resolve(path.relativize(file).toString());
                    if (!dest.toFile().exists()) {
                        Files.copy(file, dest);
                    }
                    try (final BufferedReader reader = Files.newBufferedReader(file)) {
                        synchronize(reader, dest);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private void synchronize(BufferedReader template, Path current) {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(current.toFile(), true));
             final BufferedReader reader = new BufferedReader(new FileReader(current.toFile()))) {
            final Properties properties = new Properties();
            properties.load(template);
            final Properties curr = new Properties();
            curr.load(reader);
            // Update current file (e.g. new locales)
            for (String key : properties.stringPropertyNames()) {
                if (curr.containsKey(key))
                    continue;
                writer.newLine();
                writer.write(key + " = " + properties.getProperty(key));
                writer.newLine();
                curr.setProperty(key, properties.getProperty(key));
            }
            writer.flush();

            // Store locales in cache
            String fileName = current.getFileName().toString();
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            final Map<String, String> translations = new HashMap<>();
            for (String key : curr.stringPropertyNames()) {
                translations.put(key, curr.getProperty(key));
            }
            languages.put(fileName, new Language(fileName, translations));
            plugin.getLogger().info("Loaded language " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String translate(String language, String key, Object... params) {
        return languages.getOrDefault(language, languages.get("en_US")).translate(key, params);
    }

}