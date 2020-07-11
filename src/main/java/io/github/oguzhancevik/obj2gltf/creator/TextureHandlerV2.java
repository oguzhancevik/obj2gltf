package io.github.oguzhancevik.obj2gltf.creator;

import java.util.LinkedHashMap;
import java.util.Map;

import de.javagl.jgltf.impl.v2.GlTF;
import de.javagl.jgltf.impl.v2.Image;
import de.javagl.jgltf.impl.v2.Sampler;
import de.javagl.jgltf.impl.v2.Texture;
import de.javagl.jgltf.model.Optionals;

/**
 * A class for managing the {@link Texture}s of a {@link GlTF}. It allows
 * obtaining a {@link Texture} index for a given {@link Image#getUri()
 * image URI}, creating the {@link Image} and the {@link Texture} if they
 * do not exist yet.
 */
class TextureHandlerV2 {
    /**
     * The {@link GlTF} which contains the {@link Image}s and {@link Texture}s
     */
    private final GlTF gltf;

    /**
     * The index of the common {@link Sampler} that will be used for all
     * {@link Texture}s. The {@link Sampler} will be created on demand
     * and added to the {@link GlTF}
     */
    private int samplerIndex;

    /**
     * A mapping from {@link Image#getUri() image URIs} to {@link Texture}
     * indices
     */
    private final Map<String, Integer> imageUriToTextureIndex;

    /**
     * Creates a new texture handler that creates {@link Image}s and
     * {@link Texture}s in the given {@link GlTF} as necessary
     *
     * @param gltf The {@link GlTF}
     */
    TextureHandlerV2(GlTF gltf) {
        this.gltf = gltf;
        this.samplerIndex = -1;
        this.imageUriToTextureIndex = new LinkedHashMap<>();
    }

    /**
     * Returns the index of the {@link Texture} that contains the {@link Image}
     * with the given {@link Image#getUri() image URI}. If such a
     * {@link Texture} did not exist yet, it is created (together with the
     * {@link Image}) and stored in the {@link GlTF}
     *
     * @param imageUri The {@link Image#getUri() image URI}
     * @return The {@link Texture} index
     */
    int getTextureIndex(String imageUri) {
        // Create the common (default) sampler if it was not created yet
        if (samplerIndex == -1) {
            Sampler sampler = new Sampler();
            samplerIndex = Optionals.of(gltf.getSamplers()).size();
            gltf.addSamplers(sampler);
        }

        Integer textureIndex = imageUriToTextureIndex.get(imageUri);
        if (textureIndex != null) {
            return textureIndex;
        }

        // Create the image
        Image image = new Image();
        image.setUri(imageUri);
        int imageIndex = Optionals.of(gltf.getImages()).size();
        gltf.addImages(image);

        // Create the texture that refers to the image
        Texture texture = new Texture();
        texture.setSource(imageIndex);
        texture.setSampler(samplerIndex);
        textureIndex = Optionals.of(gltf.getTextures()).size();
        gltf.addTextures(texture);

        imageUriToTextureIndex.put(imageUri, textureIndex);
        return textureIndex;
    }


}