package io.github.oguzhancevik.obj2gltf.obj;

import java.nio.FloatBuffer;
import java.util.logging.Logger;

/**
 * Utility methods for correcting the length of normals that have been read
 * from an OBJ file.<br>
 * <br>
 * This class should not be considered to be part of the public API!
 */
public class ObjNormals {
    /**
     * The logger used in this class
     */
    private static final Logger logger =
            Logger.getLogger(ObjNormals.class.getName());

    /**
     * An epsilon for normals that have a length of nearly zero
     */
    private static final double EPSILON = 1e-6;

    /**
     * Normalize the normals in the given buffer, which is assumed to contain
     * the (x,y,z) coordinates of the normals. If there are normals that have
     * nearly zero length, they will be replaced by an unspecified default,
     * and a warning will be printed.<br>
     * <br>
     * This is done specifically for glTF, because non-normalized normals
     * are reported as an error by the validator.
     *
     * @param normals The normals buffer
     */
    public static void normalize(FloatBuffer normals) {
        int numZeroLengthNormals = 0;
        int n = normals.capacity() / 3;
        for (int i = 0; i < n; i++) {
            float x = normals.get(i * 3);
            float y = normals.get(i * 3 + 1);
            float z = normals.get(i * 3 + 2);
            double length = Math.sqrt(x * x + y * y + z * z);
            float nx = 1.0f;
            float ny = 0.0f;
            float nz = 0.0f;
            if (length < EPSILON) {
                numZeroLengthNormals++;
            } else {
                float invLength = (float) (1.0 / length);
                nx = x * invLength;
                ny = y * invLength;
                nz = z * invLength;
            }
            normals.put(i * 3, nx);
            normals.put(i * 3 + 1, ny);
            normals.put(i * 3 + 2, nz);
        }
        if (numZeroLengthNormals > 0) {
            logger.warning("There have been " + numZeroLengthNormals
                    + " normals with zero length. Using (1,0,0) as a default.");
        }
    }

    /**
     * Private constructor to prevent instantiation
     */
    private ObjNormals() {
        // Private constructor to prevent instantiation
    }
}