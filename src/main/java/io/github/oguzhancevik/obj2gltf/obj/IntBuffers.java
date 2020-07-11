package io.github.oguzhancevik.obj2gltf.obj;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import de.javagl.jgltf.model.io.Buffers;

/**
 * Utility methods related to <code>IntBuffer</code> instances
 */
public class IntBuffers {
    /**
     * Convert the given input buffer to a byte buffer that contains the data
     * of the input buffer, converted to elements with the given element size.
     * If the given element size is 1 or 2, then the resulting buffer will
     * contain the bytes of the elements of the input buffer, casted to
     * <code>byte</code> or <code>short</code>, respectively. If the element
     * size is 4, then the resulting buffer will contain the bytes of the
     * elements of the input buffer.
     *
     * @param inputBuffer The input buffer
     * @param elementSize The element size
     * @return The byte buffer
     * @throws IllegalStateException If the given element size is neither
     *                               1, 2 nor 4
     */
    public static ByteBuffer convertToByteBuffer(
            IntBuffer inputBuffer, int elementSize) {
        switch (elementSize) {
            case 1:
                return Buffers.castToByteBuffer(inputBuffer);
            case 2:
                return Buffers.castToShortByteBuffer(inputBuffer);
            case 4:
                return Buffers.createByteBufferFrom(inputBuffer);
            default:
                break;
        }
        throw new IllegalArgumentException(
                "The elementSize must be 1, 2 or 4, but is " + elementSize);
    }

    /**
     * Private constructor to prevent instantiation
     */
    private IntBuffers() {
        // Private constructor to prevent instantiation
    }

}