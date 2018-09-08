package morabem.converters;

import morabem.domain.Foto;
import morabem.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class base64ToFotoConverter implements Converter<String, Foto> {

    @Autowired
    private StorageService storageService;

    @Override
    public Foto convert(String s) {
        if (s.contains("base64")) {
            Foto f = new Foto();
            f.setUrl(storageService.saveBase64Image(s));
            return f;
        }
        return null;
    }
}
