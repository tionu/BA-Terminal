package tools;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tools {

	public static List<String> listResourceFiles(String folder) {
		URI uri;
		FileSystem fileSystem = null;
		try {
			uri = Tools.class.getResource(folder).toURI();
			Path myPath;
			if (uri.getScheme().equals("jar")) {
				fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
				myPath = fileSystem.getPath(folder);
			} else {
				myPath = Paths.get(uri);
			}
			return Files.list(myPath).filter(Files::isRegularFile).map(item -> item.getFileName().toString())
					.collect(Collectors.toList());
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		} finally {
			if (fileSystem != null && fileSystem.isOpen()) {
				try {
					fileSystem.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new ArrayList<>();
	}

}
