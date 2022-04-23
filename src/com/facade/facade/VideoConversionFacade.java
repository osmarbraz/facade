package com.facade.facade;

import com.facade.medialibrary.AudioMixer;
import com.facade.medialibrary.BitrateReader;
import com.facade.medialibrary.Codec;
import com.facade.medialibrary.CodecFactory;
import com.facade.medialibrary.MPEG4CompressionCodec;
import com.facade.medialibrary.OggCompressionCodec;
import com.facade.medialibrary.VideoFile;
import java.io.File;

/**
 * Fachada fornece uma interface de usuário simples de conversão de vídeo.
 */
public class VideoConversionFacade {

    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversao iniciada.");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if (format.equals("mp4")) {
            destinationCodec = new MPEG4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversao completada.");
        return result;
    }
}
