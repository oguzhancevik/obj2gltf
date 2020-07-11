package io.github.oguzhancevik.obj2gltf.obj;

import de.javagl.jgltf.impl.v1.Buffer;

/**
 * An enumeration of strategies for the creation of {@link Buffer}
 * instances when converting an OBJ file into a glTF asset.
 */
public enum BufferStrategy {
    /**
     * Create one {@link Buffer} for the whole OBJ file
     */
    BUFFER_PER_FILE,

    /**
     * Create one {@link Buffer} for each MTL group
     */
    BUFFER_PER_GROUP,

    /**
     * Create one {@link Buffer} for each part (for the case that
     * the geometry data was split into multiple parts to obey
     * the 16 bit index constraint)
     */
    BUFFER_PER_PART,
}